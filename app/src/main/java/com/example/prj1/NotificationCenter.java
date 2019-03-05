package com.example.prj1;

import android.content.Intent;
import java.util.ArrayList;

public class NotificationCenter implements Subject {

    static Intent data_loaded = new Intent();
    private static NotificationCenter INSTANCE = null;
    private ArrayList<RepositoryObserver> mObservers;

    private NotificationCenter() {
        mObservers = new ArrayList<>();
    }

//    notificationCenter ra singleton tarif mikonim:

    public static NotificationCenter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new NotificationCenter();
        }
        return INSTANCE;
    }

    @Override
    public void registerObserver(RepositoryObserver repositoryObserver) {
        if(!mObservers.contains(repositoryObserver)) {
            mObservers.add(repositoryObserver);
        }
    }

    @Override
    public void unregisterObserver(RepositoryObserver repositoryObserver) {
        if(mObservers.contains(repositoryObserver)) {
            mObservers.remove(repositoryObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for (RepositoryObserver observer: mObservers) {
            observer.onUserDataChanged(data_loaded);
        }
    }

    public void data_loaded(ArrayList<Integer> arrayList){
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++){
            array[i] = arrayList.get(i);
        }

//        be data_loaded araye ra midahim:

        data_loaded.putExtra("values", array);
        notifyObservers();
    }
}