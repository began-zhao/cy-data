package com.chenyue.cydata.impl;

import com.chenyue.cydata.service.CaffeineServiceTest;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: zzg
 * @date: 2024/2/3
 * @Description: zzg
 */
@Slf4j
@Service
public class CaffeineServiceTestImpl implements CaffeineServiceTest {

    private static final String ORDER_CACHE = "orderCache";
    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            // 创建cache实例
            .withCache(ORDER_CACHE, CacheConfigurationBuilder
                    // 声明一个容量为20的堆内缓存
                    .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(20)))
            .build(true);
    // 获取cache实例
    Cache<String, String> cache = cacheManager.getCache(ORDER_CACHE, String.class, String.class);
    @Override
    public  void tCache(){
        String orderId = String.valueOf(123456789);
        // 获取orderInfo，如果key不存在，callable中调用getInfo方法返回数据
        String orderInfo = cache.get(orderId);
        if (StringUtils.isBlank(orderInfo)) {
            orderInfo = getInfo(orderId);
            cache.put(orderId, orderInfo);
        }
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
