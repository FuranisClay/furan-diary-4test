package com.furan.living.commodity.service.impl;

import com.furan.living.commodity.controller.IncomeExpenseController;
import com.furan.living.commodity.entity.DiaryEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.Query;
import com.furan.living.commodity.utils.R;

import com.furan.living.commodity.dao.IncomeExpenseDao;
import com.furan.living.commodity.entity.IncomeExpenseEntity;
import com.furan.living.commodity.service.IncomeExpenseService;


@Service("incomeExpenseService")
public class IncomeExpenseServiceImpl extends ServiceImpl<IncomeExpenseDao, IncomeExpenseEntity> implements IncomeExpenseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<IncomeExpenseEntity> page = this.page(
                new Query<IncomeExpenseEntity>().getPage(params),
                new QueryWrapper<IncomeExpenseEntity>().eq(StringUtils.isNotBlank(key), "id", key)
        );


        return new PageUtils(page);
    }

}
