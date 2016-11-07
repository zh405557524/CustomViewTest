package com.bdxh;

import android.graphics.Bitmap;

/**
 *
 * Created by bdxh on 2016/7/22.
 */
public class Blur {

    static {
        System.loadLibrary("bdxhblur");
    }


    public static Bitmap blurBitmap(Bitmap original, int radius,boolean canReuseInBitmap){

        if (radius < 1) {
            return null;
        }

        if(original == null){
            throw new NullPointerException("source blur bitmap is null");
        }

        //获取bitmap config 只支持Bitmap.Config.ARGB_8888 and Bitmap.Config.RGB_565
        Bitmap.Config config = original.getConfig();
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new RuntimeException("source blur bitmap config error.");
        }

        Bitmap bitmap ;

        if (canReuseInBitmap) {
            bitmap = original;
        } else {
            bitmap = original.copy(config, true);
        }


        if (radius == 1) {
            return bitmap;
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        //most
        int[] pix = new int[w * h];

        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        blur(pix,w,h,radius);

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return bitmap;
    }

    public static native void blur(int[] img, int w, int h, int r);
}
