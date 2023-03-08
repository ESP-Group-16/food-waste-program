package com.example.espappversion2;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SignInTest {

    @Mock
    private FirebaseAuth mAuth;

    @Mock
    private Task<AuthResult> task;

    @Mock
    private FirebaseUser user;

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = new MainActivity();
    }

    @Test
    public void signInWithEmailAndPassword() {
        String email = "test@test.com";
        String password = "test1234";

        // mock the sign-in task
        when(mAuth.signInWithEmailAndPassword(email, password)).thenReturn(task);

        // mock the successful result of the sign-in task
        when(task.isSuccessful()).thenReturn(true);

        // mock the current user
        when(mAuth.getCurrentUser()).thenReturn(user);

        // call the sign-in method
        activity.signIn(email, password);

        // verify that the sign-in task was called with the correct arguments
        verify(mAuth).signInWithEmailAndPassword(email, password);

        // verify that the sign-in task was successful
        verify(task).isSuccessful();

        // verify that the current user is not null
        verify(mAuth).getCurrentUser();


    }
}