package com.furan.furandaily;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.furan.living.commodity.controller.HealthInfoController;
import com.furan.living.commodity.entity.HealthInfoEntity;
import com.furan.living.commodity.service.HealthInfoService;
import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.R;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

public class HealthInfoControllerTest {

    @InjectMocks
    private HealthInfoController healthInfoController;

    @Mock
    private HealthInfoService healthInfoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试 save 方法：
     * 血压格式及数值边界：
     * 1) bloodPressure = null，非法，应返回错误
     * 2) bloodPressure 格式不含“/”，非法，应返回错误
     * 3) bloodPressure 含非数字，非法，应返回错误
     * 4) 收缩压或舒张压 >= 200，非法，应返回错误
     * 睡眠时间边界：
     * 5) sleepHours = null，非法，应返回错误
     * 6) sleepHours >= 24，非法，应返回错误
     * 体重边界：
     * 7) weight = null，非法，应返回错误
     * 8) weight >= 500，非法，应返回错误
     * 9) 合法值，调用服务层保存成功，返回success
     */
    @Test
    public void testSave_Validation() {
        // 1) bloodPressure = null
        HealthInfoEntity entity1 = new HealthInfoEntity();
        entity1.setBloodPressure(null);
        entity1.setSleepHours(8);
        entity1.setWeight(70);
        R res1 = healthInfoController.save(entity1);
        assertEquals("血压格式必须是XX/XX的形式", res1.get("msg"));

        // 2) bloodPressure格式不含"/"
        HealthInfoEntity entity2 = new HealthInfoEntity();
        entity2.setBloodPressure("12080");
        entity2.setSleepHours(8);
        entity2.setWeight(70);
        R res2 = healthInfoController.save(entity2);
        assertEquals("血压格式必须是XX/XX的形式", res2.get("msg"));

        // 3) bloodPressure含非数字
        HealthInfoEntity entity3 = new HealthInfoEntity();
        entity3.setBloodPressure("abc/90");
        entity3.setSleepHours(8);
        entity3.setWeight(70);
        R res3 = healthInfoController.save(entity3);
        assertEquals("血压格式必须是XX/XX的形式", res3.get("msg"));

        // 4) 收缩压>=200
        HealthInfoEntity entity4 = new HealthInfoEntity();
        entity4.setBloodPressure("200/80");
        entity4.setSleepHours(8);
        entity4.setWeight(70);
        R res4 = healthInfoController.save(entity4);
        assertEquals("血压两边数值必须小于200", res4.get("msg"));

        // 4) 舒张压>=200
        HealthInfoEntity entity5 = new HealthInfoEntity();
        entity5.setBloodPressure("120/200");
        entity5.setSleepHours(8);
        entity5.setWeight(70);
        R res5 = healthInfoController.save(entity5);
        assertEquals("血压两边数值必须小于200", res5.get("msg"));

        // 5) sleepHours = null
        HealthInfoEntity entity6 = new HealthInfoEntity();
        entity6.setBloodPressure("120/80");
        entity6.setSleepHours(null);
        entity6.setWeight(70);
        R res6 = healthInfoController.save(entity6);
        assertEquals("睡眠时间必须小于24小时", res6.get("msg"));

        // 6) sleepHours >= 24
        HealthInfoEntity entity7 = new HealthInfoEntity();
        entity7.setBloodPressure("120/80");
        entity7.setSleepHours(24);
        entity7.setWeight(70);
        R res7 = healthInfoController.save(entity7);
        assertEquals("睡眠时间必须小于24小时", res7.get("msg"));

        // 7) weight = null
        HealthInfoEntity entity8 = new HealthInfoEntity();
        entity8.setBloodPressure("120/80");
        entity8.setSleepHours(8);
        entity8.setWeight(null);
        R res8 = healthInfoController.save(entity8);
        assertEquals("体重必须小于500kg", res8.get("msg"));

        // 8) weight >= 500
        HealthInfoEntity entity9 = new HealthInfoEntity();
        entity9.setBloodPressure("120/80");
        entity9.setSleepHours(8);
        entity9.setWeight(500);
        R res9 = healthInfoController.save(entity9);
        assertEquals("体重必须小于500kg", res9.get("msg"));

        // 9) 合法输入，模拟服务层保存成功
        HealthInfoEntity entity10 = new HealthInfoEntity();
        entity10.setBloodPressure("120/80");
        entity10.setSleepHours(8);
        entity10.setWeight(70);

        when(healthInfoService.save(entity10)).thenReturn(R.ok().get("msg") == "success");

        R res10 = healthInfoController.save(entity10);
        assertEquals("success", res10.get("msg"));
        verify(healthInfoService, times(1)).save(entity10);
        reset(healthInfoService);
    }

    /**
     * 测试 update 方法，逻辑与 save 方法一致，复用边界和等价类
     */
    @Test
    public void testUpdate_Validation() {
        // 1) bloodPressure = null
        HealthInfoEntity entity1 = new HealthInfoEntity();
        entity1.setBloodPressure(null);
        entity1.setSleepHours(8);
        entity1.setWeight(70);
        R res1 = healthInfoController.update(entity1);
        assertEquals("血压格式必须是XX/XX的形式", res1.get("msg"));

        // 2) bloodPressure格式不含"/"
        HealthInfoEntity entity2 = new HealthInfoEntity();
        entity2.setBloodPressure("12080");
        entity2.setSleepHours(8);
        entity2.setWeight(70);
        R res2 = healthInfoController.update(entity2);
        assertEquals("血压格式必须是XX/XX的形式", res2.get("msg"));

        // 4) 收缩压>=200
        HealthInfoEntity entity3 = new HealthInfoEntity();
        entity3.setBloodPressure("200/80");
        entity3.setSleepHours(8);
        entity3.setWeight(70);
        R res3 = healthInfoController.update(entity3);
        assertEquals("血压两边数值必须小于200", res3.get("msg"));

        // 5) sleepHours = null
        HealthInfoEntity entity4 = new HealthInfoEntity();
        entity4.setBloodPressure("120/80");
        entity4.setSleepHours(null);
        entity4.setWeight(70);
        R res4 = healthInfoController.update(entity4);
        assertEquals("睡眠时间必须小于24小时", res4.get("msg"));

        // 7) weight = null
        HealthInfoEntity entity5 = new HealthInfoEntity();
        entity5.setBloodPressure("120/80");
        entity5.setSleepHours(8);
        entity5.setWeight(null);
        R res5 = healthInfoController.update(entity5);
        assertEquals("体重必须小于500kg", res5.get("msg"));

        // 9) 合法输入，模拟服务层更新成功
        HealthInfoEntity entity6 = new HealthInfoEntity();
        entity6.setBloodPressure("120/80");
        entity6.setSleepHours(8);
        entity6.setWeight(70);

        when(healthInfoService.updateById(entity6)).thenReturn(R.ok().get("msg") == "success");

        R res6 = healthInfoController.update(entity6);
        assertEquals("success", res6.get("msg"));
        verify(healthInfoService, times(1)).updateById(entity6);
        reset(healthInfoService);
    }

    /**
     * 测试 list 方法，验证调用和返回结果
     */
    @Test
    public void testList() {
        Map<String, Object> params = new HashMap<>();
        PageUtils pageUtils = mock(PageUtils.class);
        when(healthInfoService.queryPage(params)).thenReturn(pageUtils);

        R result = healthInfoController.list(params);

        assertNotNull(result);
        assertEquals(pageUtils, result.get("page"));
        verify(healthInfoService, times(1)).queryPage(params);
    }

    /**
     * 测试 info 方法，验证根据ID查询
     */
    @Test
    public void testInfo() {
        Integer id = 1;
        HealthInfoEntity entity = new HealthInfoEntity();
        entity.setBloodPressure("120/80");

        when(healthInfoService.getById(id)).thenReturn(entity);

        R res = healthInfoController.info(id);

        assertEquals(entity, res.get("healthInfo"));
        verify(healthInfoService, times(1)).getById(id);
    }
}
