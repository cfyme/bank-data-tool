package com.fshows.commons.dao.dbsource;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperBankNew {
    private static DataSource dataSource;

    private DbHelperBankNew() {
    }


    //新的平台化数据源
    public static QueryRunner getQueryRunner() {
        if (DbHelperBankNew.dataSource == null) {
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
            DbHelperBankNew.dataSource = (DataSource) dbcpDataSource;
            System.out.println("Initialize Liquidation dbcp..." + DataConstant.isProd);
        }
        return new QueryRunner(DbHelperBankNew.dataSource);
    }

    private static void setTestConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1hs6532ub366f7go.mysql.rds.aliyuncs.com:3306/fs_bank_liquidation?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("fshows");
        dbcpDataSource.setPassword("Fshows12#$");
    }


    private static void setOnlineConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp133f44jd8sf7aa4.mysql.rds.aliyuncs.com/fs_bank_liquidation?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("fs_biz_user2");
        dbcpDataSource.setPassword("mJ=4w3)MKCwnpYeg");
        System.out.println(" new bank setOnlineConnection");


//        rm-bp133f44jd8sf7aa4.mysql.rds.aliyuncs.com 3306
//        fs_biz_user2
//                mJ=4w3)MKCwnpYeg

    }

}