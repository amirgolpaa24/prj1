package com.example.prj1;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager extends Thread {

    static Context context;
    Object lock;
    Object runStorageThread;


    public StorageManager(Object runStorageThread, Object lock, Context context){
        this.runStorageThread = runStorageThread;
        this.lock = lock;
        this.context = context;
    }

    @Override
    public void run() {

        super.run();
        while (true){
                synchronized (runStorageThread){
                    try {
                        runStorageThread.wait();
                    }
                    catch (InterruptedException e){
                        e.getStackTrace();
                    }

                    if (MessageController.loadMethodIsCalled == false){
                        save(MessageController.theNumberThatMustBeSavedInFile, this.context);
                    }
                    else {
                        load(this.context);
                    }

                }

            synchronized (lock){
                lock.notify();
            }
        }

    }

    public static void save(int lastNumber, Context context){

        Log.i(MainActivity.TAG, "StorageManager method is running in:\n" +
                "pid: " + android.os.Process.myPid() + "\t" +
                "tid: " + android.os.Process.myTid());

        try {
            FileOutputStream fos = context.openFileOutput("file", context.MODE_PRIVATE);
            fos.write(lastNumber);
            fos.close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }



    public static void load(Context context){

        Log.i(MainActivity.TAG, "StorageManager method is running in:\n" +
                "pid: " + android.os.Process.myPid() + "\t" +
                "tid: " + android.os.Process.myTid());

        MessageController.cache = new ArrayList();


        int lastNumberInFile = 0;
        int lastNumberInLinearLayout;
        try {
                FileInputStream fis = context.openFileInput("file");
                if (fis.available() == 0) {
                    fis.close();
                    MessageController.cache = null;
                }
                else {
                    lastNumberInFile = fis.read();
                    fis.close();
                }
            }
        catch(IOException e){
                e.getStackTrace();
            }


        TextView textView = (TextView) MainActivity.linearLayout.getChildAt(MainActivity.linearLayout.getChildCount()-1);
        if (textView == null){

//            agar textView i dar linearLayout vojud nadashte bashad lastNumberInLinearLayout ra barabar
//            ba 0 dar nazar migirim:

            lastNumberInLinearLayout = 0;
        }
        else{

//            maghadire mojud dar textView ra dar nazar migirim va har kodam az aan ha ra dar yek khane
//            az araye minevisim

            String string = textView.getText().toString();
            String values[] = string.split(" ");

//            akharin adade mojud dar textView yani akharin khaneye araye ra dar lastNumberInLinearLayout
//            minevisim:

            lastNumberInLinearLayout = Integer.parseInt(values[9]);
        }

        if (lastNumberInLinearLayout < lastNumberInFile){
            for (int i = 1; i <= 10; i++){
                MessageController.cache.add(lastNumberInLinearLayout + i);
            }
        }
        else{

//            dar sorati k akharin adade chap shode dar linearLayour bozorgtar ya mosavie adade mojud
//            dar file bashad hich arayeyi barnemigardanad:

            MessageController.cache = null;
        }
    }
}
