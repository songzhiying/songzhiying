package cn.duc.global.until;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	// @Override
	// public KeyGenerator keyGenerator() {
	// return new KeyGenerator() {
	//
	// @Override
	// public Object generate(Object target, Method method, Object... params) {
	// String cacheNameSpace =
	// PropertiesUtil.getString("redis.cache.namespace");
	// StringBuilder sb = new StringBuilder(cacheNameSpace);
	// CacheConfig cacheConfig =
	// target.getClass().getAnnotation(CacheConfig.class);
	// Cacheable cacheable = method.getAnnotation(Cacheable.class);
	// CachePut cachePut = method.getAnnotation(CachePut.class);
	// CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
	// if (cacheable != null) {
	// String[] cacheNames = cacheable.value();
	// if (cacheNames != null && cacheNames.length > 0) {
	// sb.append(cacheNames[0]);
	// }
	// } else if (cachePut != null) {
	// String[] cacheNames = cachePut.value();
	// if (cacheNames != null && cacheNames.length > 0) {
	// sb.append(cacheNames[0]);
	// }
	// } else if (cacheEvict != null) {
	// String[] cacheNames = cacheEvict.value();
	// if (cacheNames != null && cacheNames.length > 0) {
	// sb.append(cacheNames[0]);
	// }
	// }
	// if (cacheConfig != null && sb.toString().equals(cacheNameSpace)) {
	// String[] cacheNames = cacheConfig.cacheNames();
	// if (cacheNames != null && cacheNames.length > 0) {
	// sb.append(cacheNames[0]);
	// }
	// }
	// if (sb.toString().equals(cacheNameSpace)) {
	// sb.append(target.getClass().getName());
	// }
	// sb.append(":");
	// if (params != null) {
	// if (params.length == 1) {
	// if (params[0] instanceof Map) {
	//
	// }
	// }
	// }
	// return sb.toString();
	// }
	// };
	// }
}
