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
 * 生活记录表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-20 18:59:07
 */
@Data
@TableName("life_record")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LifeRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	@JsonProperty("id")
	private Integer id;

	/**
	 * 标题
	 */
	@TableField("title")
	@JsonProperty("title")
	private String title;

	/**
	 * 内容
	 */
	@TableField("content")
	@JsonProperty("content")
	private String content;

	/**
	 * 图片路径（可为空）
	 */
	@TableField("image_url")
	@JsonProperty("imageUrl")
	private String imageUrl;

	/**
	 * 记录日期
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
		return "LifeRecordEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", recordDate=" + recordDate +
				", createTime=" + createTime +
				'}';
	}
}
