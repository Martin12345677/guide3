package com.example.asus.beiyangtong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class HoerActivity extends AppCompatActivity {
    private EditText I, u1, u2, u3, u4, x, B;
    private TextView ur,br;
    private Button b1,b2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoer_page);
        I = (EditText)findViewById(R.id.Im);
        u1 = (EditText)findViewById(R.id.u1);
        u2 = (EditText)findViewById(R.id.u2);
        u3 = (EditText)findViewById(R.id.u3);
        u4 = (EditText)findViewById(R.id.u4);
        x = (EditText)findViewById(R.id.x);
        B = (EditText)findViewById(R.id.B);
        ur = (TextView)findViewById(R.id.resultOfUR);
        br = (TextView)findViewById(R.id.resultOfBR);
        b1 = (Button)findViewById(R.id.Ur);
        b2 = (Button)findViewById(R.id.Br);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(I.getText()==null||u1.getText()==null||u2.getText()==null||u3.getText()==null||u4.getText()==null){
                    ur.setText("输入不能为空！");
                }else {
                    float i = Float.parseFloat(I.getText().toString());
                    float uu1 = Float.parseFloat(u1.getText().toString());
                    float uu2 = Float.parseFloat(u2.getText().toString());
                    float uu3 = Float.parseFloat(u3.getText().toString());
                    float uu4 = Float.parseFloat(u4.getText().toString());
                    float U = getU(uu1,uu2,uu3,uu4);
                    float B = getB(i);
                    float R = getR(U, B);
                    ur.setText("UH=" + U + "mV   B=" + B + "mT   R=" + R );
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x.getText()==null||B.getText()==null){
                    br.setText("输入不能为空！");
                }else{
                    float X = Float.parseFloat(x.getText().toString());
                    float b = Float.parseFloat(B.getText().toString());
                    br.setText("B=" + getb(X) + "T   R=" + getr(b,getb(X)) + "%");
                }
            }
        });
    }

    private float getU(float u1, float u2, float u3, float u4){
        return (u1-u2+u3-u4) / 4 ;
    }

    private float getB(float I){
        return (float)( 585.4 * I - 1.6513);
    }

    private float getR(float U, float B){
        return (float)(U * 0.5 / 1.8 / B);
    }

    private float getb(float x){
        return (float)(4*3.1415*pow(10, -7)*400*0.4*0.1*0.1/2/pow((0.01+x*x*0.0001), 1.5));
    }

    private float getr(float b1, float b2){
        return abs((b1-b2)/b2)*100;
    }
}
