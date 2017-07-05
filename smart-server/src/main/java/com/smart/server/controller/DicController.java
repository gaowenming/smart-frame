package com.smart.server.controller;

import com.smart.model.Dic;
import com.smart.server.annotation.TokenValidation;
import com.smart.server.util.BaseJsonResult;
import com.smart.service.IDicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:DicController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2017年3月22日 上午10:43:59 <br/>
 * 
 * @author gaowenming
 * @version
 * @since JDK 1.6
 * @see
 */
	@RestController
	@RequestMapping("api/dic/")
	@Api(value = "api/dic/", description = "数据字典")
	@Slf4j
	public class DicController {

	@Autowired
	private IDicService dicService;

	@ApiOperation(value = "获取详细信息", notes = "根据id来获取详细信息")
	@ApiImplicitParam(name = "id", value = "字典ID", required = true, dataType = "int", paramType = "path")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BaseJsonResult<Dic> getDic(@PathVariable Integer id) throws Exception {
		BaseJsonResult<Dic> baseJsonResult = new BaseJsonResult<>();
		log.info("getDic.......");
		Dic dic = null;
		dic = dicService.get(id);
		baseJsonResult.setData(dic);
		return baseJsonResult;

	}

	@ApiOperation(value = "新增字典信息", notes = "新增字典信息")
	@RequestMapping(value = "/addDic", method = RequestMethod.POST)
	@TokenValidation
	public BaseJsonResult<Object> postDic(@RequestBody Dic dic) throws Exception {
		BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
		log.info("addDic.......");
		dicService.save(dic);
		return baseJsonResult;
	}
}
