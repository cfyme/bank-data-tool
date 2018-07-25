/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.dao.dbtemplate.DBLiquidationUtilsTemplate;
import com.fshows.commons.util.MyFileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version DbQureyStoreInfoFromLp.java, v 0.1 2018-07-09 14:56 caofy
 */
public class DbQureyStoreInfoFromLp {

    private static final Log logger = LogFactory.getLog(DbQureyStoreInfoFromLp.class);

    public void execute() {

        List<String> list = MyFileUtil.getListFromFile("myfile.txt");

        logger.info("list.size=" + list.size());

        try {
            query(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void query(List<String> list) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("sql.txt"));
        for (String storeId : list) {

            String sql = "SELECT store_address, id_card_name, store_id, province_code,city_code,district_code from lp_liquidator_store_auth WHERE store_id=?";

            Map<String, Object> map = DBLiquidationUtilsTemplate.findFirst(sql, new String[]{storeId});

            logger.info(map);

            if(map==null){
                continue;
            }

            String store_address = map.get("store_address")+"";
            String province_code = map.get("province_code")+"";
            String city_code = map.get("city_code")+"";
            String district_code = map.get("district_code")+"";

//            String updateSql = String.format("update lp_liquidator_store_auth set " +
//                    "store_address='%s',province_code='%s',city_code='%s',district_code='%s' " +
//                    "where store_id='%s';",
//                    store_address,province_code,city_code,district_code, storeId);


            String updateSql = String.format("update lp_liquidator_store_auth set " +
                            "store_address='%s' " +
                            "where store_id='%s'; ",
                    store_address,storeId);

            pw.println(updateSql);

        }

        pw.close();
    }


}
