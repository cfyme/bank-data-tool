/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import com.fshows.commons.constant.DataConstant;
import redis.clients.jedis.Jedis;

import javax.xml.crypto.Data;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author caofy
 * @version TestRedis.java, v 0.1 2018-07-08 17:53 caofy
 */
public class TestRedis {

    static final int DEFAULT_IO_THREADS = 10;
    public static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(DEFAULT_IO_THREADS,
            DEFAULT_IO_THREADS * 2, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
       // for (int i = 0; i < 20; i++) {
            //System.out.println(i);
           // executor.submit(new MyThread());

       // }

        Jedis jedis = RedisUtil.getJedis();

        String redisKey = DataConstant.redisKey;
        
        System.out.println(jedis.get(redisKey));
        
        System.out.println("99999999999");

    }


    static  class MyThread extends Thread {
        @Override
        public void run() {

            Jedis jedis = RedisUtil.getJedis();

            String redisKey = DataConstant.redisKey;
            
            // 设置在redis中的缓存，累加1
            long count = jedis.incr(redisKey);
            
            System.out.println("count="+count);


            // 如果该key不存在，则从0开始计算，并且当count为1的时候，设置过期时间
            if (count == 1) {
                jedis.expire(redisKey, 5);
            }

            // 如果redis中的count大于限制的次数，则报错
            if (count > 100) {
                System.out.println(count);
            }

            RedisUtil.returnResource(jedis);

        }
    }

}
