package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class Invoice extends AppCompatActivity{

    private TextView date;
    private TextView account;
    private TextView address;
    private TextView qty;
    private TextView price;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_page);

        Calendar calendar= Calendar.getInstance();
        String getDate= DateFormat.getDateInstance().format(calendar.getTime());

        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        date=(TextView)findViewById(R.id.invoice_date);
        account=(TextView)findViewById(R.id.invoice_account);
        address=(TextView)findViewById(R.id.invoice_address);
        phone=(TextView)findViewById(R.id.invoice_phone);
        qty=(TextView)findViewById(R.id.invoice_qty);
        price=(TextView)findViewById(R.id.invoice_price);

        Intent intent=getIntent();

        date.setText("Date: "+getDate);
        account.setText("Account: "+user.getEmail());
        address.setText(intent.getExtras().getString("address"));
        phone.setText("Phone number: "+ intent.getExtras().getString("phone"));
        qty.setText("Amount: "+intent.getExtras().getString("qty"));
        price.setText("Price: "+intent.getExtras().getString("totalPrice"));

    }

    public void invoiceOnClick(View v){
        Intent intent =new Intent(Invoice.this,TreePage.class);
        startActivity(intent);
    }

}
