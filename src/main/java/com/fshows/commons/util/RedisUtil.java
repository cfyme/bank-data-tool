package com.fshows.commons.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * RedisUtil工具类
 * @author Administrator
 *
 */

public final class RedisUtil {

//    redis.host=101.37.78.57
//    redis.port=6699
//    redis.password=PbuFNjIt3iOUQdXKsjPcmR2oq0jlC3Lx6S1+Nfm9ZCc=
    
    //Redis服务器IP
   // private static String ADDR = "101.37.78.57";
    private static String ADDR = "127.0.0.1";

    //Redis的端口号
    private static int PORT = 6379;
    
    //访问密码
    //private static String AUTH = "PbuFNjIt3iOUQdXKsjPcmR2oq0jlC3Lx6S1+Nfm9ZCc=";
    private static String AUTH = "cfyme";

    //可用连接实例的最大数目，默认值为8；
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 30000;
    
    private static int TIMEOUT = 30000;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool = null;
    
    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setMinIdle(MAX_IDLE);//设置最小空闲数
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
//Idle时进行连接扫描
            config.setTestWhileIdle(true);
//表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(30000);
//表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(10);
//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(60000);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}