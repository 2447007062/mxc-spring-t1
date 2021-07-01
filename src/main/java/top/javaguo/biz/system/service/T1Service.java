package top.javaguo.biz.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.javaguo.biz.system.bean.T1;
import top.javaguo.biz.system.dao.t1.T1Dao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;

/**
 * @describe t1
 * @author admin
 * @date 2021-06-17 15:07:44
 */
@Service
public class T1Service extends BaseService<T1>{

	/**t1**/
	@Autowired
	private T1Dao t1Dao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(T1 bean) { return getResult(t1Dao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(T1 bean) { return getResult(t1Dao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	public RespBean<Map<String, Object>> insert(T1 bean) { return getResult(t1Dao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(t1Dao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(T1 bean) { return getResult(t1Dao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(T1 bean) { return getResult(t1Dao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(t1Dao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}


}

