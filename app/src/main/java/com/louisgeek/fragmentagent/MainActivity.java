package com.louisgeek.fragmentagent;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.louisgeek.fragmentagent.atyresult.AtyResultHelper;
import com.louisgeek.fragmentagent.atyresult.AtyResultListener;
import com.louisgeek.fragmentagent.permission.PermissionsHelper;
import com.louisgeek.fragmentagent.permission.RequestPermissionsListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView idTv1;
    private TextView idTv2;
    private AppCompatActivity mAppCompatActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppCompatActivity = this;

        initView();

        idTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                ContextCompat.checkSelfPermission(this)
                PermissionsHelper.requestPermissions(mAppCompatActivity, new String[]{Manifest.permission.READ_CONTACTS},
                        new RequestPermissionsListener() {
                            @Override
                            public void each(String permission, boolean isGranted, boolean shouldShowRationale) {
                                Log.e(TAG, "each: " + permission + " " + isGranted + " " + shouldShowRationale);
                                if (!isGranted && !shouldShowRationale) {
                                    try {
                                        Intent intent =
                                                new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" +
                                                        mAppCompatActivity.getPackageName()));
                                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                                        mAppCompatActivity.startActivityForResult(intent, 10); //这里的requestCode和onActivityResult中requestCode要一致
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void all(String[] permissions, boolean isAllGranted) {
                                Log.e(TAG, "all: ----------" + isAllGranted);
                                for (String permission : permissions) {
                                    Log.e(TAG, "all: " + permission + " " + isAllGranted);
                                }
                            }
                        });
            }
        });

        idTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                ContextCompat.checkSelfPermission(this)
                AtyResultHelper.startActivityForResult(mAppCompatActivity, NormalActivity.class, new AtyResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                        //
                        String dsd = data.getStringExtra("TEST");
                        Toast.makeText(mAppCompatActivity, dsd, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void initView() {
        idTv1 = findViewById(R.id.id_tv_1);
        idTv2 = findViewById(R.id.id_tv_2);
    }
}
