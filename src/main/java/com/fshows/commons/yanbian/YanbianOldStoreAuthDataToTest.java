/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.yanbian;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBYanbianTestUtilsTemplate;
import com.fshows.commons.model.LpLiquidatorStoreAuth;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author caofy
 * @version DbTransBankNewFromTest.java, v 0.1 2018-07-08 23:47 caofy
 * @desc:延边线上老的数据 复制  到测试环境
 */
public class YanbianOldStoreAuthDataToTest {

    static int pageSize = 10000;

    private static final Log logger = LogFactory.getLog(YanbianOldStoreAuthDataToTest.class);


    public void execute(String minId, String maxId) {
        //copyStoreAuth("201807076666660000130976337302", "201807271517168076892046618008");
        copyStoreAuth(minId, maxId);
    }


    private void copyStoreAuth(String minId, String maxId) {
        int idx = 0;
        List<LpLiquidatorStoreAuth> list = getStoreAuthList(minId, maxId);
        System.out.println("list.size="+list.size());
        batchInsert(list);
        logger.info("=========end==========idx=" + idx);

    }


    private void batchInsert(List<LpLiquidatorStoreAuth> list) {
        StringBuffer sb = new StringBuffer(1024);

        String sqlPrefix = "insert into lp_liquidator_store_auth_temp (store_id,liquidator_id, id_card_num, id_card_name,store_address," +
                "id_card_hand_img,store_front_img,business_license_img," +
                "status, province_code,city_code,district_code," +
                "business_license,business_license_type,card_no,contact_type," +
                "create_time,update_time) values";

        sb.append(sqlPrefix);

        int loop=0;
        for (LpLiquidatorStoreAuth obj : list) {
            loop++;
            String address = MyStringUtil.trim(obj.getStoreAddress());

            String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                    obj.getStoreId(), obj.getLiquidatorId(), obj.getIdCardNum(), obj.getIdCardName(), address,
                    obj.getIdCardHandImg(),obj.getStoreFrontImg(),obj.getBusinessLicenseImg(),
                    obj.getStatus(), obj.getProvinceCode(), obj.getCityCode(), obj.getDistrictCode(),
                    obj.getBusinessLicense(), obj.getBusinessLicenseType(), obj.getCardNo(), obj.getContactType(),
                    obj.getCreateTime(),obj.getUpdateTime()
            );
            sb.append(tmp);



            if (loop % DataConstant.bachSize == 0) {
                sb.deleteCharAt(sb.length() - 1);
                DBYanbianTestUtilsTemplate.update(sb.toString());
                sb = new StringBuffer(10240);
                sb.append(sqlPrefix);
                System.out.println("loop="+loop);
            }
            

            
        }

        sb.deleteCharAt(sb.length() - 1);

        try {
            DBYanbianTestUtilsTemplate.update(sb.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    public List<LpLiquidatorStoreAuth> getStoreAuthList(String start, String end) {
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
                "FROM lp_liquidator_store_auth WHERE id >='" + start + "' and id<'" + end + "' and liquidator_id='20160921085633894'";
        List<LpLiquidatorStoreAuth> list = DBBankOldUtilsTemplate.find(LpLiquidatorStoreAuth.class, sql);
        return list;
    }


}
