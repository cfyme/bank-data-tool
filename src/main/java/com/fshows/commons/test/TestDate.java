/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.test;

import com.fshows.commons.util.DateUtil;

/**
 * @author caofy
 * @version TestDate.java, v 0.1 2018-07-31 16:53 caofy
 */
public class TestDate {
    
    public static void main(String[] args){
        Long time = 1499652503796L;

        String format = "yyyy-MM-dd HH:mm:ss.SSS";
        String createTime = DateUtil.getStringByMillis(time, format);
        
        System.out.println(createTime);
    }
}
