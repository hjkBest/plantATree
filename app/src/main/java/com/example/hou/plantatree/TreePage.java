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

        ImageButton olive =(ImageButton)findViewById(R.id.button_olive);
        olive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeOlive.class);
                startActivity(intent);
            }
        });

        ImageButton kauri =(ImageButton)findViewById(R.id.button_kauri);
        kauri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeKauri.class);
                startActivity(intent);
            }
        });

        ImageButton titoki =(ImageButton)findViewById(R.id.button_titoki);
        titoki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeTitoki.class);
                startActivity(intent);
            }
        });

        ImageButton evergreen =(ImageButton)findViewById(R.id.button_evergreen);
        evergreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeTitoki.class);
                startActivity(intent);
            }
        });

        ImageButton fastigiata =(ImageButton)findViewById(R.id.button_fastigiata);
        fastigiata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeTitoki.class);
                startActivity(intent);
            }
        });

        ImageButton lemon=(ImageButton)findViewById(R.id.back_button_lemon);
        lemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, TreeTitoki.class);
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

        ImageButton cart1=(ImageButton)findViewById(R.id.shopping_button1);
        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreePage.this, Cart.class);
                startActivity(intent);
            };


        });


    }

    public void ToCart(View view){
        Intent intent=new Intent(TreePage.this,Cart.class);
        startActivity(intent);
    }
}
