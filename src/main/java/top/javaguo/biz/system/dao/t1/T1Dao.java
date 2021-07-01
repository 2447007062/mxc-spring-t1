 package top.javaguo.biz.system.dao.t1;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import top.javaguo.biz.system.bean.T1;
import top.javaguo.core.biz.dao.BaseDao;

/**
 * @describe t1
 * @author admin
 * @date 2021-06-17 15:07:44
 */
@Mapper
public interface T1Dao extends BaseDao<T1> {

	/**根据条件查询所有**/
	@SelectProvider(type=T1Mapper.class,method = "selectAll")
	public List<T1> selectAll(@Param("bean") T1 bean);

	/**根据条件查询所有的个数**/
	@SelectProvider(type=T1Mapper.class,method = "selectTotal")
	public int selectTotal(@Param("bean") T1 bean);

	/**添加**/
	@InsertProvider(type=T1Mapper.class,method = "insert")
	public int insert(@Param("bean") T1 bean);

	/**通过id删除**/
	@DeleteProvider(type=T1Mapper.class,method = "deleteById")
	public int deleteById(@Param("id") String id);

	/**通过id修改**/
	@UpdateProvider(type=T1Mapper.class,method = "updateById")
	public int updateById(@Param("bean") T1 bean);

	/**通过id查询**/
	@SelectProvider(type=T1Mapper.class,method = "selectById")
	public T1 selectById(@Param("bean") T1 bean);

	/**通过ids集合删除**/
	@DeleteProvider(type=T1Mapper.class,method = "deleteByIds")
	public int deleteByIds(@Param("ids") String ids);

	/**根据关联条件查询所有**/
	@SelectProvider(type=T1Mapper.class,method = "selectAllForRelation")
	public List<T1> selectAllForRelation(@Param("bean") T1 bean);

	/**根据关联条件查询所有的个数**/
	@SelectProvider(type=T1Mapper.class,method = "selectTotalForRelation")
	public int selectTotalForRelation(@Param("bean") T1 bean);

	/**根据关联id查询**/
	@SelectProvider(type=T1Mapper.class,method = "selectByIdForRelation")
	public T1 selectByIdForRelation(@Param("id") String id);

}

