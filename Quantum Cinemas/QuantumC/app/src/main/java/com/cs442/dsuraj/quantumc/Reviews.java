package com.cs442.dsuraj.quantumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Reviews extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ArrayList<String> reviews= new ArrayList<String>();
    ArrayAdapter menu_Adapter;
    static int i=0;
    int counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        menu_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,reviews);
        final ListView listreview= (ListView)findViewById(R.id.reviewlist);
        final EditText userentry = (EditText)findViewById(R.id.userreview);
        listreview.setAdapter(menu_Adapter);

        mDatabase.child("Reviews").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reviews.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String b=snapshot.getValue().toString();
                    reviews.add(b);
                    counter++;

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button b= (Button)findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(userentry.getText().length() == 0)
                {
                    Toast.makeText(Reviews.this, "Enter Your Review Please", Toast.LENGTH_SHORT).show();
                }
                else {

                    String rev= "Review"+String.valueOf(counter);
                    String temp = userentry.getText().toString();
                    mDatabase.child("Reviews").child(rev).setValue(temp);
                    reviews.add(temp);
                    menu_Adapter.notifyDataSetChanged();
                    counter++;
                }
            }
        });
    }
}
