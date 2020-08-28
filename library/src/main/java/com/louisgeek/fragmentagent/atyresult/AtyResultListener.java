package com.louisgeek.fragmentagent.atyresult;

import android.content.Intent;

/**
 * Created by louisgeek on 2019/7/1.
 */
public interface AtyResultListener {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
