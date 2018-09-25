package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class TreePage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.trees_page);
        setTitle("Tree Menu");
        //setImage();

        ImageButton o1=(ImageButton)findViewById(R.id.button_olive);
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeOlive.class);
                startActivity(intent);
            }
        });

        ImageButton k1=(ImageButton)findViewById(R.id.button_kauri);
        k1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeKauri.class);
                startActivity(intent);
            }
        });

        ImageButton back1=(ImageButton)findViewById(R.id.back_button1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
