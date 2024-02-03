package com.chenyue.cydata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



/**
 * @author: zzg
 * @date: 2024/1/30
 * @Description: zzg
 */
@EnableCaching
@SpringBootApplication
public class CydataAppliction {
    public static void main(String[] args) {
        SpringApplication.run(CydataAppliction.class, args);
    }
}
