/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.yanbian;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBYanBianUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBYanbianTestUtilsTemplate;
import com.fshows.commons.model.LpLiquidatorStore;
import com.fshows.commons.model.LpLiquidatorStoreAuth;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyStringUtil;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author caofy
 * @version DbTransBankNewFromTest.java, v 0.1 2018-07-08 23:47 caofy
 * @desc:延边线上老的数据 复制  到测试环境
 */
public class YanbianAlipayAccount {

    static int pageSize = 10000;

    private static final Log logger = LogFactory.getLog(YanbianAlipayAccount.class);


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
        int loop = 0;
        for (LpLiquidatorStore obj : list) {
            loop++;
            String temp = "INSERT INTO fs_bank_liquidation.lp_liquidator_alipay_account (store_id, liquidator_id, platform_store_id, merchant_name, alias_name," +
                    " service_phone, contact_name, contact_phone, contact_mobile, contact_email," +
                    " category_id, memo, id_card_num, id_card_name, store_address," +
                    " province_code, city_code, district_code, business_license, business_license_type, " +
                    "alipid, card_no, card_name, contact_type, status, is_new, " +
                    "create_time, update_time)" +
                    " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', 1, 0, '%s', '%s')";

            LpLiquidatorStoreAuth auth = getStoreAuthByStoreId(obj.getStoreId());
            String storeId = MyStringUtil.trim(obj.getStoreId());
            String address = MyStringUtil.trim(auth.getStoreAddress());
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

            String sql = String.format(temp, storeId, obj.getLiquidatorId(),obj.getPlatformStoreId(),merchantName,aliasName,
                    obj.getServicePhone(),obj.getContactName(),obj.getContactPhone(),obj.getContactMobile(),email,
                    obj.getCategoryId(),memo, auth.getIdCardNum(), auth.getIdCardName(), address,
                    auth.getProvinceCode(),auth.getCityCode(),auth.getDistrictCode(),auth.getBusinessLicense(),auth.getBusinessLicenseType(),
                    obj.getAlipid(),auth.getCardNo(),auth.getIdCardName(),auth.getContactType(),createTime,updateTime);

            try {
                DBYanBianUtilsTemplate.update(sql);
                logger.info("loop="+loop);
            } catch (Exception e) {
                logger.error(e.getMessage()+", storeId="+storeId, e);
            }

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
                "FROM lp_liquidator_store_temp where id>"+maxId;
        List<LpLiquidatorStore> list = DBYanbianTestUtilsTemplate.find(LpLiquidatorStore.class, sql);
        return list;
    }

    public LpLiquidatorStoreAuth getStoreAuthByStoreId(String storeId) {
        String sql = "SELECT id, " +
                "store_id storeId," +
                " liquidator_id liquidatorId," +
                " id_card_num idCardNum, " +
                "id_card_name idCardName, " +
                "store_address storeAddress, " +
                "id_card_hand_img idCardHandImg, " +
                "    store_front_img storeFrontImg, " +
                "business_license_img businessLicenseImg, " +
                "create_time createTime, " +
                "update_time updateTime, " +
                "status, " +
                "province_code provinceCode, " +
                "    city_code cityCode," +
                " district_code districtCode, " +
                "business_license businessLicense," +
                " business_license_type businessLicenseType," +
                " card_no cardNo, " +
                "contact_type contactType " +
                "FROM lp_liquidator_store_auth_temp  where store_id='"+storeId+"'";
        LpLiquidatorStoreAuth obj = DBYanbianTestUtilsTemplate.findFirst(LpLiquidatorStoreAuth.class, sql);
        return obj;
    }

}
