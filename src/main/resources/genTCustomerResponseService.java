package com.yjiu.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.yjiu.pojo.TCustomerResponse;
import com.yjiu.tools.PTWResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yjiu123
 * @since 2019-04-03
 */
public interface TCustomerResponseService extends IService<TCustomerResponse> {
	//分页+排序+搜索
	PTWResult selectByPage(Page<TCustomerResponse> page,String field,String order, Map<String,String> searchFields);
}
