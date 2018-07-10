package com.fshows.commons.dao.dbsource;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperTestEnv {
    private static DataSource dataSource;

    private DbHelperTestEnv() {
    }


    //新的平台化数据源
    public static QueryRunner getQueryRunner() {
        //配置dbcp数据源
        BasicDataSource dbcpDataSource = new BasicDataSource();
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1hs6532ub366f7go.mysql.rds.aliyuncs.com:3306/fs_bank_liquidation?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("fshows");
        dbcpDataSource.setPassword("Fshows12#$");


        dbcpDataSource.setDefaultAutoCommit(true);
        dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dbcpDataSource.setMaxActive(1);
        dbcpDataSource.setInitialSize(1);
        dbcpDataSource.setMaxIdle(1);
        dbcpDataSource.setMaxWait(5000);
        DbHelperTestEnv.dataSource = (DataSource) dbcpDataSource;
        System.out.println("Initialize test dbcp..." + DataConstant.isProd);
        return new QueryRunner(DbHelperTestEnv.dataSource);
    }


}