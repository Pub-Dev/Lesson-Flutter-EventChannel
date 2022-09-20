import 'package:flutter/services.dart';

class BatteryChannel {
  final EventChannel _event = const EventChannel('my_app/event/battery');

  Stream get stream => _event.receiveBroadcastStream().cast();
}
