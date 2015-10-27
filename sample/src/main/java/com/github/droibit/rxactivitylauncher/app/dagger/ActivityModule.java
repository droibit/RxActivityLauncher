package com.github.droibit.rxactivitylauncher.app.dagger;

import android.app.Activity;

import com.github.droibit.rxactivitylauncher.RxLauncher;

import dagger.Module;
import dagger.Provides;

/**
 * @author kumagai
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    RxLauncher provideRxLauncher() {
        return RxLauncher.from(mActivity);
    }
}
