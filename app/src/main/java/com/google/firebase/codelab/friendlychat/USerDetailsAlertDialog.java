package com.google.firebase.codelab.friendlychat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by windows 8.1 on 7/20/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class USerDetailsAlertDialog extends DialogFragment{

    GetUserInformation getUserInformation;
    String name;
    EditText nameText;
    String number;
    EditText numberText;
    String genderSelected;
    final CharSequence gender[]={"Male","Female"};
    Dialog dialog;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        getUserInformation= (GetUserInformation) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.user_info, null);
        builder.setView(v);
        builder.setTitle("User Information");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(gender,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case 0:
                        genderSelected="Male";
                        break;
                    case 1:
                        genderSelected="Female";
                        break;
                }

            }
        });

        //radioGroup=(RadioGroup)v.findViewById(R.id.radioGroup);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getContext(), "Information Saved", Toast.LENGTH_SHORT).show();
                nameText = (EditText) v.findViewById(R.id.editText6);
                name = nameText.getText().toString();

                numberText=(EditText)v.findViewById(R.id.editText7);
                number=numberText.getText().toString();
                Log.i("Info",number+name+genderSelected);
                getUserInformation.informationUpload(name,number,genderSelected);
                dialog.dismiss();

            }
        });
        dialog = builder.create();
        return dialog;
    }


    public interface GetUserInformation{

        void informationUpload(String name, String number, String genderSelected);
    }

}
