/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.batch;

import com.csvreader.CsvReader;
import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBLiquidationUtilsTemplate;
import com.fshows.commons.liquidation.DbTrans;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author caofy
 * @version TestDBBatchInsert.java, v 0.1 2018-07-06 16:09 caofy
 */
public class TestDBBatchInsert {


    private static final Log logger = LogFactory.getLog(DbTrans.class);


    public void execute() {

        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "/users/caofy/excel/test.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                //System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();

            System.out.println("==============" + csvFileList.size());
            DBLiquidationUtilsTemplate.update("delete from fbs_aaa where 1=1");

            Thread.sleep(3000);

            logger.info("开始拼接SQL");

            StringBuffer sb = new StringBuffer(10240);
            String sqlPrefix = "insert into lp_aaa_lifecircle_merchant (ext1,ext2,ext3) values";
            sb.append(sqlPrefix);
            long startTime = System.currentTimeMillis();


            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                String ext1 = csvFileList.get(row)[0];
                String ext2 = csvFileList.get(row)[1];
                String ext3 = csvFileList.get(row)[2];

                if (StringUtils.isBlank(ext1)) {
                    continue;
                }

                String tmp = String.format("('%s', '%s', '%s'),", ext1, ext2, ext3);
                sb.append(tmp);

                if ((row + 1) % DataConstant.bachSize == 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    //
                    logger.info(sb.toString());
                    DBLiquidationUtilsTemplate.update(sb.toString());
                    sb = new StringBuffer(10240);
                    sb.append(sqlPrefix);
                }

            }


            if (sb.length() > sqlPrefix.length()) {
                sb.deleteCharAt(sb.length() - 1);
                DBLiquidationUtilsTemplate.update(sb.toString());
                logger.info(sb.toString());
            }


            long costTime = System.currentTimeMillis() - startTime;

            System.out.println("costTime = " + costTime);

            logger.info("execute costTime=" + costTime);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
