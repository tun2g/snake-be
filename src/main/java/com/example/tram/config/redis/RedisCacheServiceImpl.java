package com.example.tram.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheServiceImpl implements IRedisCacheService{

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void set(String key, String value, int ttl){
        redisTemplate.opsForValue().set(key,value,ttl, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    @Override
    public List<Object> hValues(String hash){
        return redisTemplate.opsForHash().values(hash);
    }

    @Override
    public void hDel(String hash,String key){
        redisTemplate.opsForHash().delete(hash,key);
    }

    @Override
    public void hSet(String hash,String key,String value){
        redisTemplate.opsForHash().put(hash,key,value);
    }

    @Override
    public Object hGet(String hash,String key){
        return redisTemplate.opsForHash().get(hash,key);
    }
}
