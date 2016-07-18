package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by windows 8.1 on 7/17/2016.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText email;
    private EditText password;
    private Button signUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up_layout);

        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        signUp = (Button) findViewById(R.id.button2);

        signUp.setOnClickListener(this);

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = auth.getCurrentUser();

                if (user != null) {

                    Log.d("User", "onAuthStateChanged:signed_in:" + user.getUid());
                    Log.d("User", "onAuthStateChanged:signed_in:" + firebaseAuth.getCurrentUser());

                } else {

                    Log.d("User", "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button2) {

            String email = this.email.getText().toString().trim();
            String password = this.password.getText().toString().trim();

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                Log.i("Exception", String.valueOf(task.getException()));
                            } else {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            }

                        }
                    });

        }

    }


    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authStateListener);

    }
}
