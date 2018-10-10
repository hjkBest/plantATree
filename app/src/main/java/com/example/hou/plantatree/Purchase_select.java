package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Purchase_select extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_page);

        button=findViewById(R.id.select_confrim);
        radioGroup=findViewById(R.id.delivery_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(String.valueOf(checkedId)!=null){
                    button.setEnabled(true);
                }
            }
        });


    }

    public void selectConfirmClick(View v){
        int i=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(i);

        if(i==R.id.pickup_radioButton){
            Intent intent =new Intent(Purchase_select.this,Pickup.class);
            startActivity(intent);
        }
        else if(i==R.id.delivery_radioButton){
            Intent intent =new Intent(Purchase_select.this,Delivery.class);
            startActivity(intent);
        }

    }

    public void selectBackClick(View v){
        Intent intent =new Intent(Purchase_select.this,Cart.class);
        startActivity(intent);
    }
}
