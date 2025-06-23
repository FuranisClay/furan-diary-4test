package com.furan.living.commodity.service.impl;

import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.Query;
import com.furan.living.commodity.utils.R;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.furan.living.commodity.dao.DiaryDao;
import com.furan.living.commodity.entity.DiaryEntity;
import com.furan.living.commodity.service.DiaryService;


@Service("diaryService")
public class DiaryServiceImpl extends ServiceImpl<DiaryDao, DiaryEntity> implements DiaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DiaryEntity> page = this.page(
                new Query<DiaryEntity>().getPage(params),
                new QueryWrapper<DiaryEntity>()
        );

        return new PageUtils(page);
    }

}
