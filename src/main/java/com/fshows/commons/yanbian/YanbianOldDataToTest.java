/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.yanbian;

import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBYanbianTestUtilsTemplate;
import com.fshows.commons.model.LpLiquidatorStore;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author caofy
 * @version DbTransBankNewFromTest.java, v 0.1 2018-07-08 23:47 caofy
 * @desc:延边线上老的数据 复制  到测试环境
 */
public class YanbianOldDataToTest {

    static int pageSize = 10000;

    private static final Log logger = LogFactory.getLog(YanbianOldDataToTest.class);


    public void execute(int minId,int maxId) {
        //copyStore(377034, 543129);
        copyStore(minId, maxId);
    }


    private void copyStore(int minId, int maxId) {
        int idx = 0;

        int start = minId;
        while (true) {
            idx++;
            int end = start + pageSize;

            if (end >= maxId) {
                end=maxId;
            }

            logger.info("copyStore , start="+start+", end="+end + ",idx="+idx);


            List<LpLiquidatorStore> list = getStoreList(start, end);

            start = end;

            if (CollectionUtils.isEmpty(list)) {
                continue;
            } else {
                batchInsert(list);
                logger.info("store batchInsert sucesss, size=" + list.size());
            }

            if (start >= maxId) {
                break;
            }


        }

        logger.info("=========end==========idx="+idx);

    }


    private void batchInsert(List<LpLiquidatorStore> list) {
        StringBuffer sb = new StringBuffer(1024);
        String sqlPrefix = "insert into lp_liquidator_store_temp (store_id,bank_id,liquidator_store_id,liquidator_id, platform_store_id," +
                "merchant_name,alias_name,service_phone,contact_name,contact_phone,contact_mobile," +
                "contact_email,category_id,memo,alipid,is_new," +
                "create_time,update_time) values";
        sb.append(sqlPrefix);



        for (LpLiquidatorStore obj : list) {

            String memo = MyStringUtil.trim(obj.getMemo());
            String merchantName = MyStringUtil.trim(obj.getMerchantName());
            String aliasName = MyStringUtil.trim(obj.getAliasName());

            String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                    obj.getStoreId(), obj.getBankId(), obj.getLiquidatorStoreId(), obj.getLiquidatorId(), obj.getPlatformStoreId(),
                    merchantName, aliasName, obj.getServicePhone(), obj.getContactName(), obj.getContactPhone(), obj.getContactMobile(),
                    obj.getContactEmail(), obj.getCategoryId(),memo , obj.getAlipid(), obj.getIsNew(),
                    obj.getCreateTime(),obj.getUpdateTime()
            );
            sb.append(tmp);


        }

        sb.deleteCharAt(sb.length() - 1);

        try {
            logger.info("111111111");
            DBYanbianTestUtilsTemplate.update(sb.toString());
            logger.info("222222222");

        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }


    public List<LpLiquidatorStore> getStoreList(int start, int end) {
        String sql = "SELECT  id, " +
                "store_id storeId, " +
                "bank_id bankId, " +
                "liquidator_store_id liquidatorStoreId, " +
                "liquidator_id liquidatorId," +
                " platform_store_id platformStoreId, " +
                "merchant_name merchantName, " +
                "alias_name aliasName, " +
                "service_phone servicePhone," +
                " contact_name contactName," +
                " contact_phone contactPhone," +
                " contact_mobile contactMobile, " +
                "contact_email contactEmail, " +
                " category_id categoryId," +
                " memo," +
                " balance," +
                " status ," +
                " create_time createTime," +
                " update_time updateTime," +
                " alipid," +
                "is_new isNew  " +
                "FROM lp_liquidator_store WHERE id >=" + start + " and id<" + end + " and liquidator_id='20160921085633894'";
        List<LpLiquidatorStore> list = DBBankOldUtilsTemplate.find(LpLiquidatorStore.class, sql);
        return list;
    }


}
