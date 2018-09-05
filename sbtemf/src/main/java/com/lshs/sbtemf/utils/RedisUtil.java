package com.lshs.sbtemf.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis工具类
 * @author: LuShao
 * @create: 2018-08-31 10:14
 **/
public class RedisUtil {

	private RedisTemplate<String,Object> redisTemplate;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	//-------------------------Common-----------------------

	/**
	 * 指定缓存失效时间
	 * @param key
	 * @param time (s)
	 * @return
	 */
	public boolean expire(String key,long time){
		try {
			if (time>0){
				redisTemplate.expire(key,time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据key得到过期时间
	 * @param key
	 * @return
	 */
	public long getExpire(String key){
		return redisTemplate.getExpire(key);
	}

	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key){
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除key  可以是多个
	 * @param key
	 */
	public void delKey(String ...key){
		if(key!=null&&key.length>0){
			if (key.length==1){
				redisTemplate.delete(key[0]);
			}else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	//-------------------------String-----------------------

	/**
	 * 普通缓存获取
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return key==null?null:redisTemplate.opsForValue().get(key);
	}

	/**
	 * 普通缓存放入
	 * @param key
	 * @param o
	 * @return
	 */
	public boolean set(String key,Object o){
		try {
			redisTemplate.opsForValue().set(key,o);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 放入普通缓存并设置时间
	 * @param key
	 * @param o
	 * @param time
	 * @return
	 */
	public boolean set(String key,Object o,long time){
		try {
			redisTemplate.opsForValue().set(key,o,time,TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 递增
	 * @param key
	 * @param delta
	 * @return
	 */
	public long incr(String key,long delta){
		if (delta<0){
			throw new RuntimeException("递增因子必须大于0~~~");
		}
		return redisTemplate.opsForValue().increment(key,delta);
	}

	/**
	 * 递减
	 * @param key
	 * @param delta
	 * @return
	 */
	public long decr(String key,long delta){
		if (delta<0){
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key,-delta);
	}

	//-------------------------Map-----------------------

	/**
	 * 得到hashMap对象
	 * @param key
	 * @param item
	 * @return
	 */
	public Object hget(String key,String item){
		return redisTemplate.opsForHash().get(key,item);
	}

	/**
	 * 获取hashKey的所有键值
	 * @param key
	 * @return
	 */
	public Map<Object,Object> hmget(String key){
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 设置hashMap
	 * @param key
	 * @param map
	 * @return
	 */
	public boolean hmset(String key,Map<String,Object> map){
		try {
			redisTemplate.opsForHash().putAll(key,map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 设置 hashMap并设置时间
	 * @param key
	 * @param map
	 * @param time
	 * @return
	 */
	public boolean hmset(String key,Map<String,Object> map,long time){
		try {
			redisTemplate.opsForHash().putAll(key,map);
			if (time>0) expire(key,time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据，如果不存在则创建
	 * @param key
	 * @param item
	 * @param o
	 * @return
	 */
	public boolean hset(String key,String item,Object o){
		try {
			redisTemplate.opsForHash().put(key,item,o);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据，不存在则创建，如果设置时间则会覆盖原有的时间
	 * @param key
	 * @param item
	 * @param o
	 * @param time
	 * @return
	 */
	public boolean hset(String key,String item,Object o,long time){
		try {
			redisTemplate.opsForHash().put(key,item,o);
			if (time>0) expire(key,time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除hash表中的值
	 * @param key
	 * @param item
	 */
	public void hdel(String key,Object ...item){
		redisTemplate.opsForHash().delete(key,item);
	}

	/**
	 * 判断hash表中是否有该项的值
	 * @param key
	 * @param item
	 * @return
	 */
	public boolean hHasKey(String key,String item){
		return redisTemplate.opsForHash().hasKey(key,item);
	}

	/**
	 * hash递增，如果不存在就会新建一个并把新增后的值返回
	 * @param key
	 * @param item
	 * @param dy
	 * @return
	 */
	public double hincr(String key,String item,double dy){
		return redisTemplate.opsForHash().increment(key,item,dy);
	}

	/**
	 * hash递减
	 * @param key
	 * @param item
	 * @param dy
	 * @return
	 */
	public double hdecr(String key,String item,double dy){
		return redisTemplate.opsForHash().increment(key,item,-dy);
	}

//	--------------------------------set----------------------------

	/**
	 * 根据key获得set中所有的值
	 * @param key
	 * @return
	 */
	public Set<Object> sGet(String key){
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 根据o从set中查询是否存在
	 * @param key
	 * @param o
	 * @return
	 */
	public boolean sHasKey(String key,Object o){
		return redisTemplate.opsForSet().isMember(key,o);
	}

	/**
	 * 将数据放入set 返回插入多少个
	 * @param key
	 * @param o
	 */
	public long sSet(String key,Object ...o){
		try {
			return redisTemplate.opsForSet().add(key,o);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将数据放入set中，返回插入多少个，并设置时间
	 * @param key
	 * @param o
	 * @param time
	 * @return
	 */
	public long sSet(String key,Object o,long time){
		try {
			Long add = redisTemplate.opsForSet().add(key, o);
			if (time>0) expire(key,time);
			return add;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获得set缓存的长度
	 * @param key
	 * @return
	 */
	public long sGetSize(String key){
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 移除值为o的key
	 * @param key
	 * @param o
	 * @return
	 */
	public long setRemove(String key,Object ...o){
		return redisTemplate.opsForSet().remove(key,o);
	}

	//===============================list================================

	/**
	 * 根据key得到list
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List lGet(String key,long start,long end){
		return redisTemplate.opsForList().range(key,start,end);
	}

	/**
	 * 得到key的list长度
	 * @param key
	 * @return
	 */
	public long lSize(String key){
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 通过索引获得list中的值，index>=0 下标从0开始，index<0  从后往前取值，下标从-1开始
	 * @param key
	 * @param index
	 * @return
	 */
	public Object lGetByIndex(String key,long index){
		return redisTemplate.opsForList().index(key,index);
	}

	/**
	 * list放入缓存
	 * @param key
	 * @param o
	 * @return
	 */
	public boolean lset(String key,Object o){
		try {
			redisTemplate.opsForList().rightPush(key,o);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * list放入缓存，并设置时间
	 * @param key
	 * @param o
	 * @param time
	 * @return
	 */
	public boolean lset(String key,Object o,long time){
		try {
			redisTemplate.opsForList().rightPush(key,o);
			if (time>0) expire(key,time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * list放入缓存
	 * @param key
	 * @param lo
	 * @return
	 */
	public boolean lset(String key,List<Object> lo){
		try {
			redisTemplate.opsForList().rightPushAll(key,lo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * list放入缓存并设置时间
	 * @param key
	 * @param lo
	 * @param time
	 * @return
	 */
	public boolean lset(String key,List<Object> lo,long time){
		try {
			redisTemplate.opsForList().rightPushAll(key,lo);
			if (time>0) expire(key,time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据index修改list
	 * @param key
	 * @param index
	 * @param o
	 * @return
	 */
	public boolean lupdateByindex(String key,long index,Object o){
		try {
			redisTemplate.opsForList().set(key,index,o);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 移除n个o
	 * @param key
	 * @param count
	 * @param o
	 * @return
	 */
	public long lRemove(String key,long count,Object o){
		return redisTemplate.opsForList().remove(key,count,o);
	}



}
