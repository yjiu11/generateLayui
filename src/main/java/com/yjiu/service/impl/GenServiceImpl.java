package com.yjiu.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ptw.shiro.pojo.SysRole;
import com.yjiu.service.GenService;
@Service
public class GenServiceImpl implements GenService {
	private static String currentClassName = "SysRole";					//需要修改
	private static String tableName = "sys_role";						//需要修改
	private static String javaId = "id";								//需要修改
	private static String dbId = "id";									//需要修改
	private static String getIdMethod = "getId";						//需要修改
	static Class<?> clz = new SysRole().getClass();						//需要修改
	private static String oldPackage = "com.ptw";						//需要时修改
	private static String newPackage = "com.ptw.gds";					//需要时修改
	
	//可以计算的变量
	private static String outPackage = "com\\ptw\\gds\\";				//如果改包，需要修改,涉及到pojo以及mapper是在生成的基础上修改
	private static String oldPackagePath = oldPackage.replaceAll("\\.", "/")+"/";
	private static String outPackagePath = "java\\" + outPackage;		
	private static String basePackage="com\\yjiu\\";				
	private static String basePackagePath="java\\"+basePackage;
	//生成的JAVA文件
	private static String genPath = "E:\\1YJIU\\Tools\\src\\";
	//根据SysResource所有文件去改变之前生成的JAVA文件
	private static String basePath = "E:\\1Dev\\WorkSpaces\\STS4.0\\generateLayui\\src\\main\\";
	private static File pojo = new File(genPath+oldPackagePath+"pojo\\"+currentClassName+".java");
	private static File mapper = new File(genPath+oldPackagePath+"mapper\\"+currentClassName+"Mapper.java");
	private static File mapper_xml = new File(genPath+oldPackagePath+"mapper\\"+currentClassName+"Mapper.xml");
	//获取SysResource相关文件
	private static File service = new File(basePath+basePackagePath+"service\\SysResourceService.java");
	private static File serviceImpl = new File(basePath+basePackagePath+"service\\impl\\SysResourceServiceImpl.java");
	private static File rest = new File(basePath+basePackagePath+"controller\\SysResourceController.java");
	private static File html_list = new File(basePath+"resources\\templates\\sysresource\\list_demo.html");
	private static File html_add = new File(basePath+"resources\\templates\\sysresource\\add_demo.html");
	private static String outPath = genPath+"resources\\gen\\";
	private static String old = "com.yjiu";
	private static List<String> searchField = new ArrayList<>();
	private static List<String> classField = new ArrayList<>();
	{//searchField写数据库的字段
		searchField.add("role");
		searchField.add("description");
		
		Field[] declaredFields = clz.getDeclaredFields();
		for (Field field : declaredFields) {
			if(!StringUtils.equals("serialVersionUID", field.getName())) {
				classField.add(field.getName());
			}
		}
	}
	@Override
	public String genPojo() throws Exception {
		String serviceStr = FileUtils.readFileToString(pojo);
		String serviceResult = serviceStr.replaceAll(oldPackage,newPackage).
										  replaceAll("public class "+currentClassName, "@TableName(\""+tableName+"\")\r\npublic class "+currentClassName).
										  replaceAll("import com.baomidou.mybatisplus.annotations.TableField;", 
												  "import com.baomidou.mybatisplus.annotations.TableName;\r\nimport com.baomidou.mybatisplus.annotations.TableField;");
		FileUtils.writeStringToFile(new File(outPath+currentClassName+".java"), serviceResult);
		return serviceResult;
	}
	@Override
	public String genMapper() throws Exception {
		String serviceStr = FileUtils.readFileToString(mapper);
		System.out.println(serviceStr);
		String serviceResult = serviceStr.replaceAll(oldPackage,newPackage).
										  replaceAll("public interface "+currentClassName+"Mapper", "@Mapper\r\npublic interface "+currentClassName+"Mapper").
										  replaceAll("import com.baomidou.mybatisplus.mapper.BaseMapper;", 
												  "import org.apache.ibatis.annotations.Mapper;\r\nimport com.baomidou.mybatisplus.mapper.BaseMapper;");
		FileUtils.writeStringToFile(new File(outPath+currentClassName+"Mapper.java"), serviceResult);
		return serviceResult;
	}
	@Override
	public String genMapper_XML() throws Exception {
		String serviceStr = FileUtils.readFileToString(mapper_xml);
		System.out.println(serviceStr);
		String serviceResult = serviceStr.replaceAll(oldPackage,newPackage).replaceAll("<cache type=\"org.mybatis.caches.ehcache.LoggingEhcache\"/>", "");
		FileUtils.writeStringToFile(new File(outPath+currentClassName+"Mapper.xml"), serviceResult);
		return serviceResult;
	}
	@Override
	public String genService() throws Exception {
		String serviceStr = FileUtils.readFileToString(service);
		String serviceResult = serviceStr.replaceAll(old,newPackage).replaceAll("SysResource", currentClassName);
		FileUtils.writeStringToFile(new File(outPath+currentClassName+"Service.java"), serviceResult);
		return serviceResult;
	}
	@Override
	public String genServiceImpl() throws Exception{
		String serviceStr = FileUtils.readFileToString(serviceImpl);
		String serviceResult = serviceStr.replaceAll(old,newPackage).replaceAll("SysResource", currentClassName);
		FileUtils.writeStringToFile(new File(outPath+currentClassName+"ServiceImpl.java"), serviceResult);
		return serviceResult;
	}
	@Override
	public String genController() throws Exception{
		String serviceStr = FileUtils.readFileToString(rest);
		StringBuffer searchParams = new StringBuffer();
		StringBuffer searchParamsMap = new StringBuffer();
		for (int i=0;i<searchField.size();i++) {
			if(i == searchField.size()-1) {
				searchParams.append("String "+searchField.get(i));
			}else {
				searchParams.append("String "+searchField.get(i)+",");
			}
			searchParamsMap.append("map.put(\""+searchField.get(i) +"\","+searchField.get(i)+");");
		}
		String serviceResult = serviceStr.replaceAll(old,newPackage).replaceAll("SysResource", currentClassName).
				replaceAll("sysresource/", currentClassName.toLowerCase()+"/").
				replaceAll("sId", javaId).replaceAll("s_id", dbId).
				replaceAll("sid", javaId).replaceAll("getsId", getIdMethod).
				replaceAll("String name,String permission", searchParams.toString()).
				replaceAll("map.put\\(\"name\", name\\);map.put\\(\"permission\", permission\\);", searchParamsMap.toString());
		FileUtils.writeStringToFile(new File(outPath+currentClassName+"Controller.java"), serviceResult);
		return serviceResult;
	}

	@Override
	public String genPageList() throws Exception{
		String serviceStr = FileUtils.readFileToString(html_list);
		StringBuffer searchParams1 = new StringBuffer();
		StringBuffer searchParams2 = new StringBuffer();
		StringBuffer searchParams3 = new StringBuffer();
		StringBuffer searchParams4 = new StringBuffer();
		for (int i=0;i<classField.size();i++) {
			searchParams2.append(",{field:'"+classField.get(i)+"', title: '"+classField.get(i)+"', sort: true}\r\n");
		}
		for (int i=0;i<searchField.size();i++) {
			searchParams3.append("var "+searchField.get(i)+" = \\$('#"+searchField.get(i)+"');\r\n");
			searchParams4.append(searchField.get(i)+":"+searchField.get(i)+".val(),\r\n");
			searchParams1.append(searchField.get(i)+":\r\n<div class='layui-inline'>\r\n");
			searchParams1.append("<input class='layui-input' name='"+searchField.get(i)+"' id='"+searchField.get(i)+"' autocomplete='off'>\r\n</div>\r\n");
		}
		String serviceResult = serviceStr.replaceAll(old,newPackage).replaceAll("sysresource/", currentClassName.toLowerCase()+"/").
				replaceAll("sId", javaId).replaceAll("s_id", dbId).
				replaceAll("\\{1\\}", searchParams1.toString()).
				replaceAll("\\{2\\}", searchParams2.toString()).
				replaceAll("\\{3\\}", searchParams3.toString()).
				replaceAll("\\{4\\}", searchParams4.toString());
		FileUtils.writeStringToFile(new File(outPath+currentClassName.toLowerCase()+"/list.html"), serviceResult);
		return serviceResult;
	}

	@Override
	public String genPageAdd() throws Exception{
		String serviceStr = FileUtils.readFileToString(html_add);
		StringBuffer searchParams = new StringBuffer();
		for (int i=0;i<classField.size();i++) {
			searchParams.append("<!-- 第"+(i+1)+"行 -->\r\n");
			searchParams.append("<div class=\"layui-row\">\r\n");
			searchParams.append("\t<div class=\"layui-col-md5 layui-col-md-offset3\">\r\n");
			searchParams.append("\t\t<div class=\"layui-form-item\">\r\n");
			searchParams.append("\t\t\t<label class=\"layui-form-label\">"+classField.get(i)+"</label>\r\n");
			searchParams.append("\t\t\t<div class=\"layui-input-block\" >\r\n");
			searchParams.append("\t\t\t\t<input type=\"text\" name=\""+classField.get(i)+"\" th:value=\"\\${pro?."+classField.get(i)+"}\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\""+classField.get(i)+"\" class=\"layui-input\">\r\n");
			searchParams.append("\t\t\t</div>\r\n");
			searchParams.append("\t\t</div>\r\n");
			searchParams.append("\t</div>\r\n");
			searchParams.append("</div>\r\n");
		}
		String serviceResult = serviceStr.replaceAll("sysresource/", currentClassName.toLowerCase()+"/").
				replaceAll("sId", javaId).
				replaceAll("\\{1\\}", searchParams.toString());
		FileUtils.writeStringToFile(new File(outPath+currentClassName.toLowerCase()+"/add.html"), serviceResult);
		return serviceResult;
	}
	
	
	
	
	
	
	@Override
	public void gen() throws Exception {
		try {
			this.genPojo();
			this.genMapper();
			this.genMapper_XML();
			this.genService();
			this.genServiceImpl();
			this.genController();
			this.genPageList();
			this.genPageAdd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		GenServiceImpl impl = new GenServiceImpl();
		impl.gen();
		String oldPackage = "com.ptw";
		String oldPackagePath = oldPackage.replaceAll("\\.", "/")+"/";
		//System.out.println(oldPackagePath);
		//impl.genPojo();
		//impl.genMapper();
		//impl.genController();
	}
}
