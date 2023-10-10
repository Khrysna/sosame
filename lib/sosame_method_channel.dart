import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'sosame_platform_interface.dart';

/// An implementation of [SosamePlatform] that uses method channels.
class MethodChannelSosame extends SosamePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('sosame');

  @override
  Future<bool> isInstagramInstalled() async {
    final isAppInstalled = await methodChannel.invokeMethod<bool>(
      'isInstagramInstalled',
    );

    return isAppInstalled!;
  }

  @override
  Future<bool> isThreadsInstalled() async {
    final isAppInstalled = await methodChannel.invokeMethod<bool>(
      'isThreadsInstalled',
    );

    return isAppInstalled!;
  }

  @override
  Future<bool> isFacebookInstalled() async {
    final isAppInstalled = await methodChannel.invokeMethod<bool>(
      'isFacebookInstalled',
    );

    return isAppInstalled!;
  }

  @override
  Future<bool> isTwitterInstalled() async {
    final isAppInstalled = await methodChannel.invokeMethod<bool>(
      'isTwitterInstalled',
    );

    return isAppInstalled!;
  }

  @override
  Future<bool> isWhatsAppInstalled() async {
    final isAppInstalled = await methodChannel.invokeMethod<bool>(
      'isWhatsAppInstalled',
    );

    return isAppInstalled!;
  }

  @override
  Future<void> shareInstagramStory({
    required String backgroundImagePath,
    required String sourceApplication,
    required String mimeType,
    required String? stickerImagePath,
  }) async {
    final params = <String, dynamic>{
      'backgroundImagePath': backgroundImagePath,
      'sourceApplication': sourceApplication,
      'mimeType': mimeType,
      'stickerImagePath': stickerImagePath,
    };

    await methodChannel.invokeMethod<void>('shareInstagramStory', params);
  }

  @override
  Future<void> shareInstagramFeed({
    required String mediaPath,
    required String sourceApplication,
    required String mimeType,
  }) async {
    final params = <String, dynamic>{
      'mediaPath': mediaPath,
      'sourceApplication': sourceApplication,
      'mimeType': mimeType,
    };

    await methodChannel.invokeMethod<void>('shareInstagramFeed', params);
  }

  @override
  Future<void> shareThreads({
    required String mediaPath,
    required String mimeType,
  }) async {
    final params = <String, dynamic>{
      'mediaPath': mediaPath,
      'mimeType': mimeType,
    };

    await methodChannel.invokeMethod<void>('shareThreads', params);
  }

  @override
  Future<void> shareFacebookStory({
    required String backgroundImagePath,
    required String appId,
    required String mimeType,
    required String? stickerImagePath,
  }) async {
    final params = <String, dynamic>{
      'backgroundImagePath': backgroundImagePath,
      'appId': appId,
      'mimeType': mimeType,
      'stickerImagePath': stickerImagePath,
    };

    await methodChannel.invokeMethod<void>('shareFacebookStory', params);
  }

  @override
  Future<void> shareTwitter({
    required String? mediaPath,
    required String? text,
    required String mimeType,
  }) async {
    final params = <String, dynamic>{
      'mediaPath': mediaPath,
      'text': text,
      'mimeType': mimeType,
    };

    await methodChannel.invokeMethod<void>('shareTwitter', params);
  }

  @override
  Future<void> shareWhatsApp({
    required String? mediaPath,
    required String? text,
    required String? phoneNumber,
    required String mimeType,
  }) async {
    final params = <String, dynamic>{
      'mediaPath': mediaPath,
      'text': text,
      'mimeType': mimeType,
      'phoneNumber': phoneNumber,
    };

    await methodChannel.invokeMethod<void>('shareWhatsApp', params);
  }
}
