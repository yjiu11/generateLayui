package com.yjiu.service;

public interface GenService {
	/**一次生成*/
	void gen()throws Exception;
	String genPojo() throws Exception;
	String genMapper() throws Exception;
	String genMapper_XML() throws Exception;
	//生成Service，里面有分页+搜索+排序
	String genService() throws Exception;
	//生成Service，里面有分页+搜索+排序
	String genServiceImpl() throws Exception;
	//生成Controller,跳转页面+业务处理
	String genController() throws Exception;
	//生成页面，页面表
	String genPageList() throws Exception;
	//生成页面，添加页
	String genPageAdd() throws Exception;
}
