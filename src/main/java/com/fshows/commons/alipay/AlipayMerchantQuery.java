/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AntMerchantExpandIndirectQueryRequest;
import com.alipay.api.response.AntMerchantExpandIndirectQueryResponse;
import com.fshows.commons.constant.AlipayConstant;

import javax.xml.bind.util.JAXBSource;

/**
 * @author caofy
 * @version AlipayMerchantQuery.java, v 0.1 2018-07-09 17:35 caofy
 */
public class AlipayMerchantQuery {

    public static void main(String[] args) throws  Exception{
        //executeYanBian();
       executePa();
    }

    public static void executeYanBian() throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConstant.app_id, AlipayConstant.private_key, "json", "GBK", AlipayConstant.public_key, "RSA2");
        AntMerchantExpandIndirectQueryRequest request = new AntMerchantExpandIndirectQueryRequest();
        request.setBizContent("{" +
                "\"sub_merchant_id\":\"2088131859383926\"," +
                "\"external_id\":\"20180708002950020453\"" +
                //"\"org_pid\":\"2088001969784501\"" +
                "  }");
        AntMerchantExpandIndirectQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("111111111111111");
            System.out.println("调用成功, resp="+ JSON.toJSONString(response));
        } else {
            System.out.println("调用失败");
        }
    }

    public static void executePa() throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConstant.pa_app_id, AlipayConstant.pa_private_key, "json", "GBK", "", "RSA");
        AntMerchantExpandIndirectQueryRequest request = new AntMerchantExpandIndirectQueryRequest();
        request.setBizContent("{" +
                "\"sub_merchant_id\":\"2088721797369737\"," +
                "\"external_id\":\"20170811100649025614\"" +
                //"\"org_pid\":\"2088001969784501\"" +
                "  }");
        AntMerchantExpandIndirectQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功, resp="+ JSON.toJSONString(response));

        } else {
            System.out.println("调用失败");
        }
    }
}
