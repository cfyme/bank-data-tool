/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caofy
 * @version AlipaySignUtil.java, v 0.1 2018-07-08 00:17 caofy
 */
public class AlipaySignUtil {

    public static String createSign(Map<String, Object> params, String prikey) {

        // map类型转换
        Map<String, String> map = new HashMap<>(params.size());
        for (String s : params.keySet()) {
            Object o = params.get(s);
            if (o != null) {
                map.put(s, o.toString());
            }
        }
        map.remove("sign");
        map.remove("sign_type");

        try {
            return AlipaySignature.rsaSign(map, prikey, "utf-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

}
