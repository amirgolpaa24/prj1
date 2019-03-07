package com.example.prj1;

import android.content.Context;
import java.util.ArrayList;

public class MessageController {

    Context context;
    static ArrayList cache;
    static boolean loadMethodIsCalled;
    static int theNumberThatMustBeSavedInFile;
    static Object runStorageThread = new Object();
    static Object runCloudThread = new Object();
    static Object lock = new Object();
    Thread cloud;
    Thread storage;

    public MessageController(Context context){
        this.context = context;
        ConnectionManager connectionManager = new ConnectionManager(runCloudThread, lock, context);
        StorageManager storageManager = new StorageManager(runStorageThread, lock, context);
        this.cloud = new Thread(connectionManager);
        this.storage = new Thread(storageManager);
    }


    public ArrayList fetch(boolean fromCache){

        if (fromCache == true){

            synchronized (lock){

                    loadMethodIsCalled = true;
                synchronized (runStorageThread){
                    runStorageThread.notify();
                }
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }
            }
        }

        else {

            synchronized (lock){

                synchronized (runCloudThread){
                    runCloudThread.notify();
                }
                try {
                    lock.wait();
                }
                catch (InterruptedException e){
                    e.getStackTrace();
                }

                        loadMethodIsCalled = false;
                        theNumberThatMustBeSavedInFile = (int) cache.get(9);

                        synchronized (runStorageThread){
                            runStorageThread.notify();
                        }
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException e){
                        e.getStackTrace();
                    }
            }
        }
        return this.cache;
    }
}