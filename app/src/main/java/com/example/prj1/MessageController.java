package com.example.prj1;

import android.content.Context;
import java.util.ArrayList;

public class MessageController {

    static ArrayList<Integer> cache = new ArrayList<>();

    public static void fetch(boolean fromCatch, Context context){
        if (fromCatch == true){
            if (StorageManager.load(context) != null){
                ArrayList<Integer> arrayList = StorageManager.load(context);
                for (int i = 0; i < arrayList.size(); i++){
                    cache.add(arrayList.get(i));
                }
                NotificationCenter.getInstance().data_loaded(arrayList);
            }
        }
        else{
            ArrayList<Integer> arrayList = ConnectionManager.load(context);
            for (int i = 0; i < 10; i++) {
                cache.add(arrayList.get(i));
                if (i == 9){
                    StorageManager.save(arrayList.get(i), context);
                }
            }
            NotificationCenter.getInstance().data_loaded(arrayList);
        }
    }
}