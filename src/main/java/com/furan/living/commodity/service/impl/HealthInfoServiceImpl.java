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

import com.furan.living.commodity.dao.HealthInfoDao;
import com.furan.living.commodity.entity.HealthInfoEntity;
import com.furan.living.commodity.service.HealthInfoService;


@Service("healthInfoService")
public class HealthInfoServiceImpl extends ServiceImpl<HealthInfoDao, HealthInfoEntity> implements HealthInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<HealthInfoEntity> page = this.page(
                new Query<HealthInfoEntity>().getPage(params),
                new QueryWrapper<HealthInfoEntity>().eq(StringUtils.isNotBlank(key), "id", key)
        );


        return new PageUtils(page);
    }

}
