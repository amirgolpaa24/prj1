package com.example.prj1;

import android.content.Context;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConnectionManager {

    public static ArrayList load(Context context){

//        yek araye baraye bargardandane dar khoroji dar nazar migirim:

        ArrayList<Integer> arrayList = new ArrayList<>();

        try {

//            file dar internal storage e device ra mikhanim:

            FileInputStream fis = context.openFileInput("file");
            int lastNumber;

//            agar file khali bashad dar lastNumber ra barabare 0 gharar midahim:
            
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
