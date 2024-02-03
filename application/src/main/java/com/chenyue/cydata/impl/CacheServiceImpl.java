package com.chenyue.cydata.impl;


import com.chenyue.cydata.service.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: zzg
 * @date: 2024/1/30
 * @Description: zzg
 */

@Slf4j
//@Service
public class CacheServiceImpl implements CacheService {
    Cache<String, String> cache = CacheBuilder.newBuilder()
            .initialCapacity(5)  // 初始容量
            .maximumSize(10)     // 最大缓存数，超出淘汰
            .expireAfterWrite(60, TimeUnit.SECONDS) // 过期时间
            .build();
    @Override
    public  void tCache() throws ExecutionException {
        String orderId = String.valueOf(123456789);
        // 获取orderInfo，如果key不存在，callable中调用getInfo方法返回数据
        String orderInfo = cache.get(orderId, () -> getInfo(orderId));
//        String orderInfo =  getInfo(orderId);
        log.info("orderInfo = {}", orderInfo);
    }
    private static String getInfo(String orderId) {
        String info = "bb";
        // 当redis缓存不存在查db
        log.info("get data from mysql");
        info = String.format("{orderId=%s}", info);
        log.info("获取的值为:"+info);
        return info;
    }

}
