package com.louisgeek.fragmentagent.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Created by louisgeek on 2019/7/1.
 * <p>
 * 常用9组 Dangerous Permission
 * <p>
 * CALENDAR（日历）
 * READ_CALENDAR
 * WRITE_CALENDAR
 * <p>
 * CAMERA（相机）
 * CAMERA
 * <p>
 * CONTACTS（联系人）
 * READ_CONTACTS
 * WRITE_CONTACTS
 * GET_ACCOUNTS
 * <p>
 * LOCATION（位置）
 * ACCESS_FINE_LOCATION
 * ACCESS_COARSE_LOCATION
 * <p>
 * MICROPHONE（麦克风）
 * RECORD_AUDIO
 * <p>
 * PHONE（手机）
 * READ_PHONE_STATE
 * CALL_PHONE
 * READ_CALL_LOG
 * WRITE_CALL_LOG
 * ADD_VOICEMAIL
 * USE_SIP
 * PROCESS_OUTGOING_CALLS
 * <p>
 * SENSORS（传感器）
 * BODY_SENSORS
 * <p>
 * SMS（短信）
 * SEND_SMS
 * RECEIVE_SMS
 * READ_SMS
 * RECEIVE_WAP_PUSH
 * RECEIVE_MMS
 * <p>
 * STORAGE（存储卡）
 * READ_EXTERNAL_STORAGE
 * WRITE_EXTERNAL_STORAGE
 */
public class PermissionsHelper {
    private static final String REQUEST_CODE_PERMISSIONS = "REQUEST_CODE_PERMISSIONS";

    public static void requestPermissions(AppCompatActivity mAppCompatActivity, String[] permissions, RequestPermissionsListener listener) {
        FragmentManager fragmentManager = mAppCompatActivity.getSupportFragmentManager();
        RequestPermissionsFragment fragment = (RequestPermissionsFragment) fragmentManager.findFragmentByTag(REQUEST_CODE_PERMISSIONS);
        if (fragment == null) {
            fragment = RequestPermissionsFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(fragment, REQUEST_CODE_PERMISSIONS)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        //
        fragment.requestPermissions(permissions, listener);
    }
}
