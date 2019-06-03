package com.example.asus.beiyangtong;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GuideResult extends AppCompatActivity {

    private Guide guide;
    private Place begin, end;
    private int tag;
    private TextView way1, way2, tags1, tags2, route1,route2,result;
    private Button shot, back;
    public static final String SHOT_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/保存的路线";
    private Activity activity = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_result);
        way1 = (TextView)findViewById(R.id.way1);
        way2 = (TextView)findViewById(R.id.way2);
        tags1 = (TextView)findViewById(R.id.tags1);
        tags2 = (TextView)findViewById(R.id.tags2);
        route1 = (TextView)findViewById(R.id.route1) ;
        route2 = (TextView)findViewById(R.id.route2);
        shot = (Button)findViewById(R.id.shot);
        back = (Button)findViewById(R.id.back);
        result = (TextView)findViewById(R.id.shotresult);
        Bundle bundle = this.getIntent().getExtras();
        begin = PeiYangMap.getPlace((String)bundle.get("出发地"));
        end = PeiYangMap.getPlace((String)bundle.get("目的地"));
        tag = (int)bundle.get("方式");
        guide = new Guide(begin, end, tag);
        if(tag == 1){
            way1.setText(guide.get1());
            way2.setText(guide.get2());
            tags1.setText(guide.get1tags());
            tags2.setText(guide.get2tags());
            route1.setText("路线一（距离最短）：" + guide.getlength1() + "米");
            route2.setText("\n路线二：" + guide.getlength2() + "米");
            if(guide.getlength2()>=1000000){
                route2.setText("");
                way2.setText("");
            }
        }else{
            way1.setText(guide.get3());
            way2.setText(guide.get4());
            route1.setText("路线一（距离最短）：" + guide.getlength3() + "米");
            route2.setText("路线二：" + guide.getlength4() + "米");
            if(guide.getlength3()>=1000000){
                route1.setText("暂无相关路线，换个地方试试吧！");
                route2.setText("");
                way1.setText("");
                way2.setText("");
            }
            if(guide.getlength4()>=1000000){
                route2.setText("");
                way2.setText("");
            }
        }
        shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = shot(activity);
                if(saveShot(begin.getName()+"-"+end.getName(), bitmap)){
                    result.setText("保存成功！");
                }else{
                    result.setText("保存失败！");
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideResult.this, GuideActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

    }

    public static Bitmap shot(Activity activity){
        View view = activity.getWindow().getDecorView();//顶层view
        //允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;

        WindowManager windowManager = activity.getWindowManager();

        //获取屏幕宽和高
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        //去掉状态栏
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        //销毁缓存信息
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public static boolean saveShot(String fileName, Bitmap bitmap){
        try {
            File file = new File(SHOT_FILE_PATH,fileName+".png");
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
