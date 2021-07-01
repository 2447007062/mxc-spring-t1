package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe t1
 * @author admin
 * @date 2021-06-17 15:07:44
 */
public class T1 extends BaseBean{

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,notFieldParams"; 
	/**编号**/ 
	private String id; 
  
	/**创建时间**/ 
	private String createTime; 
  
	/**修改时间**/ 
	private String updateTime; 
  
	/**是否删除**/ 
	private String isDeleted; 
  
	/**t1**/ 
	private String t1; 
  
	/**获得编号**/ 
	public String getId(){ 
		return id;
	}
 
	/**设置编号**/ 
 	public void setId(String id){ 
 		this.id = id ;
	} 
 
	/**获得创建时间**/ 
	public String getCreateTime(){ 
		return createTime;
	}
 
	/**设置创建时间**/ 
 	public void setCreateTime(String createTime){ 
 		this.createTime = createTime ;
	} 
 
	/**获得修改时间**/ 
	public String getUpdateTime(){ 
		return updateTime;
	}
 
	/**设置修改时间**/ 
 	public void setUpdateTime(String updateTime){ 
 		this.updateTime = updateTime ;
	} 
 
	/**获得是否删除**/ 
	public String getIsDeleted(){ 
		return isDeleted;
	}
 
	/**设置是否删除**/ 
 	public void setIsDeleted(String isDeleted){ 
 		this.isDeleted = isDeleted ;
	} 
 
	/**获得t1**/ 
	public String getT1(){ 
		return t1;
	}
 
	/**设置t1**/ 
 	public void setT1(String t1){ 
 		this.t1 = t1 ;
	} 
 
/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

		@Override
	public String toString(){ 
		return "T1{" + 
			"id='"+id+'\'' + 
			", createTime='"+createTime+'\'' + 
			", updateTime='"+updateTime+'\'' + 
			", isDeleted='"+isDeleted+'\'' + 
			", t1='"+t1+'\'' + 
			getToStringParam() + 
		'}'; 
	};
}
