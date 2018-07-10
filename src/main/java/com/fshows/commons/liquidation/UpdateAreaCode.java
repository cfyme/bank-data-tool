/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version UpdateAreaCode.java, v 0.1 2018-07-10 00:02 caofy
 */
public class UpdateAreaCode {

    private static final Log logger = LogFactory.getLog(UpdateAreaCode.class);

    public void execute() {
        String sql = "select * from lp_liquidator_store_auth\n" +
                "where store_id IN (select store_id from lp_liquidator_store\n" +
                "where id>=350000 AND liquidator_id='20160921085633894' AND platform_store_id ='')";

        List<Map<String, Object>> maps = DBBankOldUtilsTemplate.find(sql);

        for (Map<String, Object> map : maps) {

            String storeId = map.get("store_id") + "";
            String cityCode = map.get("city_code") + "";



                sql = "update lp_liquidator_store_auth set province_code='330000',city_code='330100',district_code='330110' where store_id='" + storeId + "'";

                DBBankOldUtilsTemplate.update(sql);
                logger.info("update area success, storeId="+storeId );



        }

        logger.info("update area code end ,size="+maps.size());
    }

    public void execute2() {
        String sql = "select * from lp_liquidator_store_auth\n" +
                "where store_id IN (select store_id from lp_liquidator_store\n" +
                "where id>=350000 AND liquidator_id='20160921085633894' AND platform_store_id ='')";

        List<Map<String, Object>> maps = DBBankOldUtilsTemplate.find(sql);

        for (Map<String, Object> map : maps) {

            String storeId = map.get("store_id") + "";
            String cityCode = map.get("city_code") + "";


            //district_code

            sql = "SELECT * FROM lp_area WHERE area_parent_id='" + cityCode + "' LIMIT 1";

            Map<String, Object> areaMap = DBBankOldUtilsTemplate.findFirst(sql);

            if (areaMap != null) {
                String districtCode = areaMap.get("area_code") + "";

                sql = "update lp_liquidator_store_auth set district_code='" + districtCode + "' where store_id='" + storeId + "'";

                DBBankOldUtilsTemplate.update(sql);
                logger.info("update area success, storeId="+storeId + ",districtCode="+districtCode);

            }


        }

        logger.info("update area code end ,size="+maps.size());
    }
}
