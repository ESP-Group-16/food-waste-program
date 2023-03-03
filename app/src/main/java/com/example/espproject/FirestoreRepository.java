package com.example.espproject;

public interface FirestoreRepository {
    void saveData(String collectionName, Object data, FirestoreRepositoryCallback callback);
    void getData(String collectionName, String documentId, FirestoreRepositoryCallback callback);
}
