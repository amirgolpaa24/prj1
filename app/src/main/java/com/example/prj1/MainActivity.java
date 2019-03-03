package com.example.prj1;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NotificationCenter notificationCenter = new NotificationCenter();
    MessageController messageController = new MessageController();
    static LinearLayout linearLayout;
    static ArrayList<Integer> arrayList = new ArrayList<>();
    static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        textView = new TextView(this);
        registerReceiver(notificationCenter, new IntentFilter("data_loaded"));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(notificationCenter);

    }

    public void clearIntent(View view){
        linearLayout.removeAllViews();
        MainActivity.arrayList.clear();
        MessageController.cache.clear();
        sendBroadcast(new Intent("Linear layout cleared"));
    }
    public void refreshIntent(View view){
        arrayList = messageController.fetch(true, this);
        notificationCenter.data_loaded(arrayList);
    }
    public void getIntent(View view){
        arrayList = messageController.fetch(false, this);
        notificationCenter.data_loaded(arrayList);
    }
    public static void createTextView(ArrayList<Integer> arrayList){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++){
            stringBuilder.append(arrayList.get(i));
        }
        textView.setText(stringBuilder);
        linearLayout.addView(textView);
    }
}
