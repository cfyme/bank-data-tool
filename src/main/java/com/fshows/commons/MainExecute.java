package com.fshows.commons;

import com.fshows.commons.alipay.AipayMerchantBatchCreate;
import com.fshows.commons.alipay.AipayMerchantCreate;
import com.fshows.commons.bank.SyncStoreBankNew;
import com.fshows.commons.bank.SyncStoreBankOld;
import com.fshows.commons.batch.DbBankNewExecuteSql;
import com.fshows.commons.batch.DbBankOldExecuteSql;
import com.fshows.commons.check.DbBankNewCheck;
import com.fshows.commons.check.DbBankOldCheck;
import com.fshows.commons.check.DbLiquidationOldCheck;
import com.fshows.commons.check.DbYanBianCheck;
import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.kfshop.KfshopTest;
import com.fshows.commons.liquidation.*;
import com.fshows.commons.yanbian.*;

public class MainExecute {

    public static void main(String[] args) throws Exception {
        String a = "", b = "", c = "", d = "";
        try {
            a = args[0];
        } catch (Exception e) {
        }
        try {
            b = args[1];
        } catch (Exception e) {
        }
        try {
            c = args[2];
        } catch (Exception e) {
        }
        try {
            d = args[3];
        } catch (Exception e) {
        }

        System.out.println(String.format("a=%s,b=%s,c=%s,d=%s", a, b, c, d));

        if ("prod".equalsIgnoreCase(b)) {
            //线上环境
            DataConstant.isProd = true;
        }


        if ("DbTrans".equalsIgnoreCase(a)) {
            DbTrans dbTrans = new DbTrans();
            dbTrans.execute();
        }


        if ("DbYanBianCheck".equalsIgnoreCase(a)) {
            DbYanBianCheck gaara = new DbYanBianCheck();
            gaara.execute();
        }

        if ("DbLiquidationOldCheck".equalsIgnoreCase(a)) {
            DbLiquidationOldCheck gaara = new DbLiquidationOldCheck();
            gaara.execute();
        }

        if ("DbBankOldCheck".equalsIgnoreCase(a)) {
            DbBankOldCheck gaara = new DbBankOldCheck();
            gaara.execute();
        }
        if ("DbBankNewCheck".equalsIgnoreCase(a)) {
            DbBankNewCheck gaara = new DbBankNewCheck();
            gaara.execute();
        }

        if ("DbBankNewCheck".equalsIgnoreCase(a)) {
            DbBankNewCheck gaara = new DbBankNewCheck();
            gaara.execute();
        }

        //老的平台化数据批量导入
        if ("DbTransToBankOld".equalsIgnoreCase(a)) {
            DbTransToBankOld dbTransToBankOld = new DbTransToBankOld();
            dbTransToBankOld.execute();
        }

        //老的平台化 支付宝入驻
        if ("AipayMerchantCreate".equalsIgnoreCase(a)) {
            AipayMerchantCreate aipayMerchantCreate = new AipayMerchantCreate();
            aipayMerchantCreate.execute(c);
        }

        //老的平台化 调用支付宝批量入驻
        if ("AipayMerchantBatchCreate".equalsIgnoreCase(a)) {
            AipayMerchantBatchCreate aipayMerchantBatchCreate = new AipayMerchantBatchCreate();
            aipayMerchantBatchCreate.execute();
        }


        //-----------------------------------------------------------

        //新的平台化数据批量导入
        if ("DbTransToBankNew".equalsIgnoreCase(a)) {
            DbTransToBankNew dbTransToBankNew = new DbTransToBankNew();
            dbTransToBankNew.execute();
        }
        if ("DbTransWxAccount".equalsIgnoreCase(a)) {
            DbTransWxAccount dbTransWxAccount = new DbTransWxAccount();
            dbTransWxAccount.execute(c);
        }
        if ("DbTransWxAccountAppid".equalsIgnoreCase(a)) {
            DbTransWxAccountAppid dbTransWxAccount = new DbTransWxAccountAppid();
            dbTransWxAccount.execute(Long.valueOf(c));
        }


        if ("DbTransBankNewFromTest".equalsIgnoreCase(a)) {
            DbTransBankNewFromTest gaara = new DbTransBankNewFromTest();
            gaara.execute(Integer.valueOf(c));
        }
        if ("DbTransBankOldFromTest".equalsIgnoreCase(a)) {
            DbTransBankOldFromTest gaara = new DbTransBankOldFromTest();
            gaara.execute(Integer.valueOf(c));
        }


        if ("DbQureyStoreInfoFromLp".equalsIgnoreCase(a)) {
            DbQureyStoreInfoFromLp gaara = new DbQureyStoreInfoFromLp();
            gaara.execute();
        }

        if ("DbBankOldExecuteSql".equalsIgnoreCase(a)) {
            DbBankOldExecuteSql gaara = new DbBankOldExecuteSql();
            gaara.execute();
        }
        if ("DbBankNewExecuteSql".equalsIgnoreCase(a)) {
            DbBankNewExecuteSql gaara = new DbBankNewExecuteSql();
            gaara.execute();
        }


        if ("UpdateAreaCode".equalsIgnoreCase(a)) {
            UpdateAreaCode gaara = new UpdateAreaCode();
            gaara.execute();
        }
        if ("SyncStoreBankOld".equalsIgnoreCase(a)) {
            SyncStoreBankOld gaara = new SyncStoreBankOld();
            gaara.execute();
        }
        if ("SyncStoreBankNew".equalsIgnoreCase(a)) {
            SyncStoreBankNew gaara = new SyncStoreBankNew();
            gaara.execute();
        }

        if ("YanbianOldDataToTest".equalsIgnoreCase(a)) {
            YanbianOldDataToTest gaara = new YanbianOldDataToTest();
            gaara.execute(Integer.valueOf(c), Integer.valueOf(d));
        }
        if ("YanbianOldStoreAuthDataToTest".equalsIgnoreCase(a)) {
            YanbianOldStoreAuthDataToTest gaara = new YanbianOldStoreAuthDataToTest();
            gaara.execute(c, d);
        }
        if ("YanbianStoreTestToOnline".equalsIgnoreCase(a)) {
            YanbianStoreTestToOnline gaara = new YanbianStoreTestToOnline();
            gaara.execute(Integer.valueOf(c));
        }
        if ("YanbianStoreAuthTestToOnline".equalsIgnoreCase(a)) {
            YanbianStoreAuthTestToOnline gaara = new YanbianStoreAuthTestToOnline();
            gaara.execute(Integer.valueOf(c));
        }
        if ("YanbianAlipayAccount".equalsIgnoreCase(a)) {
            YanbianAlipayAccount gaara = new YanbianAlipayAccount();
            gaara.execute(Integer.valueOf(c));
        }

        if ("YanbianFinanceToTest".equalsIgnoreCase(a)) {
            YanbianFinanceToOnline gaara = new YanbianFinanceToOnline();
            gaara.execute();
        }
        if ("KfshopTest".equalsIgnoreCase(a)) {
            KfshopTest gaara = new KfshopTest();
            gaara.execute();
        }
        if ("YanbianRefundOrderSync".equalsIgnoreCase(a)) {
            YanbianRefundOrderSync gaara = new YanbianRefundOrderSync();
            gaara.execute(Integer.valueOf(c));
        }

        if ("YanbianRefundOrderToOnline".equalsIgnoreCase(a)) {
            YanbianRefundOrderToOnline gaara = new YanbianRefundOrderToOnline();
            gaara.execute(Integer.valueOf(c));
        }


    }
}
