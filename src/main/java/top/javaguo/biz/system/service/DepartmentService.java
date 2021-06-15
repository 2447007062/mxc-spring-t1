package top.javaguo.biz.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import top.javaguo.biz.system.bean.Department;
import top.javaguo.biz.system.bean.SysUser;
import top.javaguo.biz.system.dao.department.DepartmentDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.cacheNames.SpringCacheNames;
import top.javaguo.core.resp.RespBean;

/**
 * @describe 部门
 * @author javaGuo
 * @date 2018-12-06
 */
@Service
@SuppressWarnings("all")
@CacheConfig(cacheNames = SpringCacheNames.DEPARTMENTONE) // 此注解下所有spring cache 注解中 cacheNames默认属性
public class DepartmentService extends BaseService<Department> {

	/** 部门 **/
	@Autowired
	private DepartmentDao departmentDao;

	/** 通过id查询 **/
	@Cacheable(key = "#id")
	public Department selectById(String id) {
		System.out.println("department selectById开始.....");
		try {
			Thread.sleep(3 * 1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("department selectById结束.....");
		return departmentDao.selectById(id);
	}

}
