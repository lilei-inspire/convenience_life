package com.ls.util.common;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtil {

    private final static String SAVE_PATH_CIRCLE = "D:\\Data\\图片\\用户相册\\";
    private final static String SAVE_PATH_GOODS = "D:\\Data\\图片\\商品相册\\";


    /**
     * 图片转base64
     */
    public String imgToBase(String type, String filename) throws IOException {
        FileInputStream fileInputStream = null;
        if (type.equals("head")) {
            fileInputStream = new FileInputStream("D:\\Data\\图片\\用户头像\\" + filename);
        } else if(type.equals("picture")){
            fileInputStream = new FileInputStream("D:\\Data\\图片\\用户相册\\" + filename);
        }else {
            fileInputStream = new FileInputStream("D:\\Data\\图片\\商品相册\\" + filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        byte[] fileByte = bos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String data = encoder.encode(fileByte);
        return data;
    }

    /**
     * 是否是指定图片格式
     */
    public boolean isImg(String type) {
        if (type != null) {
            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 图片存放目录
     */
    public File getFile(String type) {
        String filePath = null;
        if (type.equals("circle")) {
            filePath = new String(SAVE_PATH_CIRCLE);
        }else if(type.equals("goods")){
            filePath = new String(SAVE_PATH_GOODS);
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

}
