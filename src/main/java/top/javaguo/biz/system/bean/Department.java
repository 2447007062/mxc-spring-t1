package top.javaguo.biz.system.bean;

import java.io.Serializable;

/**
 * @describe 部门
 * @author javaGuo
 * @date 2018-11-29
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,roleIds,department,notFieldParams";

	/** 部门ID **/
	private String id;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String updateTime;

	/** 部门名 **/
	private String name;

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	/** 获取部门ID **/
	public String getId() {
		return id;
	}

	/** 设置部门ID **/
	public void setId(String id) {
		this.id = id;
	}

	/** 获取创建时间 **/
	public String getCreateTime() {
		return createTime;
	}

	/** 设置创建时间 **/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/** 获取修改时间 **/
	public String getUpdateTime() {
		return updateTime;
	}

	/** 设置修改时间 **/
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/** 获取部门名 **/
	public String getName() {
		return name;
	}

	/** 设置部门名 **/
	public void setNname(String name) {
		this.name = name;
	}

}
