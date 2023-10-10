import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'sosame_method_channel.dart';

abstract class SosamePlatform extends PlatformInterface {
  /// Constructs a SosamePlatform.
  SosamePlatform() : super(token: _token);

  static final Object _token = Object();

  static SosamePlatform _instance = MethodChannelSosame();

  /// The default instance of [SosamePlatform] to use.
  ///
  /// Defaults to [MethodChannelSosame].
  static SosamePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [SosamePlatform] when
  /// they register themselves.
  static set instance(SosamePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<bool> isInstagramInstalled() {
    throw UnimplementedError('isInstagramInstalled() has not been implemented.');
  }

  Future<bool> isThreadsInstalled() {
    throw UnimplementedError('isThreadsInstalled() has not been implemented.');
  }

  Future<bool> isFacebookInstalled() {
    throw UnimplementedError('isFacebookInstalled() has not been implemented.');
  }

  Future<bool> isTwitterInstalled() {
    throw UnimplementedError('isTwitterInstalled() has not been implemented.');
  }

  Future<bool> isWhatsAppInstalled() {
    throw UnimplementedError('isWhatsAppInstalled() has not been implemented.');
  }

  Future<void> shareInstagramStory({
    required String backgroundImagePath,
    required String sourceApplication,
    required String mimeType,
    required String? stickerImagePath,
  }) {
    throw UnimplementedError('shareInstagramStory() has not been implemented.');
  }

  Future<void> shareInstagramFeed({
    required String mediaPath,
    required String sourceApplication,
    required String mimeType,
  }) {
    throw UnimplementedError('shareInstagramFeed() has not been implemented.');
  }

  Future<void> shareThreads({
    required String mediaPath,
    required String mimeType,
  }) {
    throw UnimplementedError('shareThreads() has not been implemented.');
  }

  Future<void> shareFacebookStory({
    required String backgroundImagePath,
    required String appId,
    required String mimeType,
    required String? stickerImagePath,
  }) {
    throw UnimplementedError('shareFacebookStory() has not been implemented.');
  }

  Future<void> shareTwitter({
    required String? mediaPath,
    required String? text,
    required String mimeType,
  }) {
    throw UnimplementedError('shareTwitter() has not been implemented.');
  }

  Future<void> shareWhatsApp({
    required String? mediaPath,
    required String? text,
    required String? phoneNumber,
    required String mimeType,
  }) {
    throw UnimplementedError('shareWhatsApp() has not been implemented.');
  }
}
