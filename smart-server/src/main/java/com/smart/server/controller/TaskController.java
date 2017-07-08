package com.smart.server.controller;

import com.smart.server.task.AsyncTask;
import com.smart.server.util.BaseJsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * Author: gaowenming
 * Description:
 * Date: Created in 11:29 2017/7/8.
 */
@RestController
@RequestMapping("api/task/")
@Slf4j
@Api
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping(value = "/async", method = RequestMethod.GET)
    public BaseJsonResult<String> task1() throws Exception {
        BaseJsonResult<String> baseJsonResult = new BaseJsonResult<>();
        log.info("test async................");
        asyncTask.taskAsync1();
        asyncTask.taskAsync2();
        return baseJsonResult;

    }

}
