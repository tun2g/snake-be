package com.example.tram.config.redis;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRedisCacheService {

    void hSet(String hash, String key, String value);

    Object hGet(String hash,String key);

    void hDel(String hash,String key);
    List<Object> hValues(String hash);

    void set(String key,String value, int ttl);

    void set(String key, String value);

    Object get(String key);

    void del(String key);


}
