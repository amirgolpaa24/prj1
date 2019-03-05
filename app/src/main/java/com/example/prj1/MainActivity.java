package com.example.prj1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements RepositoryObserver {

    NotificationCenter notificationCenter = NotificationCenter.getInstance();
    MessageController messageController = new MessageController();
    TextView textView;
    static LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        notificationCenter.registerObserver(this);


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
        messageController.fetch(true, this);

    }
    public void getIntent(View view){
        messageController.fetch(false, this);
    }

    @Override
    public void onUserDataChanged(Intent intent) {
        textView = new TextView(this);

//        maghadire mojud dar intent e data_loaded ra dar stringBuilder minevisim:

        StringBuilder stringBuilder = new StringBuilder();
        int[] array = intent.getIntArrayExtra("values");
        for (int i = 0; i < intent.getIntArrayExtra("values").length; i++){
            stringBuilder.append(array[i] + " ");
        }
        textView.setText(stringBuilder);
        linearLayout.addView(textView);
    }
}
