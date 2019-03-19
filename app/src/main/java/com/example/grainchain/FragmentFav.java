package com.example.grainchain;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentFav extends Fragment {
    EditText edtName, edtNum;
    Button saveContact;
    View v;
    FragmentContact fragmentContact = new FragmentContact();

    public FragmentFav(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fav_fragment, container, false);

        edtName = (EditText)v.findViewById(R.id.your_name);
        edtNum = (EditText)v.findViewById(R.id.your_num);
        saveContact = (Button) v.findViewById(R.id.save_contact);

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Elemento añadido", Toast.LENGTH_SHORT).show();
                fragmentContact.addItem(edtName.getText().toString(),edtNum.getText().toString());
                edtName.setText("");
                edtNum.setText("");
            }
        });

        return v;
    }
}
