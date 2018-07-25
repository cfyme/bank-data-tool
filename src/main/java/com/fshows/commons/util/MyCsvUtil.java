/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import com.csvreader.CsvReader;
import com.fshows.commons.constant.DataConstant;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version MyCsvUtil.java, v 0.1 2018-07-07 21:30 caofy
 */
public class MyCsvUtil {

    private static final Log logger = LogFactory.getLog(DateUtil.class);

    static Map<String, String> uidMap = Maps.newHashMap();

    public static ArrayList<String[]> getCsvList() {
        try {

            logger.info("load csv file start");

            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = DataConstant.lifecircle_csv;
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName(DataConstant.charset));
            // 跳过表头如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {

                String[] array = reader.getValues();
                //String uid = MyStringUtil.trim(array[0]);
                String storeId = MyStringUtil.trim(array[2]);


                if (uidMap.containsKey(storeId)) {
                    logger.info("existstoreId=" + storeId);
                } else {
                    csvFileList.add(array);
                }

                uidMap.put(storeId, storeId);

            }
            reader.close();

            logger.info("load csv file end");

            return csvFileList;

        } catch (Exception e) {
            logger.error("getCsvList error", e);
            return null;
        }
    }


    public static ArrayList<String[]> getStoreBankCsvList() {
        try {

            logger.info("load gBankCsv file start");

            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = DataConstant.bank_csv;
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName(DataConstant.charset));
            // 跳过表头如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                String[] array = reader.getValues();
                String uid = MyStringUtil.trim(array[0]);
                String storeId = MyStringUtil.trim(array[2]);
                csvFileList.add(array);
            }
            reader.close();
            logger.info("load csv file end");
            return csvFileList;
        } catch (Exception e) {
            logger.error("getCsvList error", e);
            return null;
        }
    }

}
