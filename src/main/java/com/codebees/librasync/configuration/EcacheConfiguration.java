package com.codebees.librasync.configuration;



import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class EcacheConfiguration extends CachingConfigurerSupport {


	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {

		CacheConfiguration catlogCache = new CacheConfiguration();
		catlogCache.setName("catalog-cache");
		catlogCache.setMemoryStoreEvictionPolicy("LRU");
		catlogCache.setMaxEntriesLocalHeap(1000);
		catlogCache.setTimeToLiveSeconds(60);

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(catlogCache);
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}