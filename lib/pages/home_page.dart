import 'package:flutter/material.dart';

import '../channels/battery_channel.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final BatteryChannel _channel = BatteryChannel();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            StreamBuilder(
              stream: _channel.stream,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Text('Battery is ${snapshot.data}%');
                } else {
                  return const Text('Battery is not found');
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
