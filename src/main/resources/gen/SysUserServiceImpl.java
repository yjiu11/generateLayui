package com.yjiu.service.impl;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yjiu.mapper.SysUserMapper;
import com.yjiu.pojo.SysUser;
import com.yjiu.service.SysUserService;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	@Override
	public PTWResult selectByPage(Page<SysUser> page, String field, String order,
			Map<String, String> searchFields) {
		boolean flag = StringUtils.equals(order, "desc")?false:true;
		EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
		Set<String> keys = searchFields.keySet();
		for (String k : keys) {
			if(!StringUtils.isEmpty(searchFields.get(k))) {
				System.out.println(k+":"+searchFields.get(k));
				wrapper.like(k, searchFields.get(k));
			}
		}
		Page<SysUser> selectPage = this.selectPage(page, wrapper);
		wrapper.orderBy(field, flag);
		System.out.println(selectPage.getTotal());
		return PTWResult.ok(selectPage.getRecords(),selectPage.getTotal());
	}

}
