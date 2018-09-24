package com.example.hou.plantatree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Cart extends AppCompatActivity{

    ArrayList<Product> cart=new ArrayList<Product>();

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.cart_page);

        Button b1=(Button)findViewById(R.id.back_cart);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Cart.this,TreePage.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences_laod= getSharedPreferences("shared",MODE_PRIVATE);
        Gson gson_laod=new Gson();
        String json_load=sharedPreferences_laod.getString("cart list",null);
        Type type=new TypeToken<ArrayList<Product>>(){}.getType();
        cart = gson_laod.fromJson(json_load,type);

        if(cart==null){
            cart=new ArrayList<Product>();
        }


        Intent intent = getIntent();
        String qty=intent.getStringExtra("QTY");
        String name=intent.getStringExtra("Name");
        String price = intent.getStringExtra("Price");

        Product product=new Product();
        product.setAttribute(qty,name,price);

        cart.add(product);

        SharedPreferences sharedPreferences= getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson_save=new Gson();
        String json_save = gson_save.toJson(cart);
        editor.putString("cart list",json_save);
        editor.apply();
        page();

    }



    public void page(){
        GridLayout grid=(GridLayout)findViewById(R.id.grid_layout);

        for(int r=0; r<cart.size(); r++){

            GridLayout.LayoutParams paramName =new GridLayout.LayoutParams();
            GridLayout.LayoutParams paramQTY =new GridLayout.LayoutParams();
            GridLayout.LayoutParams paramPrice =new GridLayout.LayoutParams();

            TextView view_name = new TextView(this);
            view_name.setWidth(150);
            view_name.setText(cart.get(r).name);
            grid.addView(view_name);
            paramName.columnSpec=GridLayout.spec(0);
            paramName.rowSpec=GridLayout.spec(r+1);
            view_name.setLayoutParams(paramName);

            TextView view_qty= new TextView(this);
            view_qty.setText(String.valueOf(cart.get(r).qty));
            grid.addView(view_qty);
            paramQTY.columnSpec=GridLayout.spec(1);
            paramQTY.rowSpec=GridLayout.spec(r+1);
            view_qty.setLayoutParams(paramQTY);

            TextView view_price= new TextView(this);
            view_price.setText(String.valueOf(cart.get(r).price));
            grid.addView(view_price);
            paramPrice.columnSpec=GridLayout.spec(2);
            paramPrice.rowSpec=GridLayout.spec(r+1);
            view_price.setLayoutParams(paramPrice);
        }
    }


}
