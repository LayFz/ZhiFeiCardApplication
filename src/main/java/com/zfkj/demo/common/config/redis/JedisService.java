package com.zfkj.demo.common.config.redis;

import com.zfkj.demo.common.utils.SerializableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;
import java.util.Set;

/**
 * @Author lijunlin
 * @Description
 * @Date 2022/4/26 20:49
 **/
@Component
public class JedisService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 获取Jedis实例
     * @param
     * @return redis.clients.jedis.Jedis
     */
    public synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("获取Jedis资源异常:" + e.getMessage());
        }
    }

    /**
     * 释放Jedis资源
     * @param
     * @return void
     */
    public void closePool() {
        try {
            jedisPool.close();
        } catch (Exception e) {
            throw new RuntimeException("释放Jedis资源异常:" + e.getMessage());
        }
    }

    /**
     * 获取redis键值-object
     * @param key
     * @return java.lang.Object
     */
    public Object getObject(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            byte[] bytes = jedis.get(key.getBytes());
            if (Objects.nonNull(bytes) && bytes.length>0) {
                return SerializableUtil.unserializable(bytes);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取Redis键值getObject方法异常:key=" + key + " cause=" + e.getMessage());
        }
        return null;
    }

    /**
     * 设置redis键值-object
     * @param key
     * @param value
     * @return java.lang.String
     */
    public String setObject(String key, Object value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key.getBytes(), SerializableUtil.serializable(value));
        } catch (Exception e) {
            throw new RuntimeException("设置Redis键值setObject方法异常:key=" + key + " value=" + value + " cause=" + e.getMessage());
        }
    }

    /**
     * 设置redis键值-object-expiretime
     * @param key
     * @param value
     * @param expiretime
     * @return java.lang.String
     */
    public String setObject(String key, Object value, int expiretime) {
        String result;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.set(key.getBytes(), SerializableUtil.serializable(value));
            if ("OK".equals(result)) {
                jedis.expire(key.getBytes(), expiretime);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("设置Redis键值setObject方法异常:key=" + key + " value=" + value + " cause=" + e.getMessage());
        }
    }

    /**
     * 获取redis键值-Json
     * @param key
     * @return java.lang.Object
     */
    public String getJson(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            throw new RuntimeException("获取Redis键值getJson方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * 设置redis键值-Json
     * @param key
     * @param value
     * @return java.lang.String
     */
    public String setJson(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        } catch (Exception e) {
            throw new RuntimeException("设置Redis键值setJson方法异常:key=" + key + " value=" + value + " cause=" + e.getMessage());
        }
    }

    /**
     * 设置redis键值-Json-expiretime
     * @param key
     * @param value
     * @param expiretime
     * @return java.lang.String
     */
    public String setJson(String key, String value, int expiretime) {
        String result;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.set(key, value);
            System.out.println(result);
            if ("OK".equals(result)) {
                jedis.expire(key, expiretime);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("设置Redis键值setJson方法异常:key=" + key + " value=" + value + " cause=" + e.getMessage());
        }
    }

    /**
     * 删除key
     * @param key
     * @return java.lang.Long
     */
    public Long delKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("删除Redis的键delKey方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * key是否存在
     * @param key
     * @return java.lang.Boolean
     */
    public Boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("查询Redis的键是否存在exists方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * 模糊查询获取key集合(keys的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，生产不推荐使用)
     * @param key
     * @return java.util.Set<java.lang.String>
     */
    public Set<String> keysS(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(key);
        } catch (Exception e) {
            throw new RuntimeException("模糊查询Redis的键集合keysS方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * 模糊查询获取key集合(keys的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，生产不推荐使用)
     * @param key
     * @return java.util.Set<java.lang.String>
     */
    public Set<byte[]> keysB(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(key.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("模糊查询Redis的键集合keysB方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * 获取过期剩余时间
     * @param key
     * @return java.lang.String
     */
    public Long ttl(String key) {
        Long result = -2L;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.ttl(key);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("获取Redis键过期剩余时间ttl方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

}
