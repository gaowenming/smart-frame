package com.smart.server.controller;

import com.smart.server.base.BaseController;
import com.smart.server.base.BaseJsonResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaowenming
 * @create 2017-07-31 21:11
 * @desc 动态设置日志级别
 **/
@RestController
@RequestMapping("api/log/")
@Slf4j
@Api
public class LogLevelController extends BaseController {

    @RequestMapping(value = "/mylog", method = RequestMethod.GET)
    public BaseJsonResult task1() throws Exception {
        log.debug("debug log...");
        log.info("info log...");
        log.warn("warn log...");
        log.error("error log...");
        return successNullDataResult();

    }

}
