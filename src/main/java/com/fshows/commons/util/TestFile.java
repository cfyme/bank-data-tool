/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author caofy
 * @version TestFile.java, v 0.1 2018-07-09 15:28 caofy
 */
public class TestFile {

    public static void main(String[] args){
            System.out.println("--------------");

            writeFile("file.txt");
    }

    public static void writeFile(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.println("abc11111 ");
            pw.println("def222222 ");
            pw.println("hef ");
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
