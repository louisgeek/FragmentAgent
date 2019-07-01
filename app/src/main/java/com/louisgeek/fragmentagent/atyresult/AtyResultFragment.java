package com.louisgeek.fragmentagent.atyresult;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import androidx.fragment.app.Fragment;

import java.util.Random;


public class AtyResultFragment extends Fragment {
    private static final String TAG = "ActivityResultFrag";
    private SparseArray<AtyResultListener> mActivityResultListenerSparseArray = new SparseArray<>();
    private Random mRandom = new Random();

    public AtyResultFragment() {
        // Required empty public constructor
    }

    public static AtyResultFragment newInstance() {
        AtyResultFragment fragment = new AtyResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(false);
    }

    public void startActivityForResult(Intent intent, AtyResultListener listener) {
        int requestCode = mRandom.nextInt(0x0000FFFF);
        Log.e(TAG, "startActivityForResult: requestCode " + requestCode);
        mActivityResultListenerSparseArray.put(requestCode, listener);
        this.startActivityForResult(intent, requestCode, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AtyResultListener atyResultListener = mActivityResultListenerSparseArray.get(requestCode);
        mActivityResultListenerSparseArray.remove(requestCode);
        if (atyResultListener != null) {
            atyResultListener.onActivityResult(requestCode, resultCode, data);
        }
    }


}
