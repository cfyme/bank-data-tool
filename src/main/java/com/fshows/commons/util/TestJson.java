/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fshows.commons.model.AlipaySubMerchantCreateForm;

/**
 * @author caofy
 * @version TestJson.java, v 0.1 2018-07-10 16:24 caofy
 */
public class TestJson {

    public static void main(String[] args) {
        AlipaySubMerchantCreateForm obj = new AlipaySubMerchantCreateForm();
        obj.setName("test");
        obj.setCategoryId("33333");

        String content = null;
        try {
            content = JsonUtil.toJSonByJackson(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(content);
    }


}
