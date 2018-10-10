package com.example.hou.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class Search extends AppCompatActivity{
    private static final String[] TREES = new String[]{
            "Olive","Kauri","Titoki","Mayer Lemon", "Fastigiata", "Evergreen Alder"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button searchButton = (Button)findViewById(R.id.search_button);

        final AutoCompleteTextView searchBar = findViewById(R.id.searchbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, TREES);
        searchBar.setAdapter(adapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String searchTerm;
                searchTerm = searchBar.getText().toString();
                search(searchTerm);
            }
        });
    }

    void search(String searchValue)
    {
        if(searchValue.equalsIgnoreCase(TREES[0]))
        {
            Intent intent=new Intent(Search.this, TreeOlive.class);
            startActivity(intent);
        }
        else if(searchValue.equalsIgnoreCase(TREES[1])){
            Intent intent=new Intent(Search.this, TreeKauri.class);
            startActivity(intent);
        }
        else if(searchValue.equalsIgnoreCase(TREES[2])){
            Intent intent=new Intent(Search.this, TreeTitoki.class);
            startActivity(intent);
        }
        else if(searchValue.equalsIgnoreCase(TREES[3])){
            Intent intent=new Intent(Search.this, TreeLemon.class);
            startActivity(intent);
        }
        else if(searchValue.equalsIgnoreCase(TREES[4])){
            Intent intent=new Intent(Search.this, TreeFastigiata.class);
            startActivity(intent);
        }
        else if(searchValue.equalsIgnoreCase(TREES[5])){
            Intent intent=new Intent(Search.this, TreeEvergreen.class);
            startActivity(intent);
        }
    }
}