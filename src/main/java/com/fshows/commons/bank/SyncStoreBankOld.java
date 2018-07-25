/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.bank;

import com.alibaba.fastjson.JSON;
import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.model.MerchantBankModel;
import com.fshows.commons.util.CheckUtil;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyCsvUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

/**
 * @author caofy
 * @version SyncStoreBank.java, v 0.1 2018-07-12 14:56 caofy
 */
public class SyncStoreBankOld {

    private static final Log logger = LogFactory.getLog(SyncStoreBankOld.class);


    /**
     * 老的平台化绑卡同步
     */
    public void execute() {

        logger.info("老平台化 >> 同步绑卡信息 >> start");

        ArrayList<String[]> csvList = MyCsvUtil.getStoreBankCsvList();

        insertOrUpdateBankInfo(csvList);

        logger.info("老平台化 >> 同步绑卡信息 >> end, size=" + csvList.size());
    }


    private void insertOrUpdateBankInfo(ArrayList<String[]> csvFileList) {
        if (CollectionUtils.isEmpty(csvFileList)) {
            return;
        }
        try {
            long startTime = System.currentTimeMillis();

            int insertRows = 0;
            int updateRows = 0;

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                String liquidator_id = DataConstant.liquidator_id;
                String[] array = csvFileList.get(row);
                if (array.length < 6) {
                    logger.info("bank csv array not valid, storeId=" + JSON.toJSONString(array));
                    continue;
                }
                MerchantBankModel model = new MerchantBankModel(array);


                String store_id = model.getStore_id();

                //根据storeId查询是否存在绑卡记录，如果存在则更新，不存在则插入

                String sql = "SELECT COUNT(1) FROM lp_store_bank WHERE store_id = '" + store_id + "' AND is_unbind = 0";
                long count = DBBankOldUtilsTemplate.count(sql, null);

                String bank_no = model.getBank_no();
                String username = model.getUsername();

                //2对公，其他对私
                String is_public_account = model.getIs_public_account();

                String open_bank = model.getOpen_bank();
                String super_bank_no = model.getSuper_bank_no();
                String united_bank_no = model.getUnited_bank_no();
                String time =  DateUtil.getNow()+"";


                if(!CheckUtil.isBankNo(bank_no)){
                    logger.info("bankNo not valid, storeId="+store_id+",bankNo="+bank_no);
                    continue;
                }



                String insertSql = String.format("insert into lp_store_bank (store_id,bank_no,username,is_public_account, open_bank, super_bank_no,united_bank_no,create_time,update_time) values" +
                        "('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                        store_id, bank_no, username, is_public_account, open_bank, super_bank_no, united_bank_no,
                        time,time);


                String updateSql = String.format("update lp_store_bank " +
                        "set bank_no='%s'," +
                        "username='%s'," +
                        "is_public_account='%s'," +
                        "open_bank='%s'," +
                        "super_bank_no='%s'," +
                        "united_bank_no='%s'," +
                        "update_time='%s'" +
                        " where store_id='%s' AND is_unbind = 0 ",
                        bank_no,username,is_public_account,open_bank,super_bank_no,united_bank_no,
                        time,store_id);


                if (count > 0) {
                    //update
                    DBBankOldUtilsTemplate.update(updateSql);
                    updateRows++;
                } else {
                    //insert
                    DBBankOldUtilsTemplate.update(insertSql);
                    insertRows++;
                }
            }

            long costTime = System.currentTimeMillis() - startTime;

            System.out.println("costTime = " + costTime);

            logger.info("execute costTime=" + costTime+",insertRows="+insertRows+",updateRows="+updateRows);

        } catch (Exception e) {
            logger.error("insertOrUpdateBankInfo error,", e);
        }

    }


}
