package com.sqro2Lab.flutter_intent.flutter_intent;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.content.Intent;
import android.content.pm.PackageManager;



/** FlutterIntentPlugin */
public class FlutterIntentPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Context context;
  
  public static void registerWith(final Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(),"flutter_intent");
        channel.setMethodCallHandler(new FlutterIntentPlugin(channel, registrar.activeContext()));
    }
  
  private FlutterIntentPlugin(final MethodChannel channel, Context context) {
        this.channel = channel;
        this.channel.setMethodCallHandler(this);
        this.context = context;
    }
  
  public FlutterIntentPlugin() {}

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_intent");
    channel.setMethodCallHandler(this);
    context = flutterPluginBinding.getApplicationContext();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch(call.method){
      case "openApp":
      String packageName = call.argument("appId").toString();
      openApp(packageName);
      break;
      case "getPlatformVersion":
      result.success("Android " + android.os.Build.VERSION.RELEASE);
      break;
      default:
      result.notImplemented();

          
    }


    // if (call.method.equals("getPlatformVersion")) {
    //   result.success("Android " + android.os.Build.VERSION.RELEASE);
    // } else {
    //   result.notImplemented();
    // }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
    context = null;
  }


  private int openApp(@NonNull String appId, @NonNull String targetActivity) {
   // Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appId);

    // Null pointer check in case package name was not found
    //if (launchIntent != null) {
    //    context.startActivity(launchIntent);
    //}
    
    Intent intent = new Intent(Intent.ACTION_MAIN);
    ComponentName componentName = new ComponentName(appId, targetActivity);
    intent.setComponent(componentName);
    startActivity(intent);
    
    return 0;

}


}
