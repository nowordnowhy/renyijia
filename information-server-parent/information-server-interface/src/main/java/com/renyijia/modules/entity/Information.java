package com.renyijia.modules.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhouwenya
 * @since 2019-08-12
 */
@Data
@Accessors(chain = true)
public class Information extends Model<Information> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 标题
     */
	private String title;
    /**
     * 发布者
     */
	private String author;
    /**
     * 发布时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 正文
     */
	private String content;
    /**
     * 发布者id
     */
	private Integer userId;
    /**
     * 收藏数
     */
	private Integer collectNum;
    /**
     * 发布状态
     */
	private Integer status;
	private Boolean exist;
    /**
     * 图片链接
     */
	private String picUrl;
    /**
     * 过期时间
     */
	private Date expireTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Information{" +
			", id=" + id +
			", title=" + title +
			", author=" + author +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", content=" + content +
			", userId=" + userId +
			", collectNum=" + collectNum +
			", status=" + status +
			", exist=" + exist +
			", picUrl=" + picUrl +
			", expireTime=" + expireTime +
			"}";
	}
}
