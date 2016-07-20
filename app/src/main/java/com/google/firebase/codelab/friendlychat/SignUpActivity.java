package com.google.firebase.codelab.friendlychat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by windows 8.1 on 7/17/2016.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private FirebaseAnalytics analytics;
    private DatabaseReference databaseRef;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText email;
    private EditText password;
    private EditText name;
    private Button signUp;
    private Button cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up_layout);

        auth = FirebaseAuth.getInstance();
        databaseRef= FirebaseDatabase.getInstance().getReference();
        analytics=FirebaseAnalytics.getInstance(this);

        name = (EditText) findViewById(R.id.editText5);
        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        signUp = (Button) findViewById(R.id.button2);
        cancel=(Button)findViewById(R.id.button3);

        signUp.setOnClickListener(this);
        cancel.setOnClickListener(this);

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

            final String name=this.name.getText().toString().trim();
            final String email = this.email.getText().toString().trim();
            final String password = this.password.getText().toString().trim();

            if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Enter Name, Email and Password",Toast.LENGTH_LONG).show();
            }
            else{

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                    Log.i("Exception", String.valueOf(task.getException()));
                                } else {
                                    //writeNewuser(name,email);
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }

                            }
                        });
                //databaseRef.child("User Details").push();

            }

        }
        if(v.getId()==R.id.button3){

            Log.i("Cancel","Cancel Clicked");
            Intent intent=new Intent(this,SignInActivity.class);
            startActivity(intent);
        }

    }

    private void ShowErrorMessage(String s) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
//        builder.setMessage(s);
//        builder.setTitle("Error");
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dismissDialog(which);
//            }
//        });
//        builder.setCancelable(false);
//        Dialog dialog=builder.create();
//        dialog.show();
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authStateListener);

    }


}
