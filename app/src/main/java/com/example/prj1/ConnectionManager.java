package com.example.prj1;

import android.content.Context;
import java.io.FileInputStream;
import java.io.IOException;
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
