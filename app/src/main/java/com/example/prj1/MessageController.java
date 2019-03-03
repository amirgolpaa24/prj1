package com.example.prj1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class MessageController {

    static ArrayList<Integer> cache = new ArrayList<>();

    public static ArrayList fetch(boolean fromCatch, Context context){
        if (fromCatch == true){
            ArrayList<Integer> arrayList = StorageManager.load(context);
            for (int i = 0; i < 10; i++){
                cache.add(arrayList.get(i));
            }
            return arrayList;
        }
        else{
            ArrayList<Integer> arrayList = ConnectionManager.load(context);
            for (int i = 0; i < 10; i++) {
                cache.add(arrayList.get(i));
                if (i == 9){
                    StorageManager.save(arrayList.get(i), context);
                }
            }
            return arrayList;
        }
    }
}
