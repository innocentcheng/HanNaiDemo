package com.example.scada.hannaidemo.untils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Scada on 2017/10/19.
 */

public class Tools {
    public static void toastS(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void toastS(Context context,int id){
        Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
    }

    public static void logD(String msg){
        Log.d("HN",msg);
    }

    public static void  closeStream(Closeable stream){
        try {
            if (stream!=null){
                stream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int dip(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }
}
