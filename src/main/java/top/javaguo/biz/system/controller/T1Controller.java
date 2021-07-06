package top.javaguo.biz.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.T1;
import top.javaguo.biz.system.service.T1Service;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;

/**
 * @describe t1
 * @author admin
 * @date 2021-06-17 15:07:44
 */
@RestController
@RequestMapping("/system/t1")
public class T1Controller extends BaseController<T1>{

/**t1**/
	@Autowired
	private T1Service t1Service;/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(T1 bean) { return t1Service.selectAll(bean); }

	/**
	 * 通过id删除
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(t1Service.deleteById(id), t1Service);
	}

	/**
	 * 通过id查询
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(T1 bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return t1Service.selectById(bean); 
	}

	/**
	 * 通过ids集合删除
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) { 
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(t1Service.deleteByIds(ids), t1Service); 
	}

	/**
	 * 添加
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(T1 bean) {
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(t1Service.insert(bean), t1Service);
	}

	/**
	 * 通过id修改
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(T1 bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(t1Service.updateById(bean), t1Service);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(T1 bean) { return t1Service.selectAllForLayUI(bean); }

	/**LayUI根据条件查询所有**/
	@GetMapping("/test1")
	public Map<String, Object> test1() {
		Map<String, Object> stringObjectHashMap = new HashMap<>();
		stringObjectHashMap.put("data",t1Service.test1());
		return stringObjectHashMap;
	}


}
