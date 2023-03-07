package com.example.espappversion2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTest {

    private MainActivity mainActivity;

    @Mock
    private FirebaseAuth firebaseAuth;

    @Before
    public void setUp() {
        mainActivity = new MainActivity();
        mainActivity.mAuth = firebaseAuth;
    }

    @Test
    public void createAccount_shouldCreateUserWithGivenEmailAndPassword() {
        String email = "test@example.com";
        String password = "password123";

        // mock the FirebaseAuth.createUserWithEmailAndPassword method
        Task<AuthResult> mockedTask = mock(Task.class);
        when(firebaseAuth.createUserWithEmailAndPassword(email, password)).thenReturn(mockedTask);

        // call the method under test
        mainActivity.createAccount(email, password);

        // verify that the FirebaseAuth.createUserWithEmailAndPassword method was called
        verify(firebaseAuth).createUserWithEmailAndPassword(email, password);

        // verify that the OnCompleteListener was set on the Task
        verify(mockedTask).addOnCompleteListener(any(OnCompleteListener.class));
    }

}