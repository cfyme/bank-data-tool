/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.lang3.StringUtils;
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
        for (int i = 0; i < 2000; i++) {
            System.out.println(i);
            executor.submit(new MyThread());

        }
        
        System.out.println("111111111111");

        Jedis jedis = RedisUtil.getJedis();
        
        System.out.println("2222222222222");

        String redisKey = DataConstant.redisKey;

        System.out.println(jedis.get(redisKey));

        System.out.println("99999999999");
        RedisUtil.returnResource(jedis);

    }


    static class MyThread extends Thread {
        @Override
        public void run() {

            Jedis jedis = RedisUtil.getJedis();

            String redisKey = DataConstant.redisKey;

            String redisValue = jedis.get(redisKey);


            if (StringUtils.isBlank(redisValue)) {
                jedis.incr(redisKey);
                jedis.expire(redisKey, 5);
            } else if (Integer.valueOf(redisValue) > 100) {
                System.out.println("more than 100");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else{
                jedis.incr(redisKey);
            }


            // 如果redis中的count大于限制的次数，则报错

            RedisUtil.returnResource(jedis);

        }
    }

}
