/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.dao.base;

import com.google.common.collect.Maps;
import org.apache.commons.dbutils.QueryRunner;

import java.util.Map;

/**
 * @author caofy
 * @version QueryRunnerMap.java, v 0.1 2018-07-31 14:42 caofy
 */
public class DbUtil {

    public static Map<String, QueryRunner> globelMap = Maps.newHashMap();

    private static YanbianTestDBTemplate yanbianTestDBTemplate = null;
    private static YanbianOnlineDBTemplate yanbianOnlineDBTemplate;


    private static BankOldTemplate bankOldTemplate;
    private static BankNewTemplate bankNewTemplate;
    private static LiquidationTemplate liquidationTemplate;
    private static KfshopTemplate kfshopTemplate;


    private DbUtil() {
    }

    public static final YanbianTestDBTemplate getYanbianTestDBTemplate() {
        if (yanbianTestDBTemplate == null) {
            yanbianTestDBTemplate = new YanbianTestDBTemplate();
        }
        return yanbianTestDBTemplate;
    }

    public static final YanbianOnlineDBTemplate getYanbianOnlineDBTemplate() {
        if (yanbianOnlineDBTemplate == null) {
            yanbianOnlineDBTemplate = new YanbianOnlineDBTemplate();
        }
        return yanbianOnlineDBTemplate;
    }

    public static final BankOldTemplate getBankOldTemplate() {
        if (bankOldTemplate == null) {
            bankOldTemplate = new BankOldTemplate();
        }
        return bankOldTemplate;
    }

    public static final BankNewTemplate getBankNewTemplate() {
        if (bankNewTemplate == null) {
            bankNewTemplate = new BankNewTemplate();
        }
        return bankNewTemplate;
    }

    public static final LiquidationTemplate getLiquidationTemplate() {
        if (liquidationTemplate == null) {
            liquidationTemplate = new LiquidationTemplate();
        }
        return liquidationTemplate;
    }

    public static final KfshopTemplate getKfshopTemplate() {
        if (kfshopTemplate == null) {
            kfshopTemplate = new KfshopTemplate();
        }
        return kfshopTemplate;
    }
}
