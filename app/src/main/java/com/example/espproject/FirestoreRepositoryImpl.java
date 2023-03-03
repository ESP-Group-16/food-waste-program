package com.example.espproject;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreRepositoryImpl implements FirestoreRepository {
    private FirebaseFirestore mFirestore;

    public FirestoreRepositoryImpl() {
        mFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void saveData(String collectionName, Object data, FirestoreRepositoryCallback callback) {
        mFirestore.collection(collectionName).add(data).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(null);
            } else {
                callback.onFailure(task.getException());
            }
        });
    }

    @Override
    public void getData(String collectionName, String documentId, FirestoreRepositoryCallback callback) {
        mFirestore.collection(collectionName).document(documentId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                callback.onSuccess(documentSnapshot);
            } else {
                callback.onFailure(task.getException());
            }
        });
    }
}

