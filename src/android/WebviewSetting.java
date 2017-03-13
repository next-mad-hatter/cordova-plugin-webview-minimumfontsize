package com.waverleysoftware.cordova.webviewsetting;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.engine.SystemWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Build;

import android.util.Log;

public class WebviewSetting extends CordovaPlugin {
    private CordovaWebView webView;
    private static final String LOG_TAG = "WebviewSetting";
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        Log.d(LOG_TAG, "set viewport");
        this.webView = webView;
        super.initialize(cordova, webView); 
        
    }
    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if ("set".equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    int minSize = 6;
                    try {
                        minSize = args.getInt(0);
                    } catch (JSONException e) {}
                    CordovaWebViewEngine engine = webView.getEngine();
                    SystemWebView webView = (SystemWebView)engine.getView();
                    webView.getSettings().setMinimumFontSize(minSize);
                    
                    callbackContext.success();
                }
            });
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }
}