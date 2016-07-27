package com.google.firebase.codelab.friendlychat.ui;

import android.content.Context;
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
import com.google.firebase.codelab.friendlychat.UserDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by windows 8.1 on 7/20/2016.
 */
public class Settings extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private Button save;

    //Database
    private DatabaseReference root;

    //SharedPreference
    private SharedPreferences userDetailsHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        name= (EditText)findViewById(R.id.editText8);
        save=(Button)findViewById(R.id.button4);
        root= FirebaseDatabase.getInstance().getReference();

        userDetailsHolder=getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button4){

            String previousName=userDetailsHolder.getString("Name","name");
            String name=this.name.getText().toString();
            //HashMap<String,Object> hashMap=new HashMap<String, Object>();
            //hashMap.put("name",name);
            SharedPreferences.Editor editor=userDetailsHolder.edit();
            editor.putString("Name",name);
            editor.commit();

//            String key = root.child("USER").child(previousName).push().getKey();
//            UserDetails ud = new UserDetails(name, MainActivity.userDetailsHolder.getString(), title, body);
//            Map<String, Object> postValues = post.toMap();
//
//            Map<String, Object> childUpdates = new HashMap<>();
//            childUpdates.put("/posts/" + key, postValues);
//            childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
//
//            mDatabase.updateChildren(childUpdates);

            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
