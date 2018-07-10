## bank-data-tool
### 注意事项
1. 平台化数据迁移工具

//校验数据库是否可以连接
java -jar   datatool.jar DbBankOldCheck  prod
java -jar   datatool.jar DbLiquidationOldCheck  prod

//支付宝单个商户入驻
java -jar   datatool.jar AipayMerchantCreate  prod 20180420112346020980

//支付宝批量入驻
java -jar   datatool.jar AipayMerchantBatchCreate  prod


//从测试环境表中导入数据
java -jar   datatool.jar DbTransBankNewFromTest  prod
java -jar   datatool.jar DbTransBankOldFromTest  prod


//wxAccount表数据增量迁移
java -jar   datatool.jar DbTransWxAccount  prod 2018070814354769687006110991
java -jar   datatool.jar DbTransWxAccountAppid  prod 4414128


//批量执行sql.txt中的sql
java -jar   datatool.jar DbBankOldExecuteSql  prod
java -jar   datatool.jar DbBankNewExecuteSql  prod

