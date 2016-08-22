package com.github.droibit.rxactivitylauncher;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Action3;


/**
 * Factory class of {@link ActivityLaunchable}.
 *
 * @author kumagai
 */
class ActivityLaunchers {

    private ActivityLaunchers() {
    }

    /**
     * Class to start another {@link Activity}.
     */
    static class FromActivity implements ActivityLaunchable, Action3<Intent, Integer, Bundle> {

        private final RxActivityLauncher launcher;

        private final Activity activity;

        @Nullable
        private Observable<?> trigger;

        FromActivity(RxActivityLauncher launcher, Activity activity) {
            this.launcher = launcher;
            this.activity = checkNotNull(activity);
        }

        @Override
        public ActivityLaunchable on(@NonNull Observable<?> trigger) {
            this.trigger = checkNotNull(trigger);
            return this;
        }

        @Override
        public Observable<ActivityResult> startActivityForResult(@NonNull Intent intent, int requestCode,
                @Nullable Bundle options) {
            if (trigger == null) {
                return launcher.startActivityForResult(this, intent, requestCode, options);
            }
            return launcher.startActivityForResult(this, trigger, intent, requestCode, options);
        }

        @Override
        public void call(@NonNull Intent intent, @NonNull Integer requestCode, @Nullable Bundle options) {
            activity.startActivityForResult(intent, requestCode, options);
        }
    }

    /**
     * Class to start another {@link Activity} from {@link Fragment}
     */
    static class FromFragment implements ActivityLaunchable, Action3<Intent, Integer, Bundle> {

        private final RxActivityLauncher launcher;

        private final Fragment fragment;

        @Nullable
        private Observable<?> trigger;

        FromFragment(RxActivityLauncher launcher, Fragment fragment) {
            this.launcher = launcher;
            this.fragment = checkNotNull(fragment);
        }

        @Override
        public ActivityLaunchable on(@NonNull Observable<?> trigger) {
            this.trigger = checkNotNull(trigger);
            return this;
        }

        @Override
        public Observable<ActivityResult> startActivityForResult(@NonNull Intent intent, int requestCode,
                @Nullable Bundle options) {
            if (trigger == null) {
                return launcher.startActivityForResult(this, intent, requestCode, options);
            }
            return launcher.startActivityForResult(this, trigger, intent, requestCode, options);
        }

        @Override
        public void call(@NonNull Intent intent, @NonNull Integer requestCode, @Nullable Bundle options) {
            fragment.startActivityForResult(intent, requestCode, options);
        }
    }

    /**
     * Class to start another {@link Activity} from {@link android.support.v4.app.Fragment}
     */
    static class FromSupportFragment implements ActivityLaunchable, Action3<Intent, Integer, Bundle> {

        private final RxActivityLauncher launcher;

        private final android.support.v4.app.Fragment fragment;

        @Nullable
        private Observable<?> trigger;

        FromSupportFragment(RxActivityLauncher launcher, android.support.v4.app.Fragment fragment) {
            this.launcher = launcher;
            this.fragment = checkNotNull(fragment);
        }

        @Override
        public ActivityLaunchable on(@NonNull Observable<?> trigger) {
            this.trigger = checkNotNull(trigger);
            return this;
        }

        @Override
        public Observable<ActivityResult> startActivityForResult(@NonNull Intent intent, int requestCode,
                @Nullable Bundle options) {
            if (trigger == null) {
                return launcher.startActivityForResult(this, intent, requestCode, options);
            }
            return launcher.startActivityForResult(this, trigger, intent, requestCode, options);
        }

        @Override
        public void call(@NonNull Intent intent, @NonNull Integer requestCode, @Nullable Bundle options) {
            fragment.startActivityForResult(intent, requestCode, options);
        }
    }

    private static <T> T checkNotNull(T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return object;
    }

    /**
     * Class to start another {@link Activity} from user defined {@link Action3}.
     */
    static class FromAction implements ActivityLaunchable, Action3<Intent, Integer, Bundle> {

        private final RxActivityLauncher launcher;

        private final Action1<Integer> action;

        @Nullable
        private Observable<?> trigger;

        public FromAction(RxActivityLauncher launcher, Action1<Integer> action) {
            this.launcher = launcher;
            this.action = checkNotNull(action);
        }

        @Override
        public ActivityLaunchable on(@NonNull Observable<?> trigger) {
            this.trigger = checkNotNull(trigger);
            return this;
        }

        @Override
        public Observable<ActivityResult> startActivityForResult(@NonNull Intent intent, int requestCode,
                @Nullable Bundle options) {
            if (trigger == null) {
                return launcher.startActivityForResult(this, intent, requestCode, options);
            }
            return launcher.startActivityForResult(this, trigger, intent, requestCode, options);
        }

        @Override
        public void call(@NonNull Intent intent, @NonNull Integer requestCode, @Nullable Bundle options) {
            action.call(requestCode);
        }
    }
}
