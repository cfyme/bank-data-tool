package com.fshows.commons.dao.dbsource;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperBankOld {
    private static DataSource dataSource;

    private DbHelperBankOld() {
    }

    //老的平台化数据源
    public static QueryRunner getQueryRunner() {
        if (DbHelperBankOld.dataSource == null) {
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
            DbHelperBankOld.dataSource = (DataSource) dbcpDataSource;
            System.out.println("Initialize bankold dbcp...isProd=" + DataConstant.isProd);
        }
        return new QueryRunner(DbHelperBankOld.dataSource);
    }

    private static void setTestConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1hs6532ub366f7go.mysql.rds.aliyuncs.com:3306/fs_bank_platform_test?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("bank_plat_test");
        dbcpDataSource.setPassword("bank_!@#QWE999");
        System.out.println("setTestConnection");
    }


    private static void setOnlineConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1p8uwnw5kw31039.mysql.rds.aliyuncs.com:3306/fs_bank_platform?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("bankplat_user0");
        dbcpDataSource.setPassword("GU6u_Pk3Cszj_bank");
        System.out.println("setOnlineConnection");

    }


}