package com.google.firebase.codelab.friendlychat.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.codelab.friendlychat.MainActivity;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by windows 8.1 on 7/20/2016.
 */
public class Settings extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private Button save;

    //Database
    private DatabaseReference root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        name= (EditText)findViewById(R.id.editText8);
        save=(Button)findViewById(R.id.button4);
        root= FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button4){

            String name=this.name.getText().toString();
            SharedPreferences.Editor editor=MainActivity.userDetailsHolder.edit();
            editor.putString("Name",name);
            editor.commit();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
