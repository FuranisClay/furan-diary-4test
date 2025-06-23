package com.furan.furandaily;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.furan.living.commodity.controller.LifeRecordController;
import com.furan.living.commodity.entity.LifeRecordEntity;
import com.furan.living.commodity.service.LifeRecordService;
import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.R;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

public class LifeRecordControllerTest {

    @InjectMocks
    private LifeRecordController lifeRecordController;

    @Mock
    private LifeRecordService lifeRecordService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * save方法测试，验证图片路径校验：
     * 等价类：
     * - null 或 空字符串 -> 非法
     * - 非URL格式字符串 -> 非法
     * - URL协议非http/https -> 非法（如ftp://）
     * - 合法http或https URL -> 合法
     */
    @Test
    public void testSave_ImageUrlValidation() {
        LifeRecordEntity entity = new LifeRecordEntity();

        // null路径
        entity.setImageUrl(null);
        R res1 = lifeRecordController.save(entity);
        assertEquals("图片路径必须是合法的网络地址", res1.get("msg"));

        // 空字符串
        entity.setImageUrl("");
        R res2 = lifeRecordController.save(entity);
        assertEquals("图片路径必须是合法的网络地址", res2.get("msg"));

        // 非URL格式
        entity.setImageUrl("not_a_url");
        R res3 = lifeRecordController.save(entity);
        assertEquals("图片路径必须是合法的网络地址", res3.get("msg"));

        // 协议非http/https
        entity.setImageUrl("ftp://example.com/image.jpg");
        R res4 = lifeRecordController.save(entity);
        assertEquals("图片路径必须是合法的网络地址", res4.get("msg"));

        // 合法http协议
        entity.setImageUrl("http://example.com/image.jpg");
        when(lifeRecordService.save(entity)).thenReturn(R.ok().get("msg") == "success");
        R res5 = lifeRecordController.save(entity);
        assertEquals("success", res5.get("msg"));
        verify(lifeRecordService, times(1)).save(entity);
        reset(lifeRecordService);

        // 合法https协议
        entity.setImageUrl("https://example.com/image.jpg");
        when(lifeRecordService.save(entity)).thenReturn(R.ok().get("msg") == "success");
        R res6 = lifeRecordController.save(entity);
        assertEquals("success", res6.get("msg"));
        verify(lifeRecordService, times(1)).save(entity);
    }

    /**
     * update方法测试，图片路径校验逻辑与save一致
     */
    @Test
    public void testUpdate_ImageUrlValidation() {
        LifeRecordEntity entity = new LifeRecordEntity();

        entity.setImageUrl(null);
        R res1 = lifeRecordController.update(entity);
        assertEquals("图片路径必须是合法的网络地址", res1.get("msg"));

        entity.setImageUrl("invalid_url");
        R res2 = lifeRecordController.update(entity);
        assertEquals("图片路径必须是合法的网络地址", res2.get("msg"));

        entity.setImageUrl("ftp://example.com/img.png");
        R res3 = lifeRecordController.update(entity);
        assertEquals("图片路径必须是合法的网络地址", res3.get("msg"));

        entity.setImageUrl("http://valid.com/image.png");
        when(lifeRecordService.updateById(entity)).thenReturn(R.ok().get("msg") == "success");
        R res4 = lifeRecordController.update(entity);
        assertEquals("success", res4.get("msg"));
        verify(lifeRecordService, times(1)).updateById(entity);
    }

    /**
     * list方法测试，验证服务调用与返回
     */
    @Test
    public void testList() {
        Map<String, Object> params = new HashMap<>();
        PageUtils pageUtils = mock(PageUtils.class);
        when(lifeRecordService.queryPage(params)).thenReturn(pageUtils);

        R res = lifeRecordController.list(params);
        assertNotNull(res);
        assertEquals(pageUtils, res.get("page"));
        verify(lifeRecordService, times(1)).queryPage(params);
    }

    /**
     * info方法测试，验证根据ID查询
     */
    @Test
    public void testInfo() {
        Integer id = 1;
        LifeRecordEntity entity = new LifeRecordEntity();
        entity.setImageUrl("http://example.com/img.jpg");
        when(lifeRecordService.getById(id)).thenReturn(entity);

        R res = lifeRecordController.info(id);
        assertEquals(entity, res.get("lifeRecord"));
        verify(lifeRecordService, times(1)).getById(id);
    }
}
