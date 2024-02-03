package com.chenyue.cydata.service;

import org.springframework.cache.annotation.Cacheable;

import java.util.concurrent.ExecutionException;

/**
 * @author: zzg
 * @date: 2024/1/30
 * @Description: zzg
 */
public interface CacheService {
     void tCache() throws ExecutionException;
}
