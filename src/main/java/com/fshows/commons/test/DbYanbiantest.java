/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.test;

import com.fshows.commons.dao.base.DbUtil;

/**
 * @author caofy
 * @version DbYanbiantest.java, v 0.1 2018-07-30 11:46 caofy
 */
public class DbYanbiantest {

    public static void main(String[] args) {
        String sql = "select count(1)  from lp_bank_config limit 1";

        long result = DbUtil.getYanbianTestDBTemplate().count(sql, null);
        System.out.println("count1=" + result);

        sql = "select count(1)  from lp_bank_config limit 1";

        result = DbUtil.getYanbianTestDBTemplate().count(sql, null);
        System.out.println("count2=" + result);
    }

}
