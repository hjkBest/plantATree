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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Pickup extends AppCompatActivity {

    private TextView price;
    private TextView qty;
    private Spinner spinner;
    private TextView address;
    private EditText phone;
    private double totalValue;
    private int totalAmount;
    private ArrayAdapter<CharSequence> myAdapter;
    private ArrayList<Product> cart=new ArrayList<Product>();
    private FirebaseAuth firebaseAuth;
    private  FirebaseUser firebaseUser;
    private int totalCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_page);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

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

        SharedPreferences sharedPreferences_laod= getSharedPreferences(firebaseUser.getEmail()+"shared",MODE_PRIVATE);
        Gson gson_laod=new Gson();
        String json_load=sharedPreferences_laod.getString("cart list",null);
        Type type=new TypeToken<ArrayList<Product>>(){}.getType();
        cart = gson_laod.fromJson(json_load,type);
        if(cart==null){
            cart=new ArrayList<Product>();
        }

        String total_load=sharedPreferences_laod.getString("total consume",null);
        Type total_type=new TypeToken<Integer>(){}.getType();
        if(total_load==null)total_load="0";
        totalCon=Integer.valueOf(total_load);

        for(int i=0;i<cart.size();++i){
            totalValue+=Integer.valueOf(cart.get(i).price)*Integer.valueOf(cart.get(i).qty);
            if(totalCon>9)totalValue=totalValue*0.9;
            totalAmount+=Integer.valueOf(cart.get(i).qty);
        }

        price.setText("$ "+String.valueOf(totalValue));
        qty.setText(String.valueOf(totalAmount));
    }

    public void pickBackOnClick(View v){
        Intent intent =new Intent(Pickup.this,Purchase_select.class);
        startActivity(intent);
    }

    public void pickConfrimOnClick(View v){
        totalCon+=totalAmount;
        cart = new ArrayList<Product>();
        SharedPreferences sharedPreferences= getSharedPreferences(firebaseUser.getEmail()+"shared",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson_save=new Gson();
        String json_cart = gson_save.toJson(cart);
        String json_total=gson_save.toJson(totalCon);
        editor.putString("cart list",json_cart);
        editor.putString("total consume",json_total);
        editor.apply();

        Intent intent =new Intent(Pickup.this,Invoice.class);
        intent.putExtra("address","Pick up from "+address.getText());
        intent.putExtra("phone",String.valueOf(phone.getText()));
        intent.putExtra("totalPrice",String.valueOf(totalValue));
        intent.putExtra("qty",String.valueOf(totalAmount));

        startActivity(intent);
    }

}
