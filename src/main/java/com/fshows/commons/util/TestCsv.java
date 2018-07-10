/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author caofy
 * @version TestCsv.java, v 0.1 2018-07-06 15:48 caofy
 */
public class TestCsv {

    public static void main(String[] args) {
        //writeCSV();
        readCSV();;
    }


    public static void writeCSV() {
        // 定义一个CSV路径
        String csvFilePath = "/users/caofy/excel/test.csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            String[] csvHeaders = {"编号", "姓名", "年龄"};
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            for (int i = 0; i < 12345; i++) {
                String[] csvContent = {i + "000000", "StemQ", "浙江省杭州市西湖区榨河巷28号" + i};
                csvWriter.writeRecord(csvContent);
                System.out.println(i);
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCSV() {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "/users/caofy/excel/a2.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK"));
            // 跳过表头如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();
            
            System.out.println("==============" +csvFileList.size());

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                String cell = csvFileList.get(row)[2];
                System.out.println("------------>" + cell);
            }

            System.out.println("size=" + csvFileList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
