package com.github.emilbayes.selfupdate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

public class UpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);

        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(launchIntent.getComponent());
        serviceIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(serviceIntent);
    }
}
