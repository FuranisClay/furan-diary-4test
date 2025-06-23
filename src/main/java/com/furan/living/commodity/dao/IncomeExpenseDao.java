package com.furan.living.commodity.dao;

import com.furan.living.commodity.entity.IncomeExpenseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收支记录表
 * 
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@Mapper
public interface IncomeExpenseDao extends BaseMapper<IncomeExpenseEntity> {
	
}
