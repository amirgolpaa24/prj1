package com.example.prj1;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;

        import java.util.ArrayList;

public class NotificationCenter extends BroadcastReceiver {

    Intent data_loaded = new Intent();

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    public void data_loaded(ArrayList arrayList){
        MainActivity.createTextView(arrayList);
    }
}
