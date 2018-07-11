/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.alipay;

import com.alibaba.fastjson.JSON;
import com.fshows.commons.constant.BaseOnline;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.liquidation.DbTrans;
import com.fshows.commons.util.MyStringUtil;
import com.fshows.commons.util.OkHttpUtils;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version AipayMerchantCreate.java, v 0.1 2018-07-07 15:22 caofy
 * @desc :延边支付宝商户入驻
 */
public class AipayMerchantCreate {

    private static final Log logger = LogFactory.getLog(AipayMerchantCreate.class);



    public void execute(String storeId) {

        try {
            logger.info("11111111 merchant start ,storeId={}" + storeId);
            Map<String, Object> params = BaseOnline.getParamMap();
            params.put("method", "fshows.alipay.merchant.create");



            String sql = "select store_id,category_id,merchant_name from lp_liquidator_store where store_id='"+storeId+"'";

            Map<String, Object> map = DBBankOldUtilsTemplate.findFirst(sql);

            if(map == null){
                logger.info("lp_liquidator_store is null");
            }

            String categoryId = map.get("category_id")+"";
            String name = map.get("merchant_name")+"";

            Map<String, Object> content = Maps.newHashMap();
            content.put("store_id", storeId);

            content.put("category_id", categoryId);

            logger.info("merchant  before name="+name);
            name= MyStringUtil.trim(name);
            //name=name.replace("娱乐", "");

            logger.info("merchant  after name="+name);

            content.put("name", name);
            content.put("alias_name", name);

            params.put("content", JSON.toJSONString(content));
            params.put("sign", BaseOnline.getSign(params));

            logger.info("merchant end ,storeId={}" + storeId);


            System.err.println("请求参数：" + JSON.toJSONString(params));

            logger.info("请求参数：" + JSON.toJSONString(params));


            String response = OkHttpUtils.post(BaseOnline.host + "/gateway", params);
            System.err.println("请求结果：" + response);

            logger.info("请求结果：" + response);


        } catch (Exception e) {
            logger.info("AipayMerchantCreate error", e);
        }

    }


}
