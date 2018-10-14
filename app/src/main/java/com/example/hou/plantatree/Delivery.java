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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Delivery extends AppCompatActivity {
    private TextView price;
    private  TextView qty;
    private  TextView address;
    private  EditText phone;
    private  double totalValue;
    private  int totalAmount;
    private  ArrayAdapter<CharSequence> myAdapter;
    private ArrayList<Product> cart=new ArrayList<Product>();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private int totalCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_page);

        phone=findViewById(R.id.phone_delivery);
        address=findViewById(R.id.address_delivery);
        price=findViewById(R.id.totalprice_delivery);
        qty=findViewById(R.id.qty_delivery);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        SharedPreferences sharedPreferences_laod= getSharedPreferences(firebaseUser.getEmail()+"shared",MODE_PRIVATE);
        Gson gson_laod=new Gson();
        String json_load=sharedPreferences_laod.getString("cart list",null);
        Type type=new TypeToken<ArrayList<Product>>(){}.getType();
        cart = gson_laod.fromJson(json_load,type);

        String total_load=sharedPreferences_laod.getString("total consume",null);
        Type total_type=new TypeToken<Integer>(){}.getType();
        if(total_load==null)total_load="0";
        totalCon=Integer.valueOf(total_load);

        if(cart==null){
            cart=new ArrayList<Product>();
        }

        for(int i=0;i<cart.size();++i){
            totalValue+=Integer.valueOf(cart.get(i).price)*Integer.valueOf(cart.get(i).qty);
            if(totalCon>9)totalValue=totalValue*0.9;
            totalAmount+=Integer.valueOf(cart.get(i).qty);
        }

        price.setText("$ "+String.valueOf(totalValue));
        qty.setText(String.valueOf(totalAmount));
    }

    public void deliveryBackOnClick(View v){
        Intent intent =new Intent(Delivery.this,Purchase_select.class);
        startActivity(intent);
    }

    public void deliveryConfrimOnClick(View v){
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

        Intent intent =new Intent(Delivery.this,Invoice.class);
        intent.putExtra("address","Delivery to "+ address.getText());
        intent.putExtra("phone",String.valueOf(phone.getText()));
        intent.putExtra("totalPrice",String.valueOf(totalValue));
        intent.putExtra("qty",String.valueOf(totalAmount));
        startActivity(intent);
    }
}
