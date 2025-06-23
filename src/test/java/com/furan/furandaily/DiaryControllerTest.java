package com.furan.furandaily;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.furan.living.commodity.controller.DiaryController;
import com.furan.living.commodity.entity.DiaryEntity;
import com.furan.living.commodity.service.DiaryService;


import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

public class DiaryControllerTest {

    @InjectMocks
    private DiaryController diaryController;

    @Mock
    private DiaryService diaryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试 save 方法：
     * 边界值与等价类分析：
     * 1) weather = null，属于无效输入，应返回错误
     * 2) weather = "" 空字符串，非允许值，应返回错误
     * 3) weather = 非“晴、雨、阴”中任何一个，等价类代表非法输入，应返回错误
     * 4) weather = “晴” 合法输入，应调用服务保存并返回成功
     * 5) weather = “雨” 合法输入，应调用服务保存并返回成功
     * 6) weather = “阴” 合法输入，应调用服务保存并返回成功
     */
    @Test
    public void testSave_WeatherValidation() {
        // case 1: null weather
        DiaryEntity diary1 = new DiaryEntity();
        diary1.setWeather(null);
        R result1 = diaryController.save(diary1);
        assertEquals("天气只能是晴、雨、阴", result1.get("msg"));

        // case 2: empty string
        DiaryEntity diary2 = new DiaryEntity();
        diary2.setWeather("");
        R result2 = diaryController.save(diary2);
        assertEquals("天气只能是晴、雨、阴", result2.get("msg"));

        // case 3: invalid string
        DiaryEntity diary3 = new DiaryEntity();
        diary3.setWeather("多云");
        R result3 = diaryController.save(diary3);
        assertEquals("天气只能是晴、雨、阴", result3.get("msg"));

        // case 4~6: valid inputs
        List<String> validWeathers = Arrays.asList("晴", "雨", "阴");
        for (String w : validWeathers) {
            DiaryEntity diary = new DiaryEntity();
            diary.setWeather(w);
            // 模拟保存成功，服务层返回R.ok()
            when(diaryService.save(diary)).thenReturn(R.ok().get("msg") == "success");
            R res = diaryController.save(diary);
            assertEquals("success", res.get("msg"));
            verify(diaryService, times(1)).save(diary);
            reset(diaryService);
        }
    }

    /**
     * 测试 update 方法，逻辑与 save 方法一致：
     * 同样验证天气字段的合法性，覆盖同样的边界与等价类
     */
    @Test
    public void testUpdate_WeatherValidation() {
        // case 1: null weather
        DiaryEntity diary1 = new DiaryEntity();
        diary1.setWeather(null);
        R result1 = diaryController.update(diary1);
        assertEquals("天气只能是晴、雨、阴", result1.get("msg"));

        // case 2: empty string
        DiaryEntity diary2 = new DiaryEntity();
        diary2.setWeather("");
        R result2 = diaryController.update(diary2);
        assertEquals("天气只能是晴、雨、阴", result2.get("msg"));

        // case 3: invalid string
        DiaryEntity diary3 = new DiaryEntity();
        diary3.setWeather("多云");
        R result3 = diaryController.update(diary3);
        assertEquals("天气只能是晴、雨、阴", result3.get("msg"));

        // case 4~6: valid inputs
        List<String> validWeathers = Arrays.asList("晴", "雨", "阴");
        for (String w : validWeathers) {
            DiaryEntity diary = new DiaryEntity();
            diary.setWeather(w);
            // 模拟更新成功，服务层返回R.ok()
            when(diaryService.updateById(diary)).thenReturn(R.ok().get("msg") == "success");
            R res = diaryController.update(diary);
            assertEquals("success", res.get("msg"));
            verify(diaryService, times(1)).updateById(diary);
            reset(diaryService);
        }
    }

    /**
     * 测试 list 方法，主要验证调用流程和返回结果：
     * 传入空参数和带参数均可，模拟 service 返回分页对象
     */
    @Test
    public void testList() {
        Map<String, Object> params = new HashMap<>();
        PageUtils pageUtils = mock(PageUtils.class);
        when(diaryService.queryPage(params)).thenReturn(pageUtils);

        R result = diaryController.list(params);

        assertNotNull(result);
        assertEquals(pageUtils, result.get("page"));
        verify(diaryService, times(1)).queryPage(params);
    }

    /**
     * 测试 info 方法：
     * 测试不同的 id 参数，包括有效id和不存在的id(模拟返回 null)：
     * 验证返回结果是否正确封装
     */
    @Test
    public void testInfo() {
        Integer validId = 1;
        DiaryEntity diary = new DiaryEntity();
        diary.setWeather("晴");

        when(diaryService.getById(validId)).thenReturn(diary);
        R result = diaryController.info(validId);

        assertEquals(diary, result.get("diary"));
        verify(diaryService, times(1)).getById(validId);

        // 模拟id不存在，返回null
        Integer invalidId = 999;
        when(diaryService.getById(invalidId)).thenReturn(null);
        R result2 = diaryController.info(invalidId);
        assertNull(result2.get("diary"));
        verify(diaryService, times(1)).getById(invalidId);
    }

}
