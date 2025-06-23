package com.furan.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.furan.living.commodity.utils.PageUtils;
import com.furan.living.commodity.utils.Query;
import com.furan.living.commodity.utils.R;
import com.furan.living.commodity.entity.LifeRecordEntity;

import java.util.Map;

/**
 * 生活记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
public interface LifeRecordService extends IService<LifeRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

