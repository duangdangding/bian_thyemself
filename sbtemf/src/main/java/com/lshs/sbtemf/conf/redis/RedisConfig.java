package com.lshs.sbtemf.conf.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lshs.sbtemf.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description: redis设置配置
 * @author: LuShao
 * @create: 2018-08-31 13:30
 **/
@Configuration
public class RedisConfig {

	/**
	 * 实例化 RedisTemplate 对象
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String,Object> functionDomainRedisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate,connectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式,并开启事务
	 * @param redisTemplate
	 * @param connectionFactory
	 */
	public void initDomainRedisTemplate(RedisTemplate<String,Object> redisTemplate,RedisConnectionFactory connectionFactory){
		//如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
		//GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
		// 使用Jackson2JsonRedisSerialize 替换默认序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		//redisTemplate.afterPropertiesSet();
		// 开启事务
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setConnectionFactory(connectionFactory);
	}

	/**
	 * 注入封装RedisTemplate
	 * @param redisTemplate
	 * @return
	 */
	@Bean(name="redisUtil")
	public RedisUtil redisUtil(RedisTemplate<String,Object> redisTemplate){
		RedisUtil redisUtil=new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		return redisUtil;
	}
}
