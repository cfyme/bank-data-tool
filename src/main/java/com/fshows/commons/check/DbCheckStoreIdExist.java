/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.check;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.util.MyCsvUtil;
import com.fshows.commons.util.MyFileUtil;
import com.fshows.commons.util.MyStringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version DbCheck5000Store.java, v 0.1 2018-07-13 15:34 caofy
 */
public class DbCheckStoreIdExist {

    public void execute() {

        ArrayList<String[]> csvFileList = MyCsvUtil.getCsvList();
        List<String> sourceList = Lists.newArrayList();


        for (int row = 0; row < csvFileList.size(); row++) {
            String[] array = csvFileList.get(row);
            String store_id = MyStringUtil.trim(array[2]);
            sourceList.add(store_id);
        }
        System.out.println("sourceList.size="+sourceList.size());


        int total = 0;
        int dbNotExist = 0;

        String sql = "select store_id from lp_all_lifecircle_merchant_temp";

        List<Map<String, Object>> dbList = DBBankNewUtilsTemplate.find(sql);
        System.out.println("dbList.size=" + dbList.size());
        HashMap storeIdMap = Maps.newHashMap();
        for (Map<String, Object> map : dbList) {
            String storeId = map.get("store_id") + "";
            storeIdMap.put(storeId, "");
        }


        for (String storeId : sourceList) {

            if (storeIdMap.containsKey(storeId)) {
                //System.out.println(storeId);
                total++;
            }else{
                dbNotExist++;
                System.out.println(storeId);
            }


        }

        System.out.println("storeIdMap.size=" + storeIdMap.size() + ", exist in db total=" + total + ",not exist="+dbNotExist);

    }

    public static void main(String[] args) {
        DbCheckStoreIdExist gaara = new DbCheckStoreIdExist();
        gaara.execute();
    }

}
