package com.louisgeek.fragmentagent.permission;

/**
 * Created by louisgeek on 2019/7/1.
 */
public interface RequestPermissionsListener {
    void each(String permission, boolean isGranted, boolean shouldShowRationale);
    void all(String[] permissions, boolean isAllGranted);
}
