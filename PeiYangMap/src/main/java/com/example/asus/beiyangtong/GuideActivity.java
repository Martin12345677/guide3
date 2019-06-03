package com.example.asus.beiyangtong;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class GuideActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView1;
    private AutoCompleteTextView autoCompleteTextView2;
    private ArrayAdapter<String> arrayAdapter;
    private Button button;
    private RadioGroup way;
    private TextView wrong;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        autoCompleteTextView1 = (AutoCompleteTextView)findViewById(R.id.places1);
        String places[] = new String[PeiYangMap.getMap().size()];
        int i  = 0;
        for(Place place : PeiYangMap.getMap()){
            places[i] = place.getName();
            i++;
        }
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, places);
        autoCompleteTextView1.setAdapter(arrayAdapter);
        autoCompleteTextView2 = (AutoCompleteTextView)findViewById(R.id.places2);
        autoCompleteTextView2.setAdapter(arrayAdapter);
        wrong = (TextView)findViewById(R.id.wrongplace);
        way = (RadioGroup)findViewById(R.id.tag);
        button = (Button)findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, GuideResult.class);
                Bundle bundle = new Bundle();
                if(PeiYangMap.getPlace(autoCompleteTextView1.getText().toString())==null||PeiYangMap.getPlace(autoCompleteTextView2.getText().toString())==null){
                    wrong.setText("无效的地名！请按提示输入。");
                }
                else{
                    bundle.putString("出发地", autoCompleteTextView1.getText().toString());
                    bundle.putString("目的地", autoCompleteTextView2.getText().toString());
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
                        wrong.setText("请选择出行方式。");
                    }else{
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                    }
                }
            }
        });

    }

}
