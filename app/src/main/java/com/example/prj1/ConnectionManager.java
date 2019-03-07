package com.example.prj1;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConnectionManager extends Thread{


    Object runCloudThread;
    Object lock;
    Context context;

    public ConnectionManager(Object runCloudThread, Object lock, Context context){
        this.runCloudThread = runCloudThread;
        this.lock = lock;
        this.context = context;
    }

    @Override
    public void run() {

        super.run();
        while (true){
                synchronized (runCloudThread){
                    try {
                        runCloudThread.wait();
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }

                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }

                    load(context);

                }

            synchronized (lock){
                lock.notify();
            }
        }

    }

    public static void load(Context context){

        Log.i(MainActivity.TAG, "ConnectionManager method is running in:\n" +
                "pid: " + android.os.Process.myPid() + "\t" +
                "tid: " + android.os.Process.myTid());

        MessageController.cache = new ArrayList();

        try {

            FileInputStream fis = context.openFileInput("file");
            int lastNumber;

//            agar file khali bashad lastNumber ra barabare 0 gharar midahim:

            if (fis.available() == 0) {
                lastNumber = 0;
            }

            else {
                lastNumber = fis.read();
            }
             fis.close();

             for (int i = 1; i <= 10; i++) {
                MessageController.cache.add(lastNumber + i);
             }
        }
        catch(IOException e){
            e.getStackTrace();
        }
    }
}