import 'dart:async';

import 'package:flutter/services.dart';

class FlutterIntent {
  static const MethodChannel _channel = const MethodChannel('flutter_intent');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> openApp(String appId, String targetActivity) async {
    await _channel.invokeMapMethod('openApp', {"appId": appId,"targetActivity": targetActivity});
  }
}
