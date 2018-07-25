/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons;

import com.fshows.commons.liquidation.DbTrans;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author caofy
 * @version MyTest.java, v 0.1 2018-07-07 16:56 caofy
 */
public class MyTest {

    public static void main(String[] args){
//        DbTrans dbTrans = new DbTrans();
//        dbTrans.execute();;

        //DbTransBankNewFromTest a = new DbTransBankNewFromTest();
        //a.execute();

//        SyncStoreBankOld gaara = new SyncStoreBankOld();
//        gaara.execute();;

//        DbCheck5000Store gaara = new DbCheck5000Store();
//        gaara.execute();;

            List<String> list = Lists.newArrayList();
            int size = 1099;
            for (int i = 0; i < size; i++) {
                list.add("hello-" + i);
            }
            // 切割大集合到指定的长度：11
            List<List<String>> rsList = Lists.partition(list, 500);
            int i = 0;
            for (List<String> obj : rsList) {
                System.out.println(String.format("row:%s -> size:%s,data:%s", ++i, obj.size(), obj));
            }
    }
}
