package com.chenyue.cydata.controller;

import com.chenyue.cydata.service.CacheService;
import com.chenyue.cydata.service.CaffeineServiceTest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author: zzg
 * @date: 2024/1/31
 * @Description: zzg
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    CaffeineServiceTest testService;

    @GetMapping("/test")
    public void test1(){
        log.info("进入方法");
        testService.tCache();
    }

}
