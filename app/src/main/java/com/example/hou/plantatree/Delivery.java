package com.example.hou.plantatree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Delivery extends AppCompatActivity {
    TextView price;
    TextView qty;
    TextView address;
    EditText phone;
    int totalValue;
    int totalAmount;
    ArrayAdapter<CharSequence> myAdapter;
    ArrayList<Product> cart=new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_page);

        phone=findViewById(R.id.phone_delivery);
        address=findViewById(R.id.address_delivery);
        price=findViewById(R.id.totalprice_delivery);
        qty=findViewById(R.id.qty_delivery);

        SharedPreferences sharedPreferences_laod= getSharedPreferences("shared",MODE_PRIVATE);
        Gson gson_laod=new Gson();
        String json_load=sharedPreferences_laod.getString("cart list",null);
        Type type=new TypeToken<ArrayList<Product>>(){}.getType();
        cart = gson_laod.fromJson(json_load,type);



        if(cart==null){
            cart=new ArrayList<Product>();
        }

        for(int i=0;i<cart.size();++i){
            totalValue+=Integer.valueOf(cart.get(i).price)*Integer.valueOf(cart.get(i).qty);
            totalAmount+=Integer.valueOf(cart.get(i).qty);
        }

        price.setText("$ "+String.valueOf(totalValue));
        qty.setText(String.valueOf(totalAmount));
    }

    public void deliveryBackOnClick(View v){
        Intent intent =new Intent(Delivery.this,Purchase_select.class);
        startActivity(intent);
    }
}
