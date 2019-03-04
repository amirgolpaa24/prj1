package com.example.prj1;

import android.content.Intent;

public interface RepositoryObserver{
    void onUserDataChanged(Intent intent);
}