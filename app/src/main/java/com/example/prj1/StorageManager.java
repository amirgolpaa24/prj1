package com.example.prj1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StorageManager {



    public static void save(int lastNumber, Context context){
        try {
            FileOutputStream fos = context.openFileOutput("file", context.MODE_PRIVATE);
            fos.write(lastNumber);
            fos.close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }

    public static ArrayList load(Context context){
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput("file");
            int lastNumber;
            if (fis.available() == 0) {
                fis.close();
                return null;
            }
            else {
                lastNumber = fis.read();
                fis.close();
                for (int i = 1; i <= lastNumber; i++) {
                    arrayList.add(i);
                }
            }
            }
        catch(IOException e){
                e.getStackTrace();
            }
            return arrayList;
    }
}
