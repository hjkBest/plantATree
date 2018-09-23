package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

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
}
