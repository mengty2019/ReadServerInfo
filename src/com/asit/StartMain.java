package com.asit;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.setting.dialect.Props;

import java.awt.SystemTray;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class StartMain {
    public static void main(String[] args) {
        Props props = new Props("sys.properties");
        String rabbitInterface = props.getProperty("rabbitInterface");
        System.out.println("----------------start------------------");
        System.out.println(rabbitInterface);
        System.out.println("SHELL脚本传参："+Convert.toStr(args));
        String[] arr= (String[]) Arrays.asList(
                "{\"cpu\":\"0.9\",\"disk\":{\"percent\":\"65%\",\"used\":\"23805844\",\"dir\":\"/\",\"ava\":\"12912864\"},\"mem\":{\"used\":\"507824\",\"total\":\"1867292\",\"free\":\"474472\"}}"
        ).toArray();
        args=arr;
        JSONObject jsonObject =new JSONObject();
        if(args!=null&&args.length>=1){
            jsonObject = new JSONObject(args[0].replaceAll("\\%",""));
        }
        try {
            String result = HttpClientUtil.remoteJsonRequest(rabbitInterface,jsonObject);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----------------end------------------");
    }
}
