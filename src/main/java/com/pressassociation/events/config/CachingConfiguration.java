package com.pressassociation.events.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 09/09/2014 16:09
 * <p/>
 * ****************************************************************************************
 */
@Configuration
@EnableCaching
public class CachingConfiguration implements CachingConfigurer {

  @Bean(destroyMethod="shutdown")
  public net.sf.ehcache.CacheManager ehCacheManager() {
    CacheConfiguration cacheConfiguration = new CacheConfiguration();
    cacheConfiguration.setName("queryCache");
    cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
    cacheConfiguration.setMaxEntriesLocalHeap(1000);
    cacheConfiguration.setTimeToLiveSeconds(300); // 5 minute timeout.

    net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
    config.addCache(cacheConfiguration);

    return net.sf.ehcache.CacheManager.newInstance(config);
  }

  @Bean
  @Override
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(ehCacheManager());
  }

  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator();
  }
}
