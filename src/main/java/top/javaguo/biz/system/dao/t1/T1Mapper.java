package top.javaguo.biz.system.dao.t1;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.T1;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe t1
 * @author admin
 * @date 2021-06-17 15:07:44
 */
public class T1Mapper extends BaseMapper<T1> {

	/**查询条件**/
	public String commonWhere(T1 bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.t1 = " , bean.getT1())
			+ whereAddFilter("t.create_time >= " , bean.getQueryBeginDate())
			+ whereAddFilter("t.create_time < " , bean.getQueryEndDate())
			;
	}

	/**所需关联查询字段**/
	private String getSelectFieldsForRelation() {
		return " t.id"
				+ " , t.create_time"
				+ " , t.update_time"
				+ " , t.is_deleted"
				+ " , t.t1"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " t1 t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")T1 bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")T1 bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		T1 bean = new T1();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}


}

