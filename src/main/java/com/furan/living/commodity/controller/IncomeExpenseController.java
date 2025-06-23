package com.furan.living.commodity.controller;

import java.util.Arrays;
import java.util.Map;

import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furan.living.commodity.entity.IncomeExpenseEntity;
import com.furan.living.commodity.service.IncomeExpenseService;




/**
 * 收支记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@RestController
@RequestMapping("commodity/incomeexpense")
public class IncomeExpenseController {
    @Autowired
    private IncomeExpenseService incomeExpenseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = incomeExpenseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		IncomeExpenseEntity incomeExpense = incomeExpenseService.getById(id);

        return R.ok().put("incomeExpense", incomeExpense);
    }

    @RequestMapping("/save")
    public R save(@RequestBody IncomeExpenseEntity incomeExpense){
        String type = incomeExpense.getType();
        if (type == null || !(type.equals("收入") || type.equals("支出"))) {
            return R.error("类型只能是收入或支出");
        }
        incomeExpenseService.save(incomeExpense);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody IncomeExpenseEntity incomeExpense){
        String type = incomeExpense.getType();
        if (type == null || !(type.equals("收入") || type.equals("支出"))) {
            return R.error("类型只能是收入或支出");
        }
        incomeExpenseService.updateById(incomeExpense);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		incomeExpenseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
