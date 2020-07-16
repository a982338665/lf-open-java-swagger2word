package com.github.lfopenjavaswagger2word.util;

import java.io.*;

public class TextUtil {


    public static void write(String file, String content) {
        File wFile = new File(file);
        try (
                //打开文件
                //创建FileWriter
                FileWriter writer = new FileWriter(wFile);
                //使用BufferedWriter加速
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
        ) {
            //写入
            bufferedWriter.write(content);
            //刷新缓冲区
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
    }

    public static String reader(String file) {
        File myFile = new File(file);
        try(
                //创建FileReader
                FileReader fileReader = new FileReader(myFile);
                //使用BufferedReader加速
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                ) {
            //打开文件
            //逐行读取文本
            String lineString = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((lineString = bufferedReader.readLine()) != null) {
//                System.out.println(lineString);
                stringBuilder.append(lineString);
            }
//            System.err.println(stringBuilder.toString());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
        return null;

    }

}
