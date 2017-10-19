package com.example.scada.hannaidemo.untils;

import android.content.Context;
import android.util.Log;
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
}
