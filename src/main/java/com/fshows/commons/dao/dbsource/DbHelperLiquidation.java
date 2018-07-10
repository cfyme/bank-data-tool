package com.fshows.commons.dao.dbsource;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperLiquidation {
    private static DataSource dataSource;

    private DbHelperLiquidation() {
    }

    public static QueryRunner getQueryRunner() {
        if (DbHelperLiquidation.dataSource == null) {
            //配置dbcp数据源
            BasicDataSource dbcpDataSource = new BasicDataSource();

            if (DataConstant.isProd) {
                setOnlineConnection(dbcpDataSource);
            } else {
                setTestConnection(dbcpDataSource);
            }

            dbcpDataSource.setDefaultAutoCommit(true);
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setMaxActive(1);
            dbcpDataSource.setInitialSize(1);
            dbcpDataSource.setMaxIdle(1);
            dbcpDataSource.setMaxWait(5000);
            DbHelperLiquidation.dataSource = (DataSource) dbcpDataSource;
            System.out.println("Initialize Liquidation ...dbcp..." + DataConstant.isProd);
        }
        return new QueryRunner(DbHelperLiquidation.dataSource);
    }

    private static void setTestConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://drdsrhg8kw6jh8v2public.drds.aliyuncs.com:3306/fs_liquidation_platform?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("fs_liquidation_platform");
        dbcpDataSource.setPassword("uUoPxfD9Lgg9blGd");
        System.out.println("setTestConnection");

    }


    private static void setOnlineConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://drds93d3kt40v3z1.drds.aliyuncs.com:3306/fs_liquidation_platform?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("fs_liquidation_platform");
        dbcpDataSource.setPassword("GU6u_Pk3Cszj_IiTNbghld");
    }


}