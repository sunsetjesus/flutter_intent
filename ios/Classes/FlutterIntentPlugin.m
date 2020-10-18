#import "FlutterIntentPlugin.h"
#if __has_include(<flutter_intent/flutter_intent-Swift.h>)
#import <flutter_intent/flutter_intent-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_intent-Swift.h"
#endif

@implementation FlutterIntentPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterIntentPlugin registerWithRegistrar:registrar];
}
@end
