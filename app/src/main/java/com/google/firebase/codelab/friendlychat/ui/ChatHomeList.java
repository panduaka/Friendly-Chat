package com.google.firebase.codelab.friendlychat.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.codelab.friendlychat.MainActivity;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.SignInActivity;
import com.google.firebase.codelab.friendlychat.USerDetailsAlertDialog;
import com.google.firebase.codelab.friendlychat.UserDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    ArrayList<String> arrayList = new ArrayList<>();
    ProgressBar progressBar;

    //Firebase
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference rootRef;
    private ArrayAdapter<String> adapter;

    //SharedPreferences
    private SharedPreferences userDetailsHolder;
    private SharedPreferences firstRun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_home);

        listView = (ListView) findViewById(R.id.list);  //getting the ID of ListView
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        Log.i("Current", String.valueOf(user));
        if(user==null){
            Intent intent=new Intent(this, SignInActivity.class);
            startActivity(intent);
            return;
        }

        userDetailsHolder=getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        firstRun=getSharedPreferences("FirstRun", Context.MODE_PRIVATE);
        final String userID=userDetailsHolder.getString("Unique ID","ID");
        adapter=new ArrayAdapter<String>(this,R.layout.online_offline,R.id.textView4,arrayList);

//        rootRef.child("USER").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                UserDetails userDetails=dataSnapshot.getValue(UserDetails.class);
//                Log.i("name",userDetails.getName());
//                arrayList.add(userDetails.getName());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                UserDetails userDetails=dataSnapshot.getValue(UserDetails.class);
//                Log.i("name",userDetails.getName());
//                arrayList.add(userDetails.getName());
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        //progressBar.setVisibility(View.VISIBLE);

        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }


        rootRef.child("USER").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    UserDetails userDetails = postSnapshot.getValue(UserDetails.class);

                    String userId=userDetails.getUserID();

                    if (!userID.equals(userId)) {
                        arrayList.add(userDetails.getName());
                        //Log.i("name", userDetails.getName());
                    }
                    if(userID.equals(userId)){
                        Query ref=rootRef.child("USER").orderByChild("userID").equalTo(userID);
                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(adapter);
        Log.i("Finish","finish Online Users");
        listView.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        rootRef.child("USER").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        Log.i("FirstRun", String.valueOf(firstRun.getBoolean("FirstRun", true)));

        if (firstRun.getBoolean("FirstRun", true)) {

            USerDetailsAlertDialog uSerDetailsAlertDialog = new USerDetailsAlertDialog();
            uSerDetailsAlertDialog.show(getFragmentManager(), "UserInformation");

            SharedPreferences.Editor editor = firstRun.edit();
            editor.putBoolean("FirstRun", false);
            editor.commit();
        }




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView c = (TextView) view.findViewById(R.id.textView4);
        String friendName = c.getText().toString();
        Log.i("Name",friendName);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Name",friendName);
        startActivity(intent);
    }
}
