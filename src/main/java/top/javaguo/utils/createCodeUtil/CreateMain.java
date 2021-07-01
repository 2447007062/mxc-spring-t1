package top.javaguo.utils.createCodeUtil;

import top.javaguo.biz.system.bean.CreateCode;
import top.javaguo.utils.GuoFileUtil;
import top.javaguo.utils.GuoSpringUtil;
import top.javaguo.utils.GuoStringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe 创建代码启动类
 * @author javaGuo
 * @date 2018-02-07
 */
public class CreateMain {

    public static String tempFolderPath = "F:\\createCodeZip\\";
    public static String folderPath = "F:\\createCode";

    /**
     * 创建文件
     */
    public void createFile(String fileTempStr , String path , String fileName) throws IOException {
        String[] paths = (folderPath + path).split("/");
        for (int i = 1; i < paths.length; i++) {
            String temp = "";
            for (int j = 0; j < i + 1; j++) {
                temp += paths[j] + "/";
            }
            GuoFileUtil.creatFolder(temp);
        }
        if(GuoFileUtil.creatTxtFile(folderPath + path, fileName)) {
            GuoFileUtil.writeTxtFile(folderPath + path + fileName,fileTempStr);
        }
    }

    public String create(Map<String,String> map , String className) throws IOException {
        String classNameLower = className.substring(0,1).toLowerCase()+className.substring(1);
        String tableName = GuoStringUtil.replaceUpperToLower(className);
        createFile(map.get("mapper"), "/biz/system/dao/"+classNameLower+"/" ,className + "Mapper.java");
        createFile(map.get("bean"),"/biz/system/bean/" ,className + ".java");
        createFile(map.get("dao"), "/biz/system/dao/"+classNameLower+"/" , className + "Dao.java");
        createFile(map.get("service"), "/biz/system/service/", className + "Service.java");
        createFile(map.get("controller"), "/biz/system/controller/", className + "Controller.java");
        createFile(map.get("htmlListPage"), "/html/"+classNameLower+"/", "list.html");
        createFile(map.get("htmlAddAndEditPage"), "/html/"+classNameLower+"/", "edit.html");
        createFile(map.get("sqlFile"), "/sql/", tableName + ".sql");

        File file = new File(tempFolderPath);
        if(!file.exists()) GuoFileUtil.creatFolder(tempFolderPath);//判断目录是否存在，不存在就创建
        file = new File(folderPath);
        if(!file.exists()) GuoFileUtil.creatFolder(folderPath);//判断目录是否存在，不存在就创建
        String filePath = tempFolderPath+ tableName + new Date().getTime()+".zip";
        GuoFileUtil.createZip(folderPath,filePath);
        GuoFileUtil.delAllFile(folderPath);
        return filePath;
    }
    /**下面是代码模板**/
    /**获得实体类bean的模板*/
    public String createBean(CreateCode bean) throws IOException {
        String keyStr = bean.getKeyStr();//字段拼接
        String[] keys = keyStr.split(",");
        String valueStr = bean.getValueStr();//注释拼接
        String[] values = valueStr.split(",");
        String one = "";//字段
        String two = "";//get set 方法
        String three = "	@Override\r\n" +
                "	public String toString(){ \r\n" +
                "		return \""+bean.getClassName()+"{\" + \r\n";

        String string = "package top.javaguo.biz.system.bean;\r\n" +
                "\r\n" +
                "import top.javaguo.core.biz.bean.BaseBean;\r\n" +
                "\r\n" +
                "/**\r\n" +
                " * @describe "+bean.getDescribes()+"\r\n" +
                " * @author "+bean.getAuthor()+"\r\n" +
                " * @date "+bean.getCreateTime()+"\r\n" +
                " */\r\n" +
                "public class "+bean.getClassName()+" extends BaseBean{\r\n" +
                "\r\n" +
                "	private static final long serialVersionUID = 1L;\r\n" +
                "\r\n" +
                "	/** 非表字段属性 **/\r\n" +
                "	private String notFieldParams = \"serialVersionUID,notFieldParams\"; \r\n";
        for (int i = 0; i < keys.length; i++) {
            //生成基础字段
            one += "	/**"+values[i]+"**/ "+
                    "\r\n" +
                    "	private String "+keys[i]+"; \r\n  "
                    + "\r\n";
            //拼接get set 方法
            two += "	/**获得" + values[i] + "**/ \r\n" +
                    "	public String get"+GuoStringUtil.replaceUnderlineToUpper(keys[i], true)+"(){ \r\n" +
                    "		return "+keys[i] + ";\r\n" +
                    "	}\r\n \r\n" +
                    "	/**设置"+values[i]+"**/ \r\n" +
                    " 	public void set"+GuoStringUtil.replaceUnderlineToUpper(keys[i], true)+"(String "+keys[i]+"){ \r\n" +
                    " 		this."+keys[i] + " = "+keys[i]+" ;\r\n" +
                    "	} \r\n \r\n";
            //拼接toString
            if(keys[i].equals("id")) {
                three += "			\""+keys[i]+"='\"+" + keys[i] + "+'\\'' + \r\n";
            }else {
                three += "			\", "+keys[i]+"='\"+" + keys[i] + "+'\\'' + \r\n";
            }
        }
        two += "/** 获取非表字段属性 **/\n" +
                "\tpublic String getNotFieldParams() {\n" +
                "\t\treturn notFieldParams;\n" +
                "\t}\n" +
                "\n" +
                "\t";
        three += "			getToStringParam() + \r\n" +
                "		'}'; \r\n" +
                "	}" +
                ";\r\n" +
                "}";
        string = string +one +two + three;
        return string;
    }
    /**获得控制层模板controller的模板*/
    public String createController( CreateCode bean) throws IOException {
        String keyStr = bean.getKeyStr();//字段拼接
        String[] keys = keyStr.split(",");
        String valueStr = bean.getValueStr();//注释拼接
        String[] values = valueStr.split(",");
        String one = "";//字段
        String temp = "package top.javaguo.biz.system.controller;\r\n" +
                "\r\n" +
                "import java.util.Map;\r\n" +
                "\r\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\r\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\r\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\r\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\r\n" +
                "import org.springframework.web.bind.annotation.RestController;\r\n" +
                "\r\n" +
                "import top.javaguo.biz.system.bean." + bean.getClassName() + ";\r\n" +
                "import top.javaguo.biz.system.service." + bean.getClassName() + "Service;\r\n" +
                "import top.javaguo.core.biz.controller.BaseController;\r\n" +
                "import top.javaguo.core.resp.RespBean;\r\n" +
                "import top.javaguo.utils.GuoRespBeanUtil;\r\n" +
                "import top.javaguo.utils.GuoStringUtil;\r\n" +
                "import top.javaguo.core.util.SnowflakeIdWorkerUtil;\r\n" +
                "\r\n" +
                "/**\r\n" +
                " * @describe " + bean.getDescribes() + "\r\n" +
                " * @author " + bean.getAuthor() + "\r\n" +
                " * @date " + bean.getCreateTime() + "\r\n" +
                " */\r\n" +
                "@RestController\r\n" +
                "@RequestMapping(\"/system/"+bean.getClassNameLower()+"\")\r\n" +
                "public class " + bean.getClassName()+ "Controller extends BaseController<" + bean.getClassName() + ">{\r\n" +
                "\r\n" +
                "/**" + bean.getDescribes() + "**/\r\n" +
                "	@Autowired\r\n" +
                "	private " + bean.getClassName() +"Service " +bean.getClassNameLower() + "Service;" +
                "/**根据条件查询所有**/\r\n" +
                "	@GetMapping(\"/selectAll\")\r\n" +
                "	public RespBean<Map<String, Object>> selectAll(" + bean.getClassName() + " bean) { return " + bean.getClassNameLower() + "Service.selectAll(bean); }\r\n" +
                "\r\n" +
                "	/**\r\n" +
                "	 * 通过id删除\r\n" +
                "	 */\r\n" +
                "	@PostMapping(\"/deleteById\")\r\n" +
                "	public RespBean<Map<String, Object>> deleteById(String id) { \r\n" +
                "		if (GuoStringUtil.isEmpty(id)) {\r\n" +
                "			return GuoRespBeanUtil.initParamNotNullRespBean();\r\n" +
                "		}\r\n" +
                "		return returnIntercept(" + bean.getClassNameLower() + "Service.deleteById(id), " + bean.getClassNameLower() + "Service);\r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**\r\n" +
                "	 * 通过id查询\r\n" +
                "	 */\r\n" +
                "	@GetMapping(\"/selectById\")\r\n" +
                "	public RespBean<Map<String, Object>> selectById(" + bean.getClassName() + " bean) { \r\n" +
                "		if (GuoStringUtil.isEmpty(bean.getId())) {\r\n" +
                "			return GuoRespBeanUtil.initParamNotNullRespBean();\r\n" +
                "		}\r\n" +
                "		return " + bean.getClassNameLower() + "Service.selectById(bean); \r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**\r\n" +
                "	 * 通过ids集合删除\r\n" +
                "	 */\r\n" +
                "	@PostMapping(\"/deleteByIds\")\r\n" +
                "	public RespBean<Map<String, Object>> deleteByIds(String ids) { \r\n" +
                "		if (GuoStringUtil.isEmpty(ids)) {\r\n" +
                "			return GuoRespBeanUtil.initParamNotNullRespBean();\r\n" +
                "		}\r\n" +
                "		return returnIntercept(" + bean.getClassNameLower() + "Service.deleteByIds(ids), " + bean.getClassNameLower() + "Service); \r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**\r\n" +
                "	 * 添加\r\n" +
                "	 */\r\n" +
                "	@PostMapping(\"/insert\")\r\n" +
                "	public RespBean<Map<String, Object>> insert(" + bean.getClassName() + " bean) {\r\n" +
                "		 \r\n" +
                "		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());\r\n" +
                "		return returnIntercept(" + bean.getClassNameLower() + "Service.insert(bean), " + bean.getClassNameLower() + "Service);\r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**\r\n" +
                "	 * 通过id修改\r\n" +
                "	 */\r\n" +
                "	@PostMapping(\"/updateById\")\r\n" +
                "	public RespBean<Map<String, Object>> updateById(" + bean.getClassName() + " bean) {\r\n" +
                "		if(GuoStringUtil.isEmpty(new String[] { bean.getId() })){\r\n" +
                "			return GuoRespBeanUtil.initParamNotNullRespBean();\r\n" +
                "		}\r\n" +
                "		return returnIntercept(" + bean.getClassNameLower() + "Service.updateById(bean), " + bean.getClassNameLower() + "Service);\r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**LayUI根据条件查询所有**/\r\n" +
                "	@GetMapping(\"/selectAllForLayUI\")\r\n" +
                "	public Map<String, Object> selectAllForLayUI(" + bean.getClassName() + " bean) { return " + bean.getClassNameLower() + "Service.selectAllForLayUI(bean); }\r\n" +
                "\r\n" +
                "\r\n" +
                "}";
        return temp;
    }
    /**获得service层的模板*/
    public String createService( CreateCode bean) throws IOException {
        String temp = "package top.javaguo.biz.system.service;\r\n" +
                "\r\n" +
                "import java.util.Map;\r\n" +
                "\r\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\r\n" +
                "import org.springframework.stereotype.Service;\r\n" +
                "\r\n" +
                "import top.javaguo.biz.system.bean." + bean.getClassName() + ";\r\n" +
                "import top.javaguo.biz.system.dao." + bean.getClassNameLower() + "." + bean.getClassName() + "Dao;\r\n" +
                "import top.javaguo.core.biz.service.BaseService;\r\n" +
                "import top.javaguo.core.resp.RespBean;\r\n" +
                "\r\n" +
                "/**\r\n" +
                " * @describe " + bean.getDescribes() + "\r\n" +
                " * @author " + bean.getAuthor() + "\r\n" +
                " * @date " + bean.getCreateTime() + "\r\n" +
                " */\r\n" +
                "@Service\r\n" +
                "public class " + bean.getClassName() + "Service extends BaseService<" + bean.getClassName() + ">{\r\n" +
                "\r\n" +
                "	/**" + bean.getDescribes() + "**/\r\n" +
                "	@Autowired\r\n" +
                "	private " + bean.getClassName() + "Dao " + bean.getClassNameLower() + "Dao;\r\n" +
                "\r\n" +
                "	/**根据条件查询所有**/\r\n" +
                "	public RespBean<Map<String, Object>> selectAll(" + bean.getClassName() + " bean) { return getResult(" + bean.getClassNameLower() + "Dao, \"selectAll\", bean); }\r\n" +
                "\r\n" +
                "	/**LayUI根据条件查询所有**/\r\n" +
                "	public Map<String, Object> selectAllForLayUI(" + bean.getClassName() + " bean) { return getResult(" + bean.getClassNameLower() + "Dao, \"selectAllForLayUI\", bean).getData(); }\r\n" +
                "\r\n" +
                "	/**添加**/\r\n" +
                "	public RespBean<Map<String, Object>> insert(" + bean.getClassName() + " bean) { return getResult(" + bean.getClassNameLower() + "Dao, \"insert\", bean); }\r\n" +
                "\r\n" +
                "	/**通过id删除**/\r\n" +
                "	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(" + bean.getClassNameLower() + "Dao, \"deleteById\", id); }\r\n" +
                "\r\n" +
                "	/**通过id修改**/\r\n" +
                "	public RespBean<Map<String, Object>> updateById(" + bean.getClassName() + " bean) { return getResult(" + bean.getClassNameLower() + "Dao, \"updateById\", bean); }\r\n" +
                "\r\n" +
                "	/**通过id查询**/\r\n" +
                "	public RespBean<Map<String, Object>> selectById(" + bean.getClassName() + " bean) { return getResult(" + bean.getClassNameLower() + "Dao, \"selectById\", bean); }\r\n" +
                "\r\n" +
                "	/**通过ids集合删除**/\r\n" +
                "	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(" + bean.getClassNameLower() + "Dao, \"deleteByIds\", ids); }\r\n" +
                "\r\n" +
                "	/** 清空多对象缓存集合 **/\r\n" +
                "	@Override\r\n" +
                "	public void clearBeanManyCache() {\r\n" +
                "	}\r\n" +
                "\r\n" +
                "\r\n" +
                "}\r\n" +
                "";
        return temp;
    }
    /**获取dao层模板*/
    public String createDao( CreateCode bean) throws IOException {
        String className = bean.getClassName();
        String classNameLower = bean.getClassNameLower();
        String temp = " package top.javaguo.biz.system.dao." + classNameLower + ";\r\n" +
                "\r\n" +
                "import java.util.List;\r\n" +
                "import org.apache.ibatis.annotations.DeleteProvider;\r\n" +
                "import org.apache.ibatis.annotations.InsertProvider;\r\n" +
                "import org.apache.ibatis.annotations.Mapper;\r\n" +
                "import org.apache.ibatis.annotations.Param;\r\n" +
                "import org.apache.ibatis.annotations.SelectProvider;\r\n" +
                "import org.apache.ibatis.annotations.UpdateProvider;\r\n" +
                "import top.javaguo.biz.system.bean." + className + ";\r\n" +
                "import top.javaguo.core.biz.dao.BaseDao;\r\n" +
                "\r\n" +
                "/**\r\n" +
                " * @describe " + bean.getDescribes()+"\r\n" +
                " * @author " + bean.getAuthor() + "\r\n" +
                " * @date " + bean.getCreateTime() + "\r\n" +
                " */\r\n" +
                "@Mapper\r\n" +
                "public interface " + className + "Dao extends BaseDao<" + className + "> {\r\n" +
                "\r\n" +
                "	/**根据条件查询所有**/\r\n" +
                "	@SelectProvider(type=" + className + "Mapper.class,method = \"selectAll\")\r\n" +
                "	public List<" + className + "> selectAll(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**根据条件查询所有的个数**/\r\n" +
                "	@SelectProvider(type=" + className + "Mapper.class,method = \"selectTotal\")\r\n" +
                "	public int selectTotal(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**添加**/\r\n" +
                "	@InsertProvider(type=" + className + "Mapper.class,method = \"insert\")\r\n" +
                "	public int insert(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**通过id删除**/\r\n" +
                "	@DeleteProvider(type=" + className + "Mapper.class,method = \"deleteById\")\r\n" +
                "	public int deleteById(@Param(\"id\")String id);\r\n" +
                "\r\n" +
                "	/**通过id修改**/\r\n" +
                "	@UpdateProvider(type=" + className + "Mapper.class,method = \"updateById\")\r\n" +
                "	public int updateById(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**通过id查询**/\r\n" +
                "	@SelectProvider(type=" + className + "Mapper.class,method = \"selectById\")\r\n" +
                "	public " + className + " selectById(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**通过ids集合删除**/\r\n" +
                "	@DeleteProvider(type=" + className + "Mapper.class,method = \"deleteByIds\")\r\n" +
                "	public int deleteByIds(@Param(\"ids\")String ids);\r\n" +
                "\r\n" +
                "	/**根据关联条件查询所有**/\r\n" +
                "	@SelectProvider(type=" + className + "Mapper.class,method = \"selectAllForRelation\")\r\n" +
                "	public List<" + className + "> selectAllForRelation(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**根据关联条件查询所有的个数**/\r\n" +
                "	@SelectProvider(type=" + className + "Mapper.class,method = \"selectTotalForRelation\")\r\n" +
                "	public int selectTotalForRelation(@Param(\"bean\")" + className + " bean);\r\n" +
                "\r\n" +
                "	/**根据关联id查询**/\r\n" +
                "	@SelectProvider(type=" + className + "Mapper.class,method = \"selectByIdForRelation\")\r\n" +
                "	public " + className + " selectByIdForRelation(@Param(\"id\")String id);\r\n" +
                "\r\n" +
                "}\r\n" +
                "";
        return temp;
    }
    /**获取mapper层模板*/
    public String createMapper( CreateCode bean) throws IOException {
        String[] keys = bean.getKeyStr().split(",");//字段拼接
        String[] values = bean.getValueStr().split(",");//注释拼接
        String[] tableKeys = bean.getTableKeyStr().split(",");//数据库字段拼接
        String className = bean.getClassName();
        String classNameLower = bean.getClassNameLower();
        String one = "";
        String two = "";
        for (int i = 0; i < values.length; i++) {
            one += "			+ whereAddFilter(\"t." + tableKeys[i] + " = \" , bean.get"+GuoStringUtil.replaceUnderlineToUpper(keys[i], true)+"())\r\n" ;
            if(i == 0 ) {
                two += "		return \" t." +tableKeys[i]+ "\"\r\n";
            }else {
                two += 	"				+ \" , t."+tableKeys[i]+"\"\r\n" ;
            }

        }
        String temp = "package top.javaguo.biz.system.dao." + classNameLower + ";\r\n" +
                "\r\n" +
                "import org.apache.ibatis.annotations.Param;\r\n" +
                "import top.javaguo.biz.system.bean." + className + ";\r\n" +
                "import top.javaguo.core.biz.dao.BaseMapper;\r\n" +
                "\r\n" +
                "/**\r\n" +
                " * @describe "+bean.getDescribes()+"\r\n" +
                " * @author " + bean.getAuthor() + "\r\n" +
                " * @date " + bean.getCreateTime() +"\r\n" +
                " */\r\n" +
                "public class " + className + "Mapper extends BaseMapper<" + className + "> {\r\n" +
                "\r\n" +
                "	/**查询条件**/\r\n" +
                "	public String commonWhere(" + className + " bean) {\r\n" +
                "		return bean == null ? \"\" : \" WHERE 1 = 1 \"\r\n" +
                one +


                "			+ whereAddFilter(\"t.create_time >= \" , bean.getQueryBeginDate())\r\n" +
                "			+ whereAddFilter(\"t.create_time < \" , bean.getQueryEndDate())\r\n" +
                "			;\r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**所需关联查询字段**/\r\n" +
                "	private String getSelectFieldsForRelation() {\r\n" +
                two +
                "				;\r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**所需关联查询表名**/\r\n" +
                "	private String getSelectedTableNameForRelation() {\r\n" +
                "		return \" " + bean.getTableName() + " t \"\r\n" +
                "				;\r\n" +
                "	}\r\n" +
                "\r\n" +
                "	/**根据关联条件查询所有数据**/\r\n" +
                "	public String selectAllForRelation(@Param(\"bean\")" + className + " bean) { return getSelectSql(bean, \"all\", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }\r\n" +
                "\r\n" +
                "	/**根据关联条件查询所有数量**/\r\n" +
                "	public String selectTotalForRelation(@Param(\"bean\")" + className + " bean) { return getSelectSql(bean, \"total\", \"count(*)\", getSelectedTableNameForRelation()); }\r\n" +
                "\r\n" +
                "	/**根据关联id查询**/\r\n" +
                "	public String selectByIdForRelation(@Param(\"id\")String id) {\r\n" +
                "		" + className + " bean = new " + className + "();\r\n" +
                "		bean.setId(id);\r\n" +
                "		return getSelectSql(bean, \"all\", getSelectFieldsForRelation(), getSelectedTableNameForRelation());\r\n" +
                "	}\r\n" +
                "\r\n" +
                "\r\n" +
                "}\r\n" +
                "";
        return temp;
    }
    /**
     * 创建edit页面内容
     * @return
     */
    public String createEditHtml(CreateCode bean){

        String[] tableKeyLength = bean.getTableKeyLength().split(",");
        String[] keyStrArr = bean.getKeyStr().split(",");
        String[] values = bean.getValueStr().split(",");
        String editContent = "<!-- \n" +
                "\t@describe: "+bean.getDescribes()+"编辑\n" +
                "\t@author: "+bean.getAuthor()+" \n" +
                "\t@date: "+bean.getCreateTime()+" \n" +
                "-->\n" +
                "<!DOCTYPE html>\n" +
                "<head>\n" +
                "\t<meta charset='utf-8'>\n" +
                "\t<title>"+bean.getDescribes()+"编辑</title>\n" +
                "\t<meta name='keywords' content=''/>\n" +
                "\t<meta name='description' content='' />\n" +
                "\t<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0'/>\n" +
                "\t<meta name='renderer' content='webkit'/>\n" +
                "\t<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>\n" +
                "\t<link rel='stylesheet' href='../../source/layui/css/layui.css' media='all'/>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\t<div class='layui-form' style='padding: 20px 0 0 0;'>\n" +
                "\t\t<input type='hidden' name='id' class='layui-input'>\n";
        for(int i=4;i<keyStrArr.length;i++){
            editContent +=  "\t\t<div class='layui-form-item'>\n" +
                    "\t\t\t<label class='layui-form-label'>"+values[i]+"</label>\n" +
                    "\t\t\t<div class='layui-input-inline'>\n" +
                    "\t\t\t\t<input type='text' name='"+keyStrArr[i]+"' lay-verify='required' placeholder='请输入"+values[i]+"' autocomplete='off' class='layui-input' maxlength='"+tableKeyLength[i]+"' >\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n";
        }
        editContent+=
                "\t\t<div class='layui-form-item layui-hide'>\n" +
                        "\t\t\t<input type='button' lay-submit lay-filter='LAY-submit' id='LAY-submit' value='确认'>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "\t<script src='../../source/layui/layui.js'></script>\n" +
                        "\t<script>layui.use('form')</script>\n" +
                        "</body>\n" +
                        "</html>";

        return editContent;
    }

    /**
     * 创建list页面内容
     * @return
     */
    public String createListHtml(CreateCode bean){
        String[] keys = bean.getKeyStr().split(",");
        String[] values = bean.getValueStr().split(",");
        StringBuffer buffer = new StringBuffer();
        buffer.append("<!-- \n" +
                "\t@describe: "+bean.getDescribes()+"列表\n" +
                "\t@author: "+bean.getAuthor()+" \n" +
                "\t@date: "+bean.getCreateTime()+" \n" +
                "-->\n" +
                "<!DOCTYPE html>\n" +
                "<head>\n" +
                "\t<meta charset='utf-8'>\n" +
                "\t<title>"+bean.getDescribes()+"列表</title>\n" +
                "\t<meta name='keywords' content=''/>\n" +
                "\t<meta name='description' content=''/>\n" +
                "\t<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0'/>\n" +
                "\t<meta name='renderer' content='webkit'/>\n" +
                "\t<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>\n" +
                "\t<link rel='stylesheet' href='../../source/layui/css/layui.css' media='all'/>\n" +
                "\t<link rel='stylesheet' href='../../source/style/admin.css' media='all'/>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\t<div class='layui-fluid'>\n" +
                "\t\t<div class='layui-card'>\n" +
                "\t\t\t<div class='layui-form layui-card-header layuiadmin-card-header-auto'>\n" +
                "\t\t\t\t<div class='layui-form-item'>\n");

        for(int i=4;i<keys.length;i++){
            buffer.append("\t\t\t\t\t<div class='layui-inline'>\n" +
                    "\t\t\t\t\t\t<label class='layui-form-label'>"+values[i]+"</label>\n" +
                    "\t\t\t\t\t\t<div class='layui-input-block'>\n" +
                    "\t\t\t\t\t\t\t<input type='text' name='"+keys[i]+"' placeholder='请输入' autocomplete='off' class='layui-input'>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n");
        }
        buffer.append("\t\t\t\t\t<div class='layui-inline'>\n" +
                "\t\t\t\t\t\t<button class='layui-btn layuiadmin-btn' lay-submit lay-filter='LAY-search'>\n" +
                "\t\t\t\t\t\t\t<i class='layui-icon layui-icon-search layuiadmin-button-btn'></i>\n" +
                "\t\t\t\t\t\t</button>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t<div class='layui-card-body'>\n" +
                "\t\t\t<div id='layui-card-body-div-btn' style='padding-bottom: 10px;'></div>\n" +
                "\t\t\t<table id='LAY-table-list' lay-filter='LAY-table-list'></table>\n" +
                "\t\t\t<script type='text/html' id='table-tool-btn'></script>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t<script src='../../source/layui/layui.js'></script>\n" +
                "\t<script src='../../js/javaGuoJs/common.js'></script>\n" +
                "\t<script>\n" +
                "\t\tlayui.config({\n" +
                "\t\t\tbase: '../../source/' //静态资源所在路径\n" +
                "\t\t}).extend({\n" +
                "\t\t\tindex: 'lib/index' //主入口模块\n" +
                "\t\t}).use(['index' , 'table' , 'form'], function(){\n" +
                "\t\t\tvar $ = layui.$\n" +
                "\t\t\t,form = layui.form\n" +
                "\t\t\t,table = layui.table\n" +
                "\t\t\t,admin = layui.admin\n" +
                "\t\t\t,actionUrl = '/system/"+bean.getClassNameLower()+"/'\n" +
                "\t\t\t,roleResourceName = '"+bean.getDescribes()+"';\n" +
                "\n" +
                "\t\t\t//进入界面成功后可执行的方法，可用于初始化一些数据\n" +
                "\t\t\tvar successFunction = \n" +
                "\t\t\t{\n" +
                "\t\t\t\taddSuccess: function(layero, index){\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\teditSuccess: function(layero, index , obj){\n" +
                "\t\t\t\t\t//界面对象，用于获取界面中节点\n" +
                "\t\t\t\t\tvar $IC = layero.find('iframe').contents();\n"+
                "\t\t\t\t\t$IC.find('input[name=\"id\"]').val(obj.data.id);\n" +
                "\t\t\t\t\t$IC.find('input[name=\"isDeleted\"]').val(obj.data.isDeleted);\n");

        for(int i=4;i<keys.length;i++){
            buffer.append("\t\t\t\t\t$IC.find('input[name=\""+keys[i]+"\"]').val(obj.data."+keys[i]+");\n");
        }
        buffer.append("\t\t\t\t},\n" +
                "\t\t\t\tsubmitBefore: function(layero){\n" +
                "\t\t\t\t}\n" +
                "\t\t\t};\n" +
                "\n" +
                "\t\t\t//表格初始化列表json参数\n" +
                "\t\t\tvar tableRenderJson = {\n" +
                "\t\t\t\telem: '#LAY-table-list'\n" +
                "\t\t\t\t,url:  baseUrl  + actionUrl + 'selectAllForLayUI'\n" +
                "\t\t\t\t,where : paramAddInfo({})\n" +
                /* "\t\t\t\t,where : jsonAddToken({})\n" +*/
                "\t\t\t\t,cols: [[\n" +
                "\t\t\t\t\t{type: 'checkbox', fixed: 'left'}\n" +
                "\t\t\t\t\t,{title: '序号', width:70 , templet : function(d){return d.LAY_INDEX;}}\n"+
                "\t\t\t\t\t,{field: 'createTime', title: '创建时间'}\n" +
                "\t\t\t\t\t,{field: 'updateTime', title: '修改时间'}\n");
        for(int i=4;i<keys.length;i++){
            buffer.append("\t\t\t\t\t,{field: '"+keys[i]+"', title: '"+values[i]+"'}\n");
        }
        buffer.append("\t\t\t\t\t,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-tool-btn'}\n" +
                "\t\t\t\t]]\n" +
                "\t\t\t\t,request : {pageName: 'offset',limitName: 'limit'}\n" +
                "\t\t\t\t,text: {none:\"暂无数据\"}\n" +
                "\t\t\t\t,page: true\n" +
                "\t\t\t};\n" +
                "\n" +
                "\t\t\t//初始化页面基础功能\n" +
                "\t\t\tinitPageFunction($ , form , table , admin , actionUrl , successFunction , roleResourceName , tableRenderJson);\n" +
                "\n" +
                "\t\t\t//初始化列表数据\n" +
                "\t\t\ttable.render(tableRenderJson);\n" +
                "\t\t});\n" +
                "\t</script>\n" +
                "</body>\n" +
                "</html>");

        return buffer.toString();
    }


    /**
     * 创建mysql 新增数据表文本
     * @return
     */
    public String createSql(CreateCode bean){
        String[] tableKeyLengthArray = bean.getTableKeyLength().split(",");
        String[] tableKeyTypeArray = bean.getTableKeyType().split(",");
        String[] tableKeyStrArr = bean.getTableKeyStr().split(",");
        String[] valueStrArr = bean.getValueStr().split(",");
        String sqlStr = "SET FOREIGN_KEY_CHECKS=0;\n" +
                "DROP TABLE IF EXISTS `"+bean.getTableName()+"`;\n" +
                "CREATE TABLE `"+bean.getTableName()+"` (\n";
        sqlStr += "\t`id` varchar(18) NOT NULL COMMENT '编号',\n";
        for(int i=1;i<tableKeyStrArr.length;i++){
            sqlStr += "\t`"+tableKeyStrArr[i]+"` "+tableKeyTypeArray[i]+"("+tableKeyLengthArray[i]+") DEFAULT '"+
                    returnFieldDefaultValue(tableKeyTypeArray[i])+"' COMMENT '"+valueStrArr[i]+"',\n";
        }

        sqlStr += "\tPRIMARY KEY (`id`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '"+bean.getDescribes()+"';";

        return sqlStr;
    }
    /**
     * 通过字段类型返回默认值
     * @param field
     * @return
     */
    private String returnFieldDefaultValue(String field){
        if(field.indexOf("char") >=0)return "";
        if(field.indexOf("int")>=0)return "0";
        return null;
    }

}