package com.smart.server.controller;

import com.smart.model.Dic;
import com.smart.server.annotation.TokenValidation;
import com.smart.server.base.BaseController;
import com.smart.server.base.BaseJsonResult;
import com.smart.service.IDicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/dic/")
@Api
@Slf4j
public class DicController extends BaseController {

    @Autowired
    private IDicService dicService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseJsonResult<Dic> getDic(@PathVariable Integer id) throws Exception {
        log.info("getDic.......");
        Dic dic = dicService.get(id);
        return successResult(dic);
    }

    @RequestMapping(value = "/addDic", method = RequestMethod.POST)
    @TokenValidation
    public BaseJsonResult addDic(@RequestBody Dic dic) throws Exception {
        log.info("addDic.......");
        dicService.save(dic);
        return successNullDataResult();
    }

    @RequestMapping(value = "/updateDic", method = RequestMethod.POST)
    public BaseJsonResult updateDic(Integer id) throws Exception {
        return successNullDataResult();
    }
}
