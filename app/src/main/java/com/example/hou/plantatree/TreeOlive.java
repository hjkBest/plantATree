package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class TreeOlive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_olive);
        setTitle("Olive");

        ImageButton back_olive=(ImageButton)findViewById(R.id.back_button_olive);
        back_olive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreeOlive.this, TreePage.class);
                startActivity(intent);
            }
        });

    }

    public void clickAdd(View view){
        EditText number=(EditText) findViewById(R.id.quantive_olive);
        String name="Olive";
        String price="12";
        String qty=number.getText().toString();

        Intent intent=new Intent(TreeOlive.this,Cart.class);

        intent.putExtra("Name",  name);
        intent.putExtra("Price", price);
        intent.putExtra("QTY", qty);
        startActivity(intent);

    }

}
