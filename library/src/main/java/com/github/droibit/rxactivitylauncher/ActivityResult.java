package com.github.droibit.rxactivitylauncher;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * This class holds the data received by {@link Activity#onActivityResult(int, int, Intent)}.
 *
 * @author kumagai
 */
public class ActivityResult {

    public final int resultCode;
    public final Intent data;

    ActivityResult(int resultCode, @Nullable Intent data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public boolean isOk() {
        return resultCode == Activity.RESULT_OK;
    }

    public boolean isCanceled() {
        return resultCode == Activity.RESULT_CANCELED;
    }
}
