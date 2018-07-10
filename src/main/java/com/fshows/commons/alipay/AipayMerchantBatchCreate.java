/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.alipay;

import com.alibaba.fastjson.JSON;
import com.fshows.commons.constant.BaseOnline;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.util.OkHttpUtils;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author caofy
 * @version AipayMerchantCreate.java, v 0.1 2018-07-07 15:22 caofy
 * @desc :延边支付宝商户入驻 (批量入驻)
 */
public class AipayMerchantBatchCreate {

    private static final Log logger = LogFactory.getLog(AipayMerchantBatchCreate.class);
    static final int DEFAULT_IO_THREADS = 20;
    public static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(DEFAULT_IO_THREADS,
            DEFAULT_IO_THREADS * 2, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());


    public void execute() {

        try {


            String sql = "select store_id,category_id from lp_liquidator_store where id>92424 and bank_id=5 and platform_store_id=''";

            List<Map<String, Object>> list = DBBankOldUtilsTemplate.find(sql);

            int size = list.size();
            int idx = 0;
            logger.info("mercant will aiipay merchant list.size=" + size);

            for (Map<String, Object> map : list) {
                idx ++;
                String storeId = map.get("store_id") + "";
                String category_id = map.get("category_id") + "";

                executor.submit(new AlipayMerchantThread(storeId,category_id, idx));

                logger.info("AipayMerchantCreate, idx="+idx+", total="+size);
            }


        } catch (Exception e) {
            logger.info("AipayMerchantCreate error", e);
        }

    }


    class AlipayMerchantThread extends Thread {

        public AlipayMerchantThread(String storeId, String categoryId, int idx) {
            this.storeId = storeId;
            this.categoryId=categoryId;
            this.idx=idx;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        private int idx;
        private String storeId;
        private String categoryId;

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        @Override
        public void run() {
            try {
                logger.info("merchant start ,storeId=" + storeId);
                Map<String, Object> params = BaseOnline.getParamMap();
                params.put("method", "fshows.alipay.merchant.create");
                Map<String, Object> content = Maps.newHashMap();
                content.put("store_id", storeId);
                content.put("category_id", categoryId);


                params.put("content", JSON.toJSONString(content));
                params.put("sign", BaseOnline.getSign(params));

                logger.info("merchant end ,storeId={}" + storeId+", idx="+idx);

                logger.info("请求参数：" + JSON.toJSONString(params));
                String response = OkHttpUtils.post(BaseOnline.host + "/gateway", params);
                logger.info("请求结果：" + response);
            } catch (Exception e) {
                logger.error("alipay merchant error, storeId=" + storeId, e);
            }


        }
    }


}
