package com.example.espproject;

import com.google.firebase.firestore.DocumentSnapshot;

public interface FirestoreRepositoryCallback {
    void onSuccess(DocumentSnapshot documentSnapshot);
    void onFailure(Exception exception);
}
