/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.csvreader.CsvReader;
import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.util.MyCsvUtil;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author caofy
 * @version DbTrans.java, v 0.1 2018-07-05 20:33 caofy
 */
public class DbTrans {


    private static final Log logger = LogFactory.getLog(DbTrans.class);


    public void execute() {

        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = MyCsvUtil.getCsvList();

            System.out.println("==============" + csvFileList.size());
            logger.info("lifecircle_csv.size = " + csvFileList.size());


            StringBuffer sb = new StringBuffer(10240);
            String sqlPrefix = "insert into lp_all_lifecircle_merchant_temp (uid,liquidator_id,liquidator_store_id,store_id,merchant_name, alias_name," +
                    "service_phone,contact_name,contact_phone,contact_mobile,contact_email," +
                    "memo,branch,category_id,bank_no,username," +
                    "is_public_account,open_bank,super_bank_no,united_bank_no,id_card_num," +
                    "id_card_name,store_address,id_card_hand_img,store_front_img,business_license_img," +
                    "province_code,city_code,district_code,business_license,business_license_type,contact_type) values";
            sb.append(sqlPrefix);
            long startTime = System.currentTimeMillis();

            //uid,生活圈商户ID,清算平台商户ID,商户名称,商户别名,客服电话,联系人名称,联系人电话,联系人手机号,联系人邮箱,
            // 行业类目,商户备注,所属分行,银行卡号,收款户名,是否为对公账户,开户行名称,
            // 超级网银号,联行行号,身份证号码,身份证姓名,
            // 手持身份证,营业执照,营业执照号,省code,市code,区code,
            // 营业执照类型,联系人类型,店铺地址,门头照
            //288106,"28810616135597	","20171020153044023031	",美食美客（丰盛九玺）,美食美客（丰盛九玺）,"13588411164	",陈宝琴,"13588411164	","13588411164	",732518510@qq.com,"2015050700000015	",,中国农业银行,"6228480329248206776	",邓家法,1,中国农业银行,"103100000026	",,"350427198807137015	",邓家法,https://img.51youdian.com/data/img/201710/20171023124842388745.jpg,https://img.51youdian.com/data/img/201710/20171023124842388745.jpg,"	",330000,330100,330106,,,榨河巷28号,https://img.51youdian.com/data/img/201710/20171020151843158278.jpg

            int idNumNull = 0;

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                String liquidator_id = DataConstant.liquidator_id;

                String[] array = csvFileList.get(row);
                String uid = MyStringUtil.trim(array[0]);
                String liquidator_store_id = MyStringUtil.trim(array[1]);
                String store_id = MyStringUtil.trim(array[2]);

                if (array.length < 30) {
                    logger.info("array not valid, storeId="+store_id);
                    continue;
                }

                try {
                    String merchant_name =MyStringUtil.trim(array[3]);
                    String alias_name = MyStringUtil.trim(array[4]);
                    alias_name = alias_name.replace("（平安银行）","");
                    alias_name = alias_name.replace("（平安银行商户）","");

                    //赵延涛（平安银行）

                    String service_phone = MyStringUtil.trim(array[5]);
                    String contact_name = MyStringUtil.trim(array[6]);
                    String contact_phone = MyStringUtil.trim(array[7]);
                    String contact_mobile = MyStringUtil.trim(array[8]);
                    String contact_email = MyStringUtil.trim(array[9]);
                    String category_id = MyStringUtil.trim(array[10]);
                    String memo = MyStringUtil.trim(array[11]);
                    String branch = MyStringUtil.trim(array[12]);

                    String bank_no = MyStringUtil.trim(array[13]);
                    String username = MyStringUtil.trim(array[14]);
                    String is_public_account = MyStringUtil.trim(array[15]);

                    String open_bank = MyStringUtil.trim(array[16]);
                    String super_bank_no = MyStringUtil.trim(array[17]);
                    String united_bank_no = MyStringUtil.trim(array[18]);
                    String id_card_num = MyStringUtil.trim(array[19]);
                    String id_card_name = MyStringUtil.trim(array[20]);

                    String id_card_hand_img = MyStringUtil.trim(array[21]);
                    String business_license_img = MyStringUtil.trim(array[22]);
                    String business_license = MyStringUtil.trim(array[23]);
                    String province_code = MyStringUtil.trim(array[24]);
                    String city_code = MyStringUtil.trim(array[25]);
                    String district_code = MyStringUtil.trim(array[26]);

                    String business_license_type = MyStringUtil.trim(array[27]);
                    String contact_type = MyStringUtil.trim(array[28]);
                    String store_address = MyStringUtil.trim(array[29]);
                    String store_front_img = MyStringUtil.trim(array[30]);

                    if(contact_mobile.length()>16){
                        logger.info("contact_mobile too long, storeId="+store_id+",contact_mobile="+contact_mobile+",uid="+uid);
                        contact_mobile=contact_mobile.substring(0,16);
                    }

                    if(business_license.length()>32){
                        logger.info("business_license too long, storeId="+store_id+",business_license="+business_license+",uid="+uid);
                        continue;

                    }

                    if(id_card_hand_img.length()>200){
                        logger.info("id_card_hand_img not valid, uid="+uid+", storeId="+store_id);

                        id_card_hand_img="9999";
                    }


                    if(StringUtils.isBlank(id_card_hand_img)){
                        idNumNull++;
                        logger.info("id_card_hand_img is null, uid="+uid+",storeId="+store_id);

                    }

                    if(business_license_img.length()>200){
                        logger.info("business_license_img not valid, uid="+uid+", storeId="+store_id);
                        business_license_img="9999";
                    }
                    if(store_front_img.length()>200){
                        logger.info("store_front_img not valid, uid="+uid+", storeId="+store_id);
                        //;
                        store_front_img="9999";
                    }


                    if (StringUtils.isBlank(merchant_name)) {
                        logger.info("merchant_name is null, store_id="+store_id);
                        continue;
                    }

                    String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                            uid,liquidator_id,liquidator_store_id,store_id,merchant_name, alias_name,
                            service_phone,contact_name,contact_phone,contact_mobile,contact_email,
                            memo,branch,category_id,bank_no,username,
                            is_public_account,open_bank,super_bank_no,united_bank_no,id_card_num,
                            id_card_name,store_address,id_card_hand_img,store_front_img,business_license_img,
                            province_code,city_code,district_code,business_license,business_license_type,contact_type
                    );
                    sb.append(tmp);

                    if ((row + 1) % DataConstant.bachSize == 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        DBBankNewUtilsTemplate.update(sb.toString());
                        sb = new StringBuffer(10240);
                        sb.append(sqlPrefix);
                    }

                }catch (Exception e){
                    logger.error("==============storeId="+store_id, e);
                }


            }


            if (sb.length() > sqlPrefix.length()) {
                sb.deleteCharAt(sb.length() - 1);
                DBBankNewUtilsTemplate.update(sb.toString());
                //logger.info(sb.toString());
            }


            long costTime = System.currentTimeMillis() - startTime;

            System.out.println("costTime = " + costTime);

            logger.info("execute costTime=" + costTime + ",idNumNull="+idNumNull);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
