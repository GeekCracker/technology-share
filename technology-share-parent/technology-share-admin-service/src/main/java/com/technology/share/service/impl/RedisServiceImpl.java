package com.technology.share.service.impl;

import com.technology.share.domain.Redis;
import com.technology.share.mapper.RedisMapper;
import com.technology.share.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

@Service("AdminRedisService")
public class RedisServiceImpl extends BaseServiceImpl<RedisMapper, Redis> implements RedisService {

    private volatile Jedis jedis;

    @Override
    public List<Map<String, Object>> queryRedisData(Map<String, Object> queryParam) {
        List<Map<String,Object>> list = new LinkedList<>();
        Object host = queryParam.get("host");
        Object port = queryParam.get("port");
        Object password = queryParam.get("password");
        if(host != null && port != null){
            Jedis jedis = new Jedis(String.valueOf(host),Integer.valueOf(String.valueOf(port)));
            if(password != null){
                jedis.auth(String.valueOf(password));
            }
            Object db = queryParam.get("db");
            if(db != null){
                jedis.select(Integer.valueOf(String.valueOf(db)));
            }
            this.jedis = jedis;
            Set<String> keys = jedis.keys("*");
            if(keys != null && !keys.isEmpty()){
                for(String key : keys){
                    Map<String,Object> map = new LinkedHashMap<>();
                    String type= jedis.type(key);
                    map.put("key",key);
                    map.put("type",type);
                    map.put("persist",jedis.persist(key));
                    list.add(map);
                }
            }
        }
        return list.stream().sorted(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return String.valueOf(o1.get("key")).hashCode() - String.valueOf(o2.get("key")).hashCode();
            }
        }).collect(Collectors.toList());
    }
}
