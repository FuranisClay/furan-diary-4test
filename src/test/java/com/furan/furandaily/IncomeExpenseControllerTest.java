package com.furan.furandaily;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.furan.living.commodity.controller.IncomeExpenseController;
import com.furan.living.commodity.entity.IncomeExpenseEntity;
import com.furan.living.commodity.service.IncomeExpenseService;
import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.R;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

public class IncomeExpenseControllerTest {

    @InjectMocks
    private IncomeExpenseController incomeExpenseController;

    @Mock
    private IncomeExpenseService incomeExpenseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * save方法测试，重点验证type字段：
     * 等价类：
     * 1) null或空字符串（非法）
     * 2) 非“收入”“支出”的字符串（非法）
     * 3) “收入”和“支出”（合法）
     */
    @Test
    public void testSave_TypeValidation() {
        IncomeExpenseEntity entity = new IncomeExpenseEntity();

        // 1. null类型
        entity.setType(null);
        R res1 = incomeExpenseController.save(entity);
        assertEquals("类型只能是收入或支出", res1.get("msg"));

        // 2. 空字符串
        entity.setType("");
        R res2 = incomeExpenseController.save(entity);
        assertEquals("类型只能是收入或支出", res2.get("msg"));

        // 3. 非法字符串
        entity.setType("借款");
        R res3 = incomeExpenseController.save(entity);
        assertEquals("类型只能是收入或支出", res3.get("msg"));

        // 4. 合法类型 "收入"
        entity.setType("收入");
        when(incomeExpenseService.save(entity)).thenReturn(R.ok().get("msg") == "success");
        R res4 = incomeExpenseController.save(entity);
        assertEquals("success", res4.get("msg"));
        verify(incomeExpenseService, times(1)).save(entity);
        reset(incomeExpenseService);

        // 5. 合法类型 "支出"
        entity.setType("支出");
        when(incomeExpenseService.save(entity)).thenReturn(R.ok().get("msg") == "success");
        R res5 = incomeExpenseController.save(entity);
        assertEquals("success", res5.get("msg"));
        verify(incomeExpenseService, times(1)).save(entity);
    }

    /**
     * update方法测试，type字段校验与save一致
     */
    @Test
    public void testUpdate_TypeValidation() {
        IncomeExpenseEntity entity = new IncomeExpenseEntity();

        // null类型
        entity.setType(null);
        R res1 = incomeExpenseController.update(entity);
        assertEquals("类型只能是收入或支出", res1.get("msg"));

        // 非法字符串
        entity.setType("转账");
        R res2 = incomeExpenseController.update(entity);
        assertEquals("类型只能是收入或支出", res2.get("msg"));

        // 合法类型
        entity.setType("收入");
        when(incomeExpenseService.updateById(entity)).thenReturn(R.ok().get("msg") == "success");
        R res3 = incomeExpenseController.update(entity);
        assertEquals("success", res3.get("msg"));
        verify(incomeExpenseService, times(1)).updateById(entity);
    }

    /**
     * list方法测试，验证调用服务及返回
     */
    @Test
    public void testList() {
        Map<String,Object> params = new HashMap<>();
        PageUtils pageUtils = mock(PageUtils.class);
        when(incomeExpenseService.queryPage(params)).thenReturn(pageUtils);

        R res = incomeExpenseController.list(params);
        assertNotNull(res);
        assertEquals(pageUtils, res.get("page"));
        verify(incomeExpenseService, times(1)).queryPage(params);
    }

    /**
     * info方法测试，验证根据id获取数据
     */
    @Test
    public void testInfo() {
        Integer id = 1;
        IncomeExpenseEntity entity = new IncomeExpenseEntity();
        entity.setType("收入");
        when(incomeExpenseService.getById(id)).thenReturn(entity);

        R res = incomeExpenseController.info(id);
        assertEquals(entity, res.get("incomeExpense"));
        verify(incomeExpenseService, times(1)).getById(id);
    }
}
