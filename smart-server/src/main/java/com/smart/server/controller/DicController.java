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
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/dic/")
@Api
@Slf4j
public class DicController {

    @Autowired
    private IDicService dicService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseJsonResult<Dic> getDic(@PathVariable Integer id) throws Exception {
        BaseJsonResult<Dic> baseJsonResult = new BaseJsonResult<>();
        log.info("getDic.......");
        Dic dic = dicService.get(id);
        baseJsonResult.setData(dic);
        return baseJsonResult;

    }

    @RequestMapping(value = "/addDic", method = RequestMethod.POST)
    @TokenValidation
    public BaseJsonResult<Object> addtDic(@RequestBody Dic dic) throws Exception {
        BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
        log.info("addDic.......");
        dicService.save(dic);
        return baseJsonResult;
    }

    @RequestMapping(value = "/updateDic", method = RequestMethod.POST)
    @TokenValidation
    public BaseJsonResult<Object> updateDic(Integer id) throws Exception {
        BaseJsonResult<Object> baseJsonResult = new BaseJsonResult<>();
        return baseJsonResult;
    }
}
