package com.furan.living.commodity.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 日记表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@Data
@TableName("diary")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日记ID
	 */
	@TableId(type = IdType.INPUT)
	@JsonProperty("id")
	private Integer id;

	/**
	 * 日记标题
	 */
	@TableField("title")
	@JsonProperty("title")
	private String title;

	/**
	 * 日记内容
	 */
	@TableField("content")
	@JsonProperty("content")
	private String content;

	/**
	 * 心情
	 */
	@TableField("mood")
	@JsonProperty("mood")
	private String mood;

	/**
	 * 天气
	 */
	@TableField("weather")
	@JsonProperty("weather")
	private String weather;

	/**
	 * 日记日期
	 */
	@TableField(value ="record_date", fill = FieldFill.INSERT)
	@JsonProperty("recordDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date recordDate;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonProperty("createdAt")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;


	@Override
	public String toString() {
		return "DiaryEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", mood='" + mood + '\'' +
				", weather='" + weather + '\'' +
				", recordDate=" + recordDate +
				", createdAt=" + createdAt +
				'}';
	}
}
