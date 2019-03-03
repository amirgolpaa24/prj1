package com.example.prj1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConnectionManager {

    public static ArrayList load(Context context){

        ArrayList<Integer> arrayList = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput("file");
            int lastNumber;
            if (fis.available() == 0) {
                lastNumber = 0;
            }
            else {
                lastNumber = fis.read();
             }
             fis.close();
             for (int i = 1; i <= 10; i++) {
                arrayList.add(lastNumber + i);
             }
        }
        catch(IOException e){
            e.getStackTrace();
        }
        return arrayList;
    }
}
