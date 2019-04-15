package com.ptw.shiro.service.impl;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ptw.shiro.mapper.SysRoleMapper;
import com.ptw.shiro.pojo.SysRole;
import com.ptw.shiro.service.SysRoleService;
import com.yjiu.tools.PTWResult;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yjiu123
 * @since 2019-04-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	@Override
	public PTWResult selectByPage(Page<SysRole> page, String field, String order,
			Map<String, String> searchFields) {
		boolean flag = StringUtils.equals(order, "desc")?false:true;
		EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
		Set<String> keys = searchFields.keySet();
		for (String k : keys) {
			if(!StringUtils.isEmpty(searchFields.get(k))) {
				System.out.println(k+":"+searchFields.get(k));
				wrapper.like(k, searchFields.get(k));
			}
		}
		wrapper.orderBy(field, flag);
		Page<SysRole> selectPage = this.selectPage(page, wrapper);
		System.out.println(selectPage.getTotal());
		return PTWResult.ok(selectPage.getRecords(),selectPage.getTotal());
	}

}
