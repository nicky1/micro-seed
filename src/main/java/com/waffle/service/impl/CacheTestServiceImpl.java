package com.waffle.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.waffle.service.CacheTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.Map;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/7/4 16:30
 * @Description:
 */
@Slf4j
public class CacheTestServiceImpl implements CacheTestService {

    @Override
    public String get(Integer id) {
        return null;
    }

    @Override
    public void save(Integer id, String name) {

    }

    @Override
    public void delete(Integer id) {

    }

    public static void main(String[] args) {
        String s1 = readFileContent("/Users/xiaoshuangyi/xuehai/云作业/临时文件/123.txt");
        JSONArray resultsArray = JSONArray.parseArray(s1);

        String s2 = readFileContent("/Users/xiaoshuangyi/xuehai/云作业/临时文件/studentWork.json");
        JSONArray studetntWorkArray = JSONArray.parseArray(s2);

        //studnetworkid:学生作业信息
        Map<String, StudentWork> map = Maps.newHashMapWithExpectedSize(studetntWorkArray.size());

        for (int i = 0, len = studetntWorkArray.size(); i < len; i++) {
            JSONObject jsonObject = studetntWorkArray.getJSONObject(i);
            String tmpsId = jsonObject.getString("_id");
            JSONArray targets = jsonObject.getJSONArray("targets");
            long userId = targets.getJSONObject(0).getJSONArray("users").getJSONObject(0).getLong("_id");
            String userName = targets.getJSONObject(0).getJSONArray("users").getJSONObject(0).getString("userName");
            StudentWork studentWork = new StudentWork(tmpsId, jsonObject.getString("name"), userId, userName);
            map.put(tmpsId, studentWork);
        }

        //studnetworkid:答案
        Map<String, String> resultMap = Maps.newHashMapWithExpectedSize(resultsArray.size());
        for (int i = 0, len = resultsArray.size(); i < len; i++) {
            JSONObject jsonObject = resultsArray.getJSONObject(i);
            String result = jsonObject.getJSONArray("topicResults").getJSONObject(1).getString("result");
            resultMap.put(jsonObject.getString("_id"), result);
        }

        //答案：学生作业集合
//    Map<String, List<StudentWork>> map3 = Maps.newHashMap();
////    for (Map.Entry<String, String> entry : resultMap.entrySet()) {
////      String studentWorkId = entry.getKey();
////      String result = entry.getValue().toLowerCase();
////      List<StudentWork> tmpList = Lists.newArrayList();
////      if (CollectionUtils.isNotEmpty(map3.get(result))) {
////        tmpList = map3.get(result);
////      }
////      tmpList.add(map.get(studentWorkId));
////      map3.put(result, tmpList);
////
////    }
        log.info("s5");


        //to excel
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("总计");

        Row topRow = sheet.createRow(0);
        topRow.createCell(0).setCellValue("作业名称");
        topRow.createCell(1).setCellValue("学生姓名");
        topRow.createCell(2).setCellValue("社团");

        int rowNum = 1;
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            Row row = sheet.createRow(rowNum);
            StudentWork studentWork = map.get(entry.getKey());
            row.createCell(0).setCellValue(studentWork.getName());
            row.createCell(1).setCellValue(studentWork.getUserName());
            row.createCell(2).setCellValue(entry.getValue());
            rowNum++;
        }

        try {
            FileOutputStream fis = new FileOutputStream("/Users/xiaoshuangyi/xuehai/云作业/临时文件/学生作业统计2.xlsx");
            workbook.write(fis);
            fis.close();
        } catch (Exception e) {
            log.error("ex", e);
        }

    }

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            log.error("ex:", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    log.error("ex2,", e1);
                }
            }
        }
        return sbf.toString();
    }

    public void simpleWrite() {
        Workbook wk = new HSSFWorkbook();


    }
}
