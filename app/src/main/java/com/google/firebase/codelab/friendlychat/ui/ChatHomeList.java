package com.google.firebase.codelab.friendlychat.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.codelab.friendlychat.MainActivity;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.UserDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by windows 8.1 on 7/21/2016.
 */
public class ChatHomeList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference rootRef;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_home);
        listView=(ListView)findViewById(R.id.list);  //getting the ID of ListView

        auth=FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();
        user= auth.getCurrentUser();

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter=new ArrayAdapter(this,R.layout.online_offline,R.id.textView4,arrayList);
        listView.setAdapter(adapter);

        rootRef.child("USER").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserDetails userDetails=dataSnapshot.getValue(UserDetails.class);
                Log.i("name",userDetails.getName());
                arrayList.add(userDetails.getName());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
