import 'package:flutter/material.dart';

import 'package:sosame/sosame.dart';
import 'package:wechat_assets_picker/wechat_assets_picker.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Builder(builder: (context) {
            return ElevatedButton(
              onPressed: () async {
                final result = await AssetPicker.pickAssets(context);

                if (result != null && result.isNotEmpty) {
                  final asset = result.first;
                  final file = await asset.file;

                  if (file != null) {
                    Sosame.shareWhatsApp();
                  }
                }
              },
              child: const Text("Pick Image"),
            );
          }),
        ),
      ),
    );
  }
}
