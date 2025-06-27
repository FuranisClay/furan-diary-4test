package com.furan.living.commodity.service.impl;

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

import com.furan.living.commodity.dao.LifeRecordDao;
import com.furan.living.commodity.entity.LifeRecordEntity;
import com.furan.living.commodity.service.LifeRecordService;


@Service("lifeRecordService")
public class LifeRecordServiceImpl extends ServiceImpl<LifeRecordDao, LifeRecordEntity> implements LifeRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<LifeRecordEntity> page = this.page(
                new Query<LifeRecordEntity>().getPage(params),
                new QueryWrapper<LifeRecordEntity>().eq(StringUtils.isNotBlank(key), "title", key)
        );


        return new PageUtils(page);
    }

}
