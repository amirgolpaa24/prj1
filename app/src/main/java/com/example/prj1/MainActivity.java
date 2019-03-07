package com.example.prj1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RepositoryObserver {

    protected static final String TAG = "Project 1";

    NotificationCenter notificationCenter = NotificationCenter.getInstance();
    MessageController messageController = new MessageController(this);
    TextView textView;
    static LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        notificationCenter.registerObserver(this);


        messageController.cloud.start();
        messageController.storage.start();


//        ********************************************************
//        use this codes for create file


//        try {
//            FileOutputStream fileOutputStream = this.openFileOutput("file", MODE_PRIVATE);
//            fileOutputStream.write(null);
//            fileOutputStream.close();
//        }catch (Exception e){
//            e.getStackTrace();
//        }


//        ********************************************************
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        notificationCenter.unregisterObserver(this);

    }

    public void clearIntent(View view){
        linearLayout.removeAllViews();
    }
    public void refreshIntent(View view){
        ArrayList arrayList = messageController.fetch(true);
        if (arrayList != null){
            notificationCenter.data_loaded(arrayList);
        }

    }
    public void getIntent(View view){
        notificationCenter.data_loaded(messageController.fetch(false));
    }

    @Override
    public void onUserDataChanged(Intent intent) {
        textView = new TextView(this);

        StringBuilder stringBuilder = new StringBuilder();
        int[] array = intent.getIntArrayExtra("values");
        for (int i = 0; i < intent.getIntArrayExtra("values").length; i++){
            stringBuilder.append(array[i] + " ");
        }
        textView.setText(stringBuilder);
        linearLayout.addView(textView);
    }
}
