package com.louisgeek.fragmentagentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NormalActivity extends AppCompatActivity {

    private Button idTvBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        initView();
    }

    private void initView() {
        idTvBtn1 = findViewById(R.id.id_tv_btn_1);
        idTvBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent();
                intent.putExtra("TEST", "1234");
                setResult(10, intent);
                //
                finish();
            }
        });
    }
}
