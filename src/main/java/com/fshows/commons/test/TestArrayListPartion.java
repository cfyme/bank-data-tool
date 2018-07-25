package com.fshows.commons.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class TestArrayListPartion {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        List<List<String>> partition = Lists.partition(list, 2);
        System.out.println(JSON.toJSONString(partition));
    }
}