package top.javaguo.biz.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.SysPublicParam;
import top.javaguo.biz.system.dao.sysPublicParam.SysPublicParamDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.cacheNames.SpringCacheNames;
import top.javaguo.core.publicParam.PublicParamUtil;
import top.javaguo.core.resp.RespBean;

/**
 * @describe 公共参数
 * @author javaGuo
 * @date 2019-02-26
 */
@Service
@CacheConfig(cacheNames = SpringCacheNames.DEFAULTONE)
public class SysPublicParamService extends BaseService<SysPublicParam>{

	/**公共参数**/
	@Autowired
	private SysPublicParamDao sysPublicParamDao;

	/**根据条件查询所有**/
	@Cacheable(cacheNames = SpringCacheNames.DEFAULTMANY, key = "#bean")
	public RespBean<Map<String, Object>> selectAll(SysPublicParam bean) { return getResult(sysPublicParamDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	@Cacheable(cacheNames = SpringCacheNames.DEFAULTMANY, key = "#bean")
	public Map<String, Object> selectAllForLayUI(SysPublicParam bean) { return getResult(sysPublicParamDao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	@CachePut(key = "#bean.id")
	public RespBean<Map<String, Object>> insert(SysPublicParam bean) { return initPublicParam(getResult(sysPublicParamDao, "insert", bean)); }

	/**通过id删除**/
	@CacheEvict(key = "#id")
	public RespBean<Map<String, Object>> deleteById(String id) { return initPublicParam(getResult(sysPublicParamDao, "deleteById", id)); }

	/**通过id修改**/
	@CachePut(key = "#bean.id")
	public RespBean<Map<String, Object>> updateById(SysPublicParam bean) { return initPublicParam(getResult(sysPublicParamDao, "updateById", bean)); }

	/**通过id查询**/
	@Cacheable(key = "#bean.id")
	public RespBean<Map<String, Object>> selectById(SysPublicParam bean) { return getResult(sysPublicParamDao, "seleteById", bean); }

	/**通过ids集合删除**/
	@CacheEvict(allEntries = true)
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return initPublicParam(getResult(sysPublicParamDao, "deleteByIds", ids)); }

	/** 清空多对象缓存集合 **/
	@CacheEvict(cacheNames = SpringCacheNames.DEFAULTMANY, allEntries = true)
	@Override
	public void clearBeanManyCache() {
	}

    /** 初始化公共参数 **/
    public RespBean<Map<String, Object>> initPublicParam(RespBean<Map<String, Object>> respBean){
        List<SysPublicParam> list = sysPublicParamDao.selectAll(new SysPublicParam());
        PublicParamUtil.map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            SysPublicParam sysPublicParam = list.get(i);
            PublicParamUtil.map.put(sysPublicParam.getParamKey(), sysPublicParam.getParamValue());
        }
        System.out.println("加载系统公共参数共计：" + PublicParamUtil.map.size() + "个");
        return respBean;
    }


}
