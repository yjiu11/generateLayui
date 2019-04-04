package com.yjiu.controller;


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
import com.yjiu.pojo.SysResource;
import com.yjiu.service.SysResourceService;
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
public class SysResourceController {
	@Autowired
	private SysResourceService thisService;
	@RequestMapping("/page/sysresource/list")
	public String toPage_ProjectList() {
		return "sysresource/list";
	}
	@RequestMapping("/page/sysresource/add")
	public String toPage_ProjectAdd(@RequestParam(value="sId",required=false)String sId,Model model) {
		if(StringUtils.isNotBlank(sId)) {
			SysResource pro = thisService.selectOne(new EntityWrapper<SysResource>().eq("s_id", sId));
			model.addAttribute("pro", pro);		 //如果是update，传过去
		}
		return "sysresource/add";
	}
	@RequestMapping("/sysresource/list/data")
	@ResponseBody
	public PTWResult data_ProjectList(@RequestParam(value="page",required=false,defaultValue="1") Integer page,
			@RequestParam(value="limit",required=false,defaultValue="10") Integer limit,
			@RequestParam(value="field",required=false,defaultValue="sid") String field,
			@RequestParam(value="order",required=false,defaultValue="asc") String order,
			String name,String permission) {
		Map<String,String> map = new HashMap<>();
		map.put("name", name);map.put("permission", permission);
		return thisService.selectByPage(new Page<>(page,limit),field,order,map);
	}
	
	/***
	 * 保存
	 * @param project
	 * @return
	 */
	@RequestMapping("/sysresource/save")
	@ResponseBody
	public PTWResult save(SysResource project) {
		try {
			System.out.println(project);
			thisService.insert(project);
			return PTWResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	
	@RequestMapping("/sysresource/update")
	@ResponseBody
	public PTWResult update(SysResource project) {
		try {
			thisService.update(project, new EntityWrapper<SysResource>().eq("s_id", project.getsId()));
			return PTWResult.ok();
		}catch(Exception e) {
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	
	@RequestMapping("/sysresource/batchDel")
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
	@RequestMapping("/sysresource/get")
	@ResponseBody
	public PTWResult get(String sid) {
		SysResource one = thisService.selectOne(new EntityWrapper<SysResource>().eq("s_id", sid));
		return PTWResult.ok(one);
	}
}

