package com.fw.mystique.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fw.mystique.wechat.service.ElkService;

@RestController
public class WechatController {
	
	public static final String SUCCESS = "success";
	
	public static final String METHOD_TEXT = "text";
	public static final String METHOD_IMAGE = "image";
	public static final String METHOD_TEMPLATE = "template";
	
	@Autowired
	private ElkService elkService;
	
	@RequestMapping(value="/text", method=RequestMethod.POST, consumes="application/json;charset=UTF-8",produces = "application/json;charset=UTF-8")
	public String text(@RequestBody JSONObject jsonParam) {
		elkService.sendMessage(this.METHOD_TEXT, jsonParam.toString());
		return this.SUCCESS;
	}
	
	@RequestMapping(value="/image", method=RequestMethod.POST, consumes="application/json;charset=UTF-8",produces = "application/json;charset=UTF-8")
	public String image(@RequestBody JSONObject jsonParam) {
		elkService.sendMessage(this.METHOD_IMAGE, jsonParam.toString());
		return this.SUCCESS;
	}
	
	@RequestMapping(value="/template", method=RequestMethod.POST, consumes="application/json;charset=UTF-8",produces = "application/json;charset=UTF-8")
	public String template(@RequestBody JSONObject jsonParam) {
		elkService.sendMessage(this.METHOD_TEMPLATE, jsonParam.toString());
		return this.SUCCESS;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return this.SUCCESS;
	}
}
