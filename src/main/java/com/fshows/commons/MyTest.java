/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons;

import com.fshows.commons.bank.SyncStoreBankNew;
import com.fshows.commons.bank.SyncStoreBankOld;
import com.fshows.commons.liquidation.DbTrans;
import com.fshows.commons.liquidation.DbTransBankNewFromTest;

/**
 * @author caofy
 * @version MyTest.java, v 0.1 2018-07-07 16:56 caofy
 */
public class MyTest {

    public static void main(String[] args){
        //DbTrans dbTrans = new DbTrans();
        //dbTrans.execute();;

        //DbTransBankNewFromTest a = new DbTransBankNewFromTest();
        //a.execute();

//        SyncStoreBankOld gaara = new SyncStoreBankOld();
//        gaara.execute();;

        SyncStoreBankNew gaara = new SyncStoreBankNew();
        gaara.execute();;
    }
}
