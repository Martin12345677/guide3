package com.example.asus.beiyangtong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.InputStream;

public class SearchResult extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private ListView listView;
    private TextView mention;
    private RadioGroup way;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.beginplace) ;
        listView = (ListView)findViewById(R.id.placelist) ;
        mention = (TextView)findViewById(R.id.mention);
        way = (RadioGroup)findViewById(R.id.tag);
        String places[] = new String[PeiYangMap.getMap().size()];
        int i  = 0;
        for(Place place : PeiYangMap.getMap()){
            places[i] = place.getName();
            i++;
        }
        Bundle bundle = this.getIntent().getExtras();
        String result[] = PeiYangMap.getPeiYangMap().searchTag(bundle.get("标签").toString());
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, places);
        autoCompleteTextView.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,result);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(PeiYangMap.getPlace(autoCompleteTextView.getText().toString())==null){
                    mention.setText("无效的出发地！请按提示输入。");
                }else {
                    String place = parent.getItemAtPosition(position).toString();
                    Intent intent = new Intent(SearchResult.this, GuideResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("出发地", autoCompleteTextView.getText().toString());
                    bundle.putString("目的地", place);
                    boolean isChosen = false;
                    for(int i = 0; i < way.getChildCount(); i++){
                        RadioButton r = (RadioButton)way.getChildAt(i);
                        if(r.isChecked()){
                            bundle.putInt("方式", i + 1);
                            isChosen = true;
                            break;
                        }
                    }
                    if(!isChosen) {
                        mention.setText("请选择出行方式。");
                    }else{
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                    }
                }


            }
        });
    }
}
