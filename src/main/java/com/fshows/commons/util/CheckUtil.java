/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

/**
 * @author caofy
 * @version CheckUtil.java, v 0.1 2018-07-15 17:50 caofy
 */
public class CheckUtil {

    public static boolean isBankNo(String str){
        
        String regex  = "\\d{8}+";
        return str.matches(regex);
        
    }
    
    public static void main(String[] args){
        System.out.println(isBankNo("0001111111"));
    }
}
