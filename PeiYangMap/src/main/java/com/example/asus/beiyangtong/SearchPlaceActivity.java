package com.example.asus.beiyangtong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class SearchPlaceActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    Button search;
    TextView lib, cafe, atm, wrongtag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_place);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.tag);
        search = (Button)findViewById(R.id.search_button);
        lib = (TextView)findViewById(R.id.lib);
        cafe = (TextView)findViewById(R.id.cafe);
        atm = (TextView)findViewById(R.id.atm);
        wrongtag = (TextView)findViewById(R.id.wrongtag);

        final Set<String> tagset = new HashSet<>();
        int i  = 0;
        for(Place place : PeiYangMap.getMap()){
            tagset.add(place.getTag());
            i++;
        }
        String[] tags = new String[tagset.size()];
        int j = 0;
        for(String tag : tagset){
            tags[j] = tag;
            j++;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tags);
        autoCompleteTextView.setAdapter(arrayAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPlaceActivity.this, SearchResult.class);
                Bundle bundle = new Bundle();
                if(!tagset.contains(autoCompleteTextView.getText().toString())){
                    wrongtag.setText("无效的地名！请按提示输入。");
                }
                else{
                    bundle.putString("标签", autoCompleteTextView.getText().toString());
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                }
            }
        });

        lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPlaceActivity.this, SearchResult.class);
                Bundle bundle = new Bundle();
                bundle.putString("标签", "图书馆");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPlaceActivity.this, SearchResult.class);
                Bundle bundle = new Bundle();
                bundle.putString("标签", "咖啡厅");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPlaceActivity.this, SearchResult.class);
                Bundle bundle = new Bundle();
                bundle.putString("标签", "ATM");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });
    }
}
