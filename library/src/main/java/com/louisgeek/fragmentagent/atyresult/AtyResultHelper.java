package com.louisgeek.fragmentagent.atyresult;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Created by louisgeek on 2019/7/1.
 */
public class AtyResultHelper {
    private static final String REQUEST_CODE_ATY_RESULT = "REQUEST_CODE_ATY_RESULT";

    public static void startActivityForResult(AppCompatActivity appCompatActivity, Class<?> clazz, AtyResultListener listener) {
        Intent intent = new Intent(appCompatActivity, clazz);
        startActivityForResult(appCompatActivity, intent, listener);
    }

    public static void startActivityForResult(AppCompatActivity appCompatActivity, Intent intent, AtyResultListener listener) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        AtyResultFragment fragment = (AtyResultFragment) fragmentManager.findFragmentByTag(REQUEST_CODE_ATY_RESULT);
        if (fragment == null) {
            fragment = AtyResultFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(fragment, REQUEST_CODE_ATY_RESULT)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        //
        fragment.startActivityForResult(intent, listener);
    }
}
