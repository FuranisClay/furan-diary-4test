package com.furan.living.commodity.dao;

import com.furan.living.commodity.entity.HealthInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 健康信息记录表
 * 
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@Mapper
public interface HealthInfoDao extends BaseMapper<HealthInfoEntity> {
	
}
