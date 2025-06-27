package com.furan.living.commodity.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@Data
@TableName("income_expense")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomeExpenseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.INPUT)
	@JsonProperty("id")
	private Integer id;

	/**
	 * 类型：收入或支出
	 */
	@TableField("type")
	@JsonProperty("type")
	private String type;

	/**
	 * 分类（如餐饮、交通、工资等）
	 */
	@TableField("category")
	@JsonProperty("category")
	private String category;

	/**
	 * 金额
	 */
	@TableField("amount")
	@JsonProperty("amount")
	private Integer amount;

	/**
	 * 备注
	 */
	@TableField("note")
	@JsonProperty("note")
	private String note;

	/**
	 * 发生日期
	 */
	@TableField(value ="record_date", fill = FieldFill.INSERT)
	@JsonProperty("recordDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date recordDate;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonProperty("createTime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createTime;

	@Override
	public String toString() {
		return "IncomeExpenseEntity{" +
				"id=" + id +
				", type='" + type + '\'' +
				", category='" + category + '\'' +
				", amount=" + amount +
				", note='" + note + '\'' +
				", recordDate=" + recordDate +
				", createTime=" + createTime +
				'}';
	}
}
