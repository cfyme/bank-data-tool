/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.yanbian;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBYanBianUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBYanbianTestUtilsTemplate;
import com.fshows.commons.model.LpLiquidatorStore;
import com.fshows.commons.model.LpLiquidatorStoreAuth;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author caofy
 * @version DbTransBankNewFromTest.java, v 0.1 2018-07-08 23:47 caofy
 * @desc:延边线上老的数据 复制  到测试环境
 */
public class YanbianStoreTestToOnline {

    static int pageSize = 10000;

    private static final Log logger = LogFactory.getLog(YanbianStoreTestToOnline.class);


    public void execute(int maxId) {
        copyStore(maxId);
    }


    private void copyStore(int maxId) {
        int idx = 0;
        List<LpLiquidatorStore> list = getStoreList(maxId);
        System.out.println("list.size=" + list.size());
        batchInsert(list);
        logger.info("=========end==========idx=" + idx);

    }


    private void batchInsert(List<LpLiquidatorStore> list) {
        StringBuffer sb = new StringBuffer(10240);
        String sqlPrefix = "insert into lp_liquidator_store (store_id,bank_id,liquidator_store_id,liquidator_id," +
                "merchant_name,alias_name,service_phone,contact_name,contact_phone,contact_mobile," +
                "contact_email,category_id,branch,memo,is_new,create_time,update_time) values ";
        sb.append(sqlPrefix);
        String bankId = "1";
        int loop = 0;
        for (LpLiquidatorStore obj : list) {
            loop++;
            String memo = MyStringUtil.trim(obj.getMemo());
            String merchantName = MyStringUtil.trim(obj.getMerchantName());
            String aliasName = MyStringUtil.trim(obj.getAliasName());
            String branch = "";
            String createTime = DateUtil.getStringByMillis(obj.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            String updateTime = DateUtil.getStringByMillis(obj.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
            String email = obj.getContactEmail();
            if(email.length()>32){
                email = email.substring(0,31);
            }

            String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                    obj.getStoreId(), bankId, obj.getLiquidatorId(), obj.getLiquidatorId(),
                    merchantName, aliasName, obj.getServicePhone(), obj.getContactName(), obj.getContactPhone(), obj.getContactMobile(),
                    email, obj.getCategoryId(), branch, memo, obj.getIsNew(), createTime, updateTime
            );
            sb.append(tmp);


            if (loop % DataConstant.bachSize == 0) {
                sb.deleteCharAt(sb.length() - 1);
                DBYanBianUtilsTemplate.update(sb.toString());
                sb = new StringBuffer(10240);
                sb.append(sqlPrefix);
                System.out.println("loop=" + loop);
            }


        }

        sb.deleteCharAt(sb.length() - 1);

        try {
            DBYanBianUtilsTemplate.update(sb.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    public List<LpLiquidatorStore> getStoreList(int maxId) {
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
                "FROM lp_liquidator_store_temp WHERE  id>"+maxId;
        List<LpLiquidatorStore> list = DBYanbianTestUtilsTemplate.find(LpLiquidatorStore.class, sql);
        return list;
    }

}
