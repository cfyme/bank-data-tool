/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import java.util.UUID;

/**
 * @author caofy
 * @version MyDataUtil.java, v 0.1 2018-07-07 21:07 caofy
 */
public class MyDataUtil {

    /**
     * 生成一个记录id(目前30位)
     * 20160809104636 02 234610196920
     * 14位日期 16位随机数字
     *
     * @return
     */
    public static String createDataId() {
        StringBuffer sb = new StringBuffer();
        // 获得时间字符串
        //sb.append(DateUtil.getNowDateTimeStr());
        sb.append("20180707666666");

        //获得6位随机数字
        sb.append(org.apache.commons.lang3.RandomStringUtils.randomNumeric(6));

        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        sb.append(String.format("%010d", hashCodeV));

        return sb.toString();
    }

}
