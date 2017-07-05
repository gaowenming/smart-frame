package com.test;

import com.smart.server.Application;
import com.smart.server.mail.SmartMailSender;
import com.smart.server.task.AsyncTask;
import com.smart.service.IDicService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private IDicService dicService;

    @Autowired
    private SmartMailSender smartMailSender;

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void getDic() throws Exception {
        System.out.println(dicService.get(6));
    }

    @Test
    public void testMail() throws Exception {
        smartMailSender.sendSimpleMail("459978392@qq.com", "测试邮件", "测试邮件内容");
    }

    @Test
    public void testTask() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncTask.task1();
        Future<String> task2 = asyncTask.task2();
        Future<String> task3 = asyncTask.task3();
        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @Test
    public void testTask1() throws Exception {
        System.out.println("start....");
        asyncTask.taskAsync1();
        asyncTask.taskAsync2();
        System.out.println("end.....");
    }


}