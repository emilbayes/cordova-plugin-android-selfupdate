package com.github.emilbayes.selfupdate;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;

public class SelfUpdate extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("install".equals(action)) {
            if (args.length() != 1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
                return false;
            }

            Uri uri = Uri.parse(args.getString(0));

            Intent openIntent = new Intent(Intent.ACTION_VIEW, uri);
            openIntent.setDataAndType(uri, "application/vnd.android.package-archive");
            ((CordovaActivity) this.cordova.getActivity()).startActivity(openIntent);
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return true;
        }

        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
        return false;
    }
}
