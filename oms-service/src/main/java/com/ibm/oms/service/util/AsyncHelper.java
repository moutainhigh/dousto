package com.ibm.oms.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author： mr.kai
 * @Description： 异步执行帮助类
 * @Date: 2018-03-06 10:17
 */
@Component
@EnableAsync
@Async(value = "taskExecutor")
public class AsyncHelper {

    private static final Logger logger = LoggerFactory.getLogger(AsyncHelper.class);

    @Async
    public <T> Future<T> call(Callable<T> job){
        try {
            return new AsyncResult<T>(job.call());
        } catch (Exception e) {
            logger.error("异步线程异常：", e.getMessage());
            return new AsyncResult<T>(null);
        }
    }

    public void run(Job job){ job.run();}

    public static interface Job{
        void run();
    }
}
