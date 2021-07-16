package com.huajie.thinking.in.spring.aop.features.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/15 14:58
 */
@EnableCaching
public class SpringCachingDemo {

  @Bean
  public CacheManager cacheManager(){
    CacheManager cacheManager = new ConcurrentMapCacheManager();
    return cacheManager;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
    configApplicationContext.register(SpringCachingDemo.class,UserImpl.class);
    configApplicationContext.refresh();

    UserService user = configApplicationContext.getBean(UserService.class);
    System.out.println(user.getUser("xwf"));
    configApplicationContext.close();
  }

}
