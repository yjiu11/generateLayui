package com.ptw.shiro.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ptw.shiro.pojo.SysRole;
import com.ptw.shiro.service.SysRoleService;
import com.yjiu.tools.PTWResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yjiu123
 * @since 2019-04-03
 */
@Controller
public class SysRoleController {
	@Autowired
	private SysRoleService thisService;
	@RequestMapping("/page/sysrole/list")
	public String toPage_ProjectList() {
		return "sysrole/list";
	}
	@RequestMapping("/page/sysrole/add")
	public String toPage_ProjectAdd(@RequestParam(value="id",required=false)String id,Model model) {
		if(StringUtils.isNotBlank(id)) {
			SysRole pro = thisService.selectOne(new EntityWrapper<SysRole>().eq("id", id));
			model.addAttribute("pro", pro);		 //如果是update，传过去
		}
		return "sysrole/add";
	}
	@RequestMapping("/sysrole/list/data")
	@ResponseBody
	public PTWResult data_ProjectList(@RequestParam(value="page",required=false,defaultValue="1") Integer page,
			@RequestParam(value="limit",required=false,defaultValue="10") Integer limit,
			@RequestParam(value="field",required=false,defaultValue="id") String field,
			@RequestParam(value="order",required=false,defaultValue="asc") String order,
			String role,String description) {
		Map<String,String> map = new HashMap<>();
		map.put("role",role);map.put("description",description);
		return thisService.selectByPage(new Page<>(page,limit),field,order,map);
	}
	
	/***
	 * 保存
	 * @param project
	 * @return
	 */
	@RequestMapping("/sysrole/save")
	@ResponseBody
	public PTWResult save(SysRole project) {
		try {
			System.out.println(project);
			thisService.insert(project);
			return PTWResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	
	@RequestMapping("/sysrole/update")
	@ResponseBody
	public PTWResult update(SysRole project) {
		try {
			thisService.update(project, new EntityWrapper<SysRole>().eq("id", project.getId()));
			return PTWResult.ok();
		}catch(Exception e) {
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	
	@RequestMapping("/sysrole/batchDel")
	@ResponseBody
	public PTWResult batchDel(String ids) {
		try {
			if(ids.contains(",")) {
				String[] idss = ids.split(",");
				thisService.deleteBatchIds(Arrays.asList(idss));
			}else {
				thisService.deleteById(Integer.parseInt(ids));
			}
			return PTWResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	@RequestMapping("/sysrole/get")
	@ResponseBody
	public PTWResult get(String id) {
		SysRole one = thisService.selectOne(new EntityWrapper<SysRole>().eq("id", id));
		return PTWResult.ok(one);
	}
}

