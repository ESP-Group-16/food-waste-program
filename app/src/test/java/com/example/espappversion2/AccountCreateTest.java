package com.example.espappversion2;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTest {

    @Mock
    private FirebaseAuth mAuth;

    private MainActivity mActivity;

    @Before
    public void setUp() {
        mActivity = new MainActivity();
        mActivity.setAuth(mAuth);
    }

    @Test
    public void testCreateAccount_Success() {
        String email = "test@example.com";
        String password = "password123";
        when(mAuth.createUserWithEmailAndPassword(email, password))
                .thenReturn(new MockTask<AuthResult>(new MockAuthResult()));

        mActivity.createAccount(email, password);

        verify(mAuth).createUserWithEmailAndPassword(email, password);
        verify(mAuth).getCurrentUser();
        assertNotNull(mAuth.getCurrentUser());
    }
}