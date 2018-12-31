package com.example.avgor.myapplication;

import android.content.Context;

import java.text.DecimalFormat;

public class Format {

   public String double4index(double x, Context context){
        String string = new DecimalFormat
                (context.getResources()
                        .getString(R.string.double_format))
                .format(x);
        return string;
    }

//    String double4index(double x) {
//        String string = new DecimalFormat("#0.00").format(x);
//        return string;
//    }
}
