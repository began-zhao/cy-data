package com.chenyue.cydata.impl;

import com.chenyue.cydata.service.CacheService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: zzg
 * @date: 2024/2/3
 * @Description: zzg
 */
@Service
@Slf4j
public class EhcacheServiceImpl implements CacheService {

    Cache<String, String> cache = Caffeine.newBuilder()
            .initialCapacity(5)
            // 超出时淘汰
            .maximumSize(10)
            //设置写缓存后n秒钟过期
            .expireAfterWrite(60, TimeUnit.SECONDS)
            //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
            //.expireAfterAccess(17, TimeUnit.SECONDS)
            .build();
    @Override
    public  void tCache(){
        String orderId = String.valueOf(123456789);
        // 获取orderInfo，如果key不存在，callable中调用getInfo方法返回数据
        String orderInfo = cache.get(orderId, key -> getInfo(orderId));
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
