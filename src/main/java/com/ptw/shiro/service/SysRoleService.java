package com.ptw.shiro.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ptw.shiro.pojo.SysRole;
import com.yjiu.tools.PTWResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yjiu123
 * @since 2019-04-03
 */
public interface SysRoleService extends IService<SysRole> {
	//分页+排序+搜索
	PTWResult selectByPage(Page<SysRole> page,String field,String order, Map<String,String> searchFields);
}
