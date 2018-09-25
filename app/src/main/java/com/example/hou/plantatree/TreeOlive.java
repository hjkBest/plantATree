package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class TreeOlive extends AppCompatActivity {
    private EditText amount;
    private Button add;
    private SeekBar height_seek;
    private SeekBar age_seek;
    private TextView height_text;
    private TextView age_text;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_olive);
        setTitle("Olive");

        amount=findViewById(R.id.amount_olive);
        add=findViewById(R.id.add_cart_olive);
        age_seek=findViewById(R.id.age_seakbar_olice);
        height_seek=findViewById(R.id.height_seakbar_olice);
        age_text=findViewById(R.id.age_textview_olive);
        height_text=findViewById(R.id.height_textview_olive);


        amount.addTextChangedListener(inputTextWatch);

        ImageButton back_olive=(ImageButton)findViewById(R.id.back_button_olive);
        back_olive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TreeOlive.this, TreePage.class);
                startActivity(intent);
            }
        });

        age_seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        age_text.setText("Age: "+progress+" year");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        height_seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        height_text.setText("Height: "+progress+"m");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


    }

    public void clickAdd(View view){
        String name="Olive";
        String price="12";
        String qty=amount.getText().toString();

        Intent intent=new Intent(TreeOlive.this,Cart.class);

        intent.putExtra("Name",  name);
        intent.putExtra("Price", price);
        intent.putExtra("QTY", qty);
        startActivity(intent);

    }

    private TextWatcher inputTextWatch=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String amountInput=amount.getText().toString();

            add.setEnabled(!amountInput.isEmpty()&&!amountInput.contains("-"));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
