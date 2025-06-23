package com.furan.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.furan.living.commodity.entity.DiaryEntity;
import com.furan.living.commodity.utils.PageUtils;

import java.util.Map;

/**
 * 日记表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
public interface DiaryService extends IService<DiaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

