package com.example.prj1;

public interface Subject {
    void registerObserver(RepositoryObserver repositoryObserver);
    void unregisterObserver(RepositoryObserver repositoryObserver);
    void notifyObservers();
}