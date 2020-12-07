package com.tangerine.supportdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btnNibp;
    private AppCompatButton btnIdcard;
    private AppCompatButton btnEcg;
    private AppCompatButton btnUrine;
    private AppCompatButton btnTemp;
    private AppCompatButton btnGlu;
    private AppCompatButton btnSpo;
    private AppCompatButton btnUa;
    private AppCompatTextView tvValue;
    private AppCompatButton btnBloodFat;
    private AppCompatButton btnBmi;
    private AppCompatButton btnOneLeadEcg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOneLeadEcg = findViewById(R.id.OneLeadEcg);
        btnEcg = findViewById(R.id.ECG);
        btnBloodFat = findViewById(R.id.bloodFat);
        btnBmi = findViewById(R.id.bmi);
        btnNibp = findViewById(R.id.Nibp);
        btnIdcard = findViewById(R.id.IDCard);
        btnGlu = findViewById(R.id.glu);
        btnUrine = findViewById(R.id.Urine);
        btnUa = findViewById(R.id.ua);
        btnTemp = findViewById(R.id.Temp);
        btnSpo = findViewById(R.id.spo);
        tvValue = findViewById(R.id.tv_value);
        btnOneLeadEcg.setOnClickListener(this);
        btnBloodFat.setOnClickListener(this);
        btnBmi.setOnClickListener(this);
        btnNibp.setOnClickListener(this);
        btnIdcard.setOnClickListener(this);
        btnGlu.setOnClickListener(this);
        btnUrine.setOnClickListener(this);
        btnUa.setOnClickListener(this);
        btnTemp.setOnClickListener(this);
        btnSpo.setOnClickListener(this);
        btnEcg.setOnClickListener(this);
        float density = getBaseContext().getResources().getDisplayMetrics().density;
        int densityDpi = getBaseContext().getResources().getDisplayMetrics().densityDpi;
        Log.e("density", "" + density);
        Log.e("densityDpi", "" + densityDpi);
        searchLauncher();

    }

    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent();
            if (v == btnUa) {
                intent.putExtra("Content", "尿酸界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnGlu) {
                intent.putExtra("Content", "血糖界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnEcg) {
                intent.putExtra("Content", "心电图界面");
                EcgPersonInfo ecgPersonInfo = new EcgPersonInfo();
                ecgPersonInfo.Birth_Day = "25";
                ecgPersonInfo.Birth_Month = "8";
                ecgPersonInfo.Birth_Year = "1998";
                ecgPersonInfo.Patient_Gender = "男";
                ecgPersonInfo.Patient_ID = "440224199712161799";
                ecgPersonInfo.Patient_Name = "谭伯恩";
                intent.putExtra("EcgData", GsonUtil.toJson(ecgPersonInfo));
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnSpo) {
                intent.putExtra("Content", "血氧界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnNibp) {
                intent.putExtra("Content", "血压界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnTemp) {
                intent.putExtra("Content", "体温界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnUrine) {
                intent.putExtra("Content", "尿常规界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnIdcard) {
                intent.putExtra("Content", "身份证界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnBloodFat) {
                intent.putExtra("Content", "血脂界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            } else if (v == btnBmi) {
                intent.putExtra("Content", "体成分界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            }else if(v == btnOneLeadEcg){
                intent.putExtra("Content", "单导心电界面");
                intent.setAction("cn.com.dihealth.ExtCheckUpActivity");
            }
            else {
                throw new RuntimeException("plz set the intent value");
            }
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Toast.makeText(this, "没有安装应用哦", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private void searchLauncher() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> packageInfo = getPackageManager().queryIntentActivities(intent, 0);
        for (int i = 0; i < packageInfo.size(); i++) {
            String launcherActivityName = packageInfo.get(i).activityInfo.name;
            String packageName = packageInfo.get(i).activityInfo.packageName;
            Log.e("Launcher", "searchLauncher: " + packageName + "        " + launcherActivityName);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1000) {
                if (data != null) {
                    String result = data.getStringExtra("RESPONSE_DATA");
                    tvValue.setText(result);
                }
            }
        }
    }
}