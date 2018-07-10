/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AntMerchantExpandIndirectModifyRequest;
import com.alipay.api.request.AntMerchantExpandIndirectQueryRequest;
import com.alipay.api.response.AntMerchantExpandIndirectModifyResponse;
import com.alipay.api.response.AntMerchantExpandIndirectQueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fshows.commons.constant.AlipayConstant;
import com.fshows.commons.model.AlipayAddressInfo;
import com.fshows.commons.model.AlipayBankcardInfo;
import com.fshows.commons.model.AlipayContactInfo;
import com.fshows.commons.model.AlipaySubMerchantCreateForm;
import com.fshows.commons.util.JsonUtil;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author caofy
 * @version AlipayMerchantQuery.java, v 0.1 2018-07-09 17:35 caofy
 * @desc:商户分级与信息修改
 */
public class AlipayMerchantModify {


    private static final Log logger = LogFactory.getLog(AlipayMerchantModify.class);


    public static void main(String[] args) throws Exception {
        executeYanBian();
        // executePa();
    }

    public static void executeYanBian() throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConstant.app_id, AlipayConstant.private_key, "json", "GBK", AlipayConstant.public_key, "RSA2");
        AntMerchantExpandIndirectModifyRequest request = new AntMerchantExpandIndirectModifyRequest();


//        request.setBizContent("{" +
//                "\"sub_merchant_id\":\"2088131859383926\"," +
//                "\"external_id\":\"20180708002950020453\"" +
//                //"\"org_pid\":\"2088001969784501\"" +
//                "  }");
        String content = getContent();

        logger.info("content=" + content);

        request.setBizContent(content);
        
        System.out.println("");
        System.out.println("------------------");

        AntMerchantExpandIndirectModifyResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("111111111111111");
            System.out.println("调用成功, resp=" + JSON.toJSONString(response));
        } else {
            System.out.println("调用失败");
        }
    }

    private static String getContent() throws Exception {
        AlipaySubMerchantCreateForm obj = new AlipaySubMerchantCreateForm();

        obj.setSubMerchantId("2088131859383926");
        obj.setExternalId("20180708002950020453");


        obj.setName("曹方毅延边银行测试2222222222");
        obj.setAliasName("cfyme007");
        obj.setCategoryId("2015050700000000");
        obj.setServicePhone("88210093");
        obj.setSource("2088621862443282");
        obj.setContactPhone("18042048687");

        List<AlipayAddressInfo> addressInfoList = Lists.newArrayList();
        AlipayAddressInfo addressInfo = new AlipayAddressInfo("330000", "330100", "330106", "丰盛九玺","BUSINESS_ADDRESS");
        addressInfoList.add(addressInfo);

        obj.setAddressInfo(addressInfoList);


        List<AlipayContactInfo> contactInfoList = Lists.newArrayList();
        //String name, String phone, String mobile, String email, String type, String idCardNo
        AlipayContactInfo alipayContactInfo = new AlipayContactInfo("cao", "18042049999", "", "cfyme@163.com", "LEGAL_PERSON", "");
        contactInfoList.add(alipayContactInfo);
        obj.setContactInfo(contactInfoList);

        obj.setBusinessLicense("123456");

        List<AlipayBankcardInfo> bankcardInfoList = Lists.newArrayList();
        AlipayBankcardInfo alipayBankcardInfo = new AlipayBankcardInfo("曹方毅", "6225885863980439");
        bankcardInfoList.add(alipayBankcardInfo);
        obj.setBankcardInfo(bankcardInfoList);

        String content = JsonUtil.toJSonByJackson(obj);

        return content;
    }


}
