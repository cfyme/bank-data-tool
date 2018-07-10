/**
 * Copyright (c) 2016, 791650277@qq.com(Mr.kiwi) All Rights Reserved.
 */
package com.fshows.commons.constant;

import com.fshows.commons.util.AlipaySignUtil;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 项目：fs_test_java_api
 * 包名：com.fshows.test.liquidator.openapi
 * 功能：
 * 时间：2016-11-21 14:35
 * 作者：Mr.Kiwi
 */
public class BaseOnline {

    public static String host = "";
    public static String prikey = "";

    static {

        host = "https://ybnsyh-openapi.51fubei.com";
        prikey =
                "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALtgfmTFa5cHwpQu\n" +
                        "AKEjhP6WBX9kfvTCxgJ01I9eOx/9xVpaSZkc1oePkAeBL4BBSAXSu8b4YwBjFOaR\n" +
                        "NSPIpVOW7lddvKvfAQvzESUEKi3opfuCkp9h3qImCdY+j76mqkx6pB8fn7hpURND\n" +
                        "ciqmm3NsExwBYvQCuSJ1kKxF0xsTAgMBAAECgYAxdOyniHw3ZvLyzZmY7pkh4VMe\n" +
                        "JtZjoC1HZLLeDI8qiH7YtFAMu9HcAgaQFIcHITPzZYXupIztAzn3CYivIzyPefrr\n" +
                        "IsfHynpUasKDFmnRRWk9BkdajWbSUYOiTG8VlsDlH6rOueTW06AyKBT6Zw0CYXLe\n" +
                        "QA+bKv6CorUjyop7MQJBAN6gLXgSumiWsjVNcjMx8o6mkf77VposKEiuorQuEZBm\n" +
                        "8w+2Muu2Bgnp6XphLH5g/v4/SD0CR6catKWzGdACvDcCQQDXd45M+sx10lj0oHpq\n" +
                        "uNX8sdtbr6lR9mE7xvGX6tYsKLZR618QufF4ba2dReDxVM5fe6+7Sv0fxlz7WXcd\n" +
                        "mwIFAkEAiIGc/8g0BteIVEi1opCY00FtufRxSL2UweRbHbI10N5fYZ3toTyOfVux\n" +
                        "bvnQp3lgQZOZ/ta8CsnEROoKBNL4hwJBAI2uSd4IlGVtOZ0x4Q1IGT2eXhV9/qKs\n" +
                        "ledOvBIfE1HaO3jGgq5m9ocv55ehGXbXQxS6KiXH+4XnU8DbZV4MTVkCQQC1BADN\n" +
                        "/0VEvFCNcu6SItlqCuyw7YxL39Nl6xMZLfdO1AQH4OJfuwpgEQlWfhqgjRN4bpms\n" +
                        "Fv1Sxnh9HjrmGQ4j";


        /**
         * 延边农商行 key
         */
        prikey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIkJOumCh9VAYE1wFS5Za0PJwOhapT2+wOQ2/NaF55ROtTj4lNfF9P01jqS4IPboaRHJjhRF1QX1vC5YPcaA3DWAEf70mVtJkeXtLoEIFfetfDZU4LgUwPU0KPMOtbOGTHQ8ztG8DK4kZKPxaW6aGYHDbXfOiBHISKyZoWM3k5SdAgMBAAECgYB3VxR5G3YT31Z+2ed4T+M8S822DIrvNNpjZQfYAxFkdQ8ZqFJnfzwV77hj9v6uSESIexFC0nXsFM9nRO23m60pnBeKdTwo5hT8HiRVPUk7EHbETzoTI3X7sx9EdILcrx1LJbCmpLgLR3sItJkpafbt4q+wavtJumvj/gwlwrWK+QJBAMEOpN40hCP8u0gvI1v//OfFzA1+KGjXONsvqSItON6B/T0rT58yl4+aZIKJnDAobXnTxmunPx8EH+d+CV5rBPsCQQC1ttQT+l2CsPUOi8nst+tH9eOeOIEhxoUlLAeRX/PefrbSTRdIO04Mw6T5sHAkfPiBsl3dcFEY232W23Nm+ylHAkEAhVuvSYtvaEiRulhNjaS0OAprXTqzgVCChu5SYDhq21Fh9Foxwx+4kEsvePjD+oRCs3A11XGPacZ4n3lx0VYN9wJBAJfvd6IAq+FTroLsFPkNyvdPPHOV0Tr5b2hmSSmfbhvyHVD3zmjB1X/MsgtoIEwSBgMDrYIyoExFcdwKP5KynI8CQCOL06zdwwHFdrKjXV/e+H2YXiBr2NFlo9SkE7HjEtJF8q62Qx6SRfcs84vpZx30Q4FKkRpH1n3bk2yg9UOxHl0=";

        prikey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCTyz+uRy24ZZSiVM4swqT4+ORRsoWBDtrm3EPMxFNT96plrnFVMW8D36Q+XtjQslVE7htJ/SgSDeAuQ/GiTv7D/DIoDaFIvs+/u7eN7UyF3aySABENg+GuVp3fv+zMCMLBfHsoqhQdrw8M9+OnwJK2jtz+uASwQIGhPP+aF8kkwEhGeU3QXHB9HRggH129HcTCUmGqlacp9fzk7Y3EvURxRLzD7sm4z6oVj/XcJ3TXPe8IH7GEGwkb0Mt34YZ94G4Evk3gzW6hTjdgygkZeCA76EY1+B6BubwJeYAP5PbNP52HsUsFfsVLP5Ey6nWdXYPmBPb3Xu/Y2bKoVWJuh8kfAgMBAAECggEAEZlWT+5x509RT7NBidrzwiIBLilP9HpMXslD2ZJXSdxoEgufu0FTsuZirHqm3tMdjEO+RCU9LEkytM+qamV1uOjDIDnfRsZR/sbfqKrlPduTvR786NsXeKcfzZERJYq1hkzIbaMzGgXKiQTLuyIXVl3UuxA2nrZPuV+GIFdMwG9mt3+wi4lZ10kahUbnsw/JLF5hXM7KA2i7pLmUaeJamS+ix7c4yhk95cVGt0xboCfdUGb8CrAiPFpVvOwt4eXXcJyOjcvPLHi3ByCtjRHWy0CMdBNf+6qM5hLTegCmeoCYIHdq7Sm16DGU3qzGmSPYmqCGULcW1/FJrvn0xl1GwQKBgQDRnP7YC3xFBsy/F2RblZAeLLrn1O/GhWxaHKGGmFJdBonoQ38iAIutF2WrYr7JBI0m2CeSr+spQCsAZD1zwZI+d42XrvvkKAkz8L6llWHzPoWJKoU9J8mKC6CAZVTNERGsB/CwaXFGTB1PUDRrhcWLSXq3UxylmyRL0SFsgKfsVwKBgQC0gBEX2Du2ZdC/b0sucMm9R4iXhAcD3g0DTwOw+6Vo8OOjlHJDTBP2uheC4LSMciIac0XI9aQZtS7YF2Icgi+zNe688MopO/72DxYgRtRPdqZS3xqSIQoGq5XEEUzVheMQj+gel7Ok/Ia7pPx1wbLxtQpIhgiR6oOeNGCU83MMeQKBgGuWb1+olYJAeTrj4Mj5PV2fi9eOffuhY/nklCunZy8EOiiS7GAhc4/GYJLVPiOjSGfVrool7Ufg/bFXwgr2IgAaEPz3/aYfvomAWfpXcE0PnC7JOlYnY9SNHzaHzDztM+resHV7yX5RdwHMovtFUB2r+2a5LAz8Umbv0n4EkyxFAoGAPJeBJXABG4myOZEyu7RBBLkusS4w7Ktt0t6DbrYLIN88i30Znl27leeCXPzxnvPxXs+zbXKIU9jukLvIw6kwOgiwy9xJAO6S+Ca4Vr1wJ/y8eLVMRiGxK+wNqnPkRwla4ARu91YViBz9XaBU33LWjy0m9pGH7vO6mzEZ+uZ+iIECgYB9cJM180t9mlGznlcKBfQqZscNC2UnTUvXymkuS2AiUnjKPP43zEA51iUJhtZjGZwLE2kpHNv2V7zrqelYoK+BUw3eCh3NUmp8URZxfDkq94Pl4jN8EZaSos9bmf5zOjdh51DHcvNZfciDb0WRtIxTGyieJWYq1lTowboovY93mw==";


        prikey = KeyConstant.privateKey;
    }


    public static String getSign(Map<String, Object> params) {
        return AlipaySignUtil.createSign(params, prikey);
    }


    public static Map<String, Object> getParamMap() {
        Map<String, Object> params = Maps.newHashMap();
        /**  //延边农商行 友店  **/
        params.put("app_id", DataConstant.liquidator_id);
        params.put("version", "1.0");
        return params;
    }


}
