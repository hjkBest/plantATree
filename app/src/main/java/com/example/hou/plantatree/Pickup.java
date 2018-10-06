package com.example.hou.plantatree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Pickup extends AppCompatActivity {

    TextView price;
    TextView qty;
    Spinner spinner;
    TextView address;
    EditText phone;
    int totalValue;
    int totalAmount;
    ArrayAdapter<CharSequence> myAdapter;
    ArrayList<Product> cart=new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_page);

        phone=findViewById(R.id.phone_pickup);
        address=findViewById(R.id.address_pickup);
        price=findViewById(R.id.totalprice_pickup);
        qty=findViewById(R.id.qty_pickup);
        spinner =findViewById(R.id.branch_spinner);

        myAdapter=ArrayAdapter.createFromResource(this,R.array.brands,android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    address.setText("55 Wellesley Street East,Auckland Central");
                }
                else if(position==1){
                    address.setText("90 Akoranga Drive Northcote, Auckland");
                }
                else if(position==2){
                    address.setText("640 Great South Rd Manukau, Auckland");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        for(int i=0;i<cart.size();++i){
            totalValue+=Integer.valueOf(cart.get(i).price)*Integer.valueOf(cart.get(i).qty);
            totalAmount+=Integer.valueOf(cart.get(i).qty);
        }

        price.setText("$ "+String.valueOf(totalValue));
        qty.setText(String.valueOf(totalAmount));
    }

    public void pickBackOnClick(View v){
        Intent intent =new Intent(Pickup.this,Purchase_select.class);
        startActivity(intent);
    }
}
