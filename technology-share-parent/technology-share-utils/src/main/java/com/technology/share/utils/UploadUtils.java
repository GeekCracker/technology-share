package com.technology.share.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {

    public static String scatterByYear(){
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    public static String scatterByMonth(){
        return new SimpleDateFormat("yyyy/MM").format(new Date());
    }

    public static String scatterByDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public static String scatterByHour(){
        return new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());
    }

    public static String scatterByMinutes(){
        return new SimpleDateFormat("yyyy/MM/dd/HH/mm").format(new Date());
    }

    public static String scatterBySeconds(){
        return new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(new Date());
    }

    /**
     * @param src 传入资源文件
     * @param target 传入压缩后的目标文件
     */
    public static void toSmallImg(File src, File target){
        try {
            //scale 尺寸缩放比例,0.1缩放10倍，outputQuality 文件大小缩放比例
            Thumbnails.of(src).scale(0.1f).outputQuality(0.1f).toFile(target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
