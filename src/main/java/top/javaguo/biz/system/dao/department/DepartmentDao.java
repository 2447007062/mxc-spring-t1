package top.javaguo.biz.system.dao.department;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import top.javaguo.biz.system.bean.Department;
import top.javaguo.biz.system.bean.SysUser;
import top.javaguo.biz.system.dao.sysUser.SysUserMapper;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe 部门
 * @author javaGuo
 * @date 2018-11-29
 */
@Mapper
public interface DepartmentDao extends BaseDao<Department> {

	/** @Example: mybatis映射一对一例子所需单元 **/
	@Select("select * from department where id = #{id}")
	public Department selectById(@Param("id") String id);

}
