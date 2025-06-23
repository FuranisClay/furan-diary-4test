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
 * 健康信息记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@Data
@TableName("health_info")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	@JsonProperty("id")
	private Integer id;

	/**
	 * 记录日期
	 */
	@TableField("record_date")
	@JsonProperty("recordDate")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date recordDate;

	/**
	 * 体重（kg）
	 */
	@TableField("weight")
	@JsonProperty("weight")
	private Integer weight;

	/**
	 * 血压（如 120/80）
	 */
	@TableField("blood_pressure")
	@JsonProperty("bloodPressure")
	private String bloodPressure;

	/**
	 * 步数
	 */
	@TableField("steps")
	@JsonProperty("steps")
	private Integer steps;

	/**
	 * 睡眠时长（小时）
	 */
	@TableField("sleep_hours")
	@JsonProperty("sleepHours")
	private Integer sleepHours;

	/**
	 * 备注
	 */
	@TableField("note")
	@JsonProperty("note")
	private String note;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonProperty("createTime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createTime;

	@Override
	public String toString() {
		return "HealthInfoEntity{" +
				"id=" + id +
				", recordDate=" + recordDate +
				", weight=" + weight +
				", bloodPressure='" + bloodPressure + '\'' +
				", steps=" + steps +
				", sleepHours=" + sleepHours +
				", note='" + note + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
