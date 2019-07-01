package com.louisgeek.fragmentagent.permission;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.Random;


public class RequestPermissionsFragment extends Fragment {
    private static final String TAG = "RequestPermissionsFragm";
    private Random mRandom = new Random();
    private AppCompatActivity mAppCompatActivity;
    private SparseArray<RequestPermissionsListener> mRequestPermissionsListenerSparseArray = new SparseArray<>();

    public RequestPermissionsFragment() {
        // Required empty public constructor
    }

    public static RequestPermissionsFragment newInstance() {
        RequestPermissionsFragment fragment = new RequestPermissionsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAppCompatActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(false);
    }

    public void requestPermissions(@NonNull String[] permissions, RequestPermissionsListener listener) {
        // 0x0000FFFF 16进制 65536 10 进制
        int requestCode = mRandom.nextInt(0x0000FFFF);
        Log.e(TAG, "requestPermissions: requestCode " + requestCode);
        mRequestPermissionsListenerSparseArray.put(requestCode, listener);
        this.requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPermissionsListener requestPermissionsListener = mRequestPermissionsListenerSparseArray.get(requestCode);
        mRequestPermissionsListenerSparseArray.remove(requestCode);
        if (requestPermissionsListener == null) {
            return;
        }
        boolean isAllGranted = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                isAllGranted = false;
                break;
            }
        }
        requestPermissionsListener.all(permissions, isAllGranted);
        //
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                requestPermissionsListener.each(permission, true, false);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mAppCompatActivity, permission)) {
                    requestPermissionsListener.each(permission, false, true);
                } else {
                    requestPermissionsListener.each(permission, false, false);
                }
            }
        }
    }


}
