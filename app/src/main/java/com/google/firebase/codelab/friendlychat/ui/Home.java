package com.google.firebase.codelab.friendlychat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.codelab.friendlychat.R;
//import com.google.firebase.codelab.friendlychat.adapters.UsersChatAdapter;
import com.google.firebase.codelab.friendlychat.models.UsersChatModel;

import java.util.ArrayList;

/**
 * Created by windows 8.1 on 7/25/2016.
 */
public class Home extends AppCompatActivity {

    //Firebase
    private Firebase rootReference;
    private Firebase userRef;

    //private UsersChatAdapter usersChatAdapter;
    //ListView
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_home);

        rootReference = new Firebase("https://friendly-chat-92579.firebaseio.com/");
        userRef = rootReference.child("USER");

        listView = (ListView) findViewById(R.id.list);

        ArrayList<UsersChatModel> usersChatModels=new ArrayList<>();

        //usersChatAdapter=new UsersChatAdapter(this,usersChatModels);



    }
}
