import 'dart:io';

import 'package:flutter/services.dart';
import 'package:sosame/exceptions/package_exception.dart';
import 'package:sosame/exceptions/sosame_exception.dart';
import 'package:sosame/file_extensions.dart';
import 'package:sosame/mime_type.dart';
import 'package:sosame/mime_type_extensions.dart';

import 'sosame_platform_interface.dart';

class Sosame {
  static Future<bool> isInstagramInstalled() {
    return SosamePlatform.instance.isInstagramInstalled();
  }

  static Future<bool> isFacebookInstalled() {
    return SosamePlatform.instance.isFacebookInstalled();
  }

  static Future<bool> isThreadsInstalled() {
    return SosamePlatform.instance.isThreadsInstalled();
  }

  static Future<bool> isWhatsAppInstalled() {
    return SosamePlatform.instance.isWhatsAppInstalled();
  }

  static Future<bool> isTwitterInstalled() {
    return SosamePlatform.instance.isTwitterInstalled();
  }

  static Future<void> shareInstagramStory({
    required String backgroundImagePath,
    required String sourceApplication,
    String? stickerImagePath,
  }) async {
    try {
      final backgroundImageFile = File(backgroundImagePath);
      await backgroundImageFile.validateExistingFile();
      final backgroundMimeType = backgroundImageFile.mimeType(
        [MimeType.image, MimeType.video],
      );

      if (stickerImagePath != null) {
        final stickerImageFile = File(stickerImagePath);
        await stickerImageFile.validateExistingFile();
        stickerImageFile.validateMimeType([MimeType.image]);
      }

      return await SosamePlatform.instance.shareInstagramStory(
        backgroundImagePath: backgroundImagePath,
        stickerImagePath: stickerImagePath,
        sourceApplication: sourceApplication,
        mimeType: backgroundMimeType,
      );
    } on PlatformException catch (error, stackTrace) {
      if (error.code == 'packageNotFound') {
        throw Error.throwWithStackTrace(
          InstagramNotFound(error),
          stackTrace,
        );
      }

      throw Error.throwWithStackTrace(
        ShareInstagramStoryException(error),
        stackTrace,
      );
    }
  }

  static Future<void> shareInstagramFeed({
    required String mediaPath,
    required String sourceApplication,
  }) async {
    try {
      final mediaFile = File(mediaPath);
      await mediaFile.validateExistingFile();
      final mediaMimeType = mediaFile.mimeType(
        [MimeType.image, MimeType.video],
      );

      return await SosamePlatform.instance.shareInstagramFeed(
        mediaPath: mediaPath,
        sourceApplication: sourceApplication,
        mimeType: mediaMimeType,
      );
    } on PlatformException catch (error, stackTrace) {
      if (error.code == 'packageNotFound') {
        throw Error.throwWithStackTrace(
          InstagramNotFound(error),
          stackTrace,
        );
      }

      throw Error.throwWithStackTrace(
        ShareInstagramFeedException(error),
        stackTrace,
      );
    }
  }

  static Future<void> shareThreads({
    required String mediaPath,
  }) async {
    try {
      final mediaFile = File(mediaPath);
      await mediaFile.validateExistingFile();
      final mediaMimeType = mediaFile.mimeType(
        [MimeType.image, MimeType.video],
      );

      return await SosamePlatform.instance.shareThreads(
        mediaPath: mediaPath,
        mimeType: mediaMimeType,
      );
    } on PlatformException catch (error, stackTrace) {
      if (error.code == 'packageNotFound') {
        throw Error.throwWithStackTrace(
          ThreadsNotFound(error),
          stackTrace,
        );
      }

      throw Error.throwWithStackTrace(
        ShareThreadsException(error),
        stackTrace,
      );
    }
  }

  static Future<void> shareFacebookStory({
    required String backgroundImagePath,
    required String appId,
    String? stickerImagePath,
  }) async {
    try {
      final backgroundImageFile = File(backgroundImagePath);
      await backgroundImageFile.validateExistingFile();
      final backgroundMimeType = backgroundImageFile.mimeType(
        [MimeType.image, MimeType.video],
      );

      if (stickerImagePath != null) {
        final stickerImageFile = File(stickerImagePath);
        await stickerImageFile.validateExistingFile();
        stickerImageFile.validateMimeType([MimeType.image]);
      }

      return await SosamePlatform.instance.shareFacebookStory(
        backgroundImagePath: backgroundImagePath,
        stickerImagePath: stickerImagePath,
        appId: appId,
        mimeType: backgroundMimeType,
      );
    } on PlatformException catch (error, stackTrace) {
      if (error.code == 'packageNotFound') {
        throw Error.throwWithStackTrace(
          FacebookNotFound(error),
          stackTrace,
        );
      }

      throw Error.throwWithStackTrace(
        ShareFacebookStoryException(error),
        stackTrace,
      );
    }
  }

  static Future<void> shareTwitter({String? mediaPath, String? text}) async {
    var mimeType = MimeType.text.path;

    if (mediaPath == null && text == null) {
      throw const ShareWhatsAppException(
        'Both text and media must not be empty',
      );
    }

    try {
      if (mediaPath != null) {
        final mediaFile = File(mediaPath);
        await mediaFile.validateExistingFile();
        mimeType = mediaFile.mimeType([MimeType.image, MimeType.video]);
      }

      return await SosamePlatform.instance.shareTwitter(
        mediaPath: mediaPath,
        text: text,
        mimeType: mimeType,
      );
    } on PlatformException catch (error, stackTrace) {
      if (error.code == 'packageNotFound') {
        throw Error.throwWithStackTrace(
          TwitterNotFound(error),
          stackTrace,
        );
      }

      throw Error.throwWithStackTrace(
        ShareTwitterException(error),
        stackTrace,
      );
    }
  }

  static Future<void> shareWhatsApp({String? mediaPath, String? text}) async {
    if (mediaPath == null && text == null) {
      throw const ShareWhatsAppException(
        'Both text and media must not be empty',
      );
    }

    var mimeType = MimeType.text.path;

    try {
      if (mediaPath != null) {
        final mediaFile = File(mediaPath);
        await mediaFile.validateExistingFile();
        mimeType = mediaFile.mimeType([MimeType.image, MimeType.video]);
      }

      return await SosamePlatform.instance.shareWhatsApp(
        mediaPath: mediaPath,
        text: text,
        mimeType: mimeType,
      );
    } on PlatformException catch (error, stackTrace) {
      if (error.code == 'packageNotFound') {
        throw Error.throwWithStackTrace(
          WhatsAppNotFound(error),
          stackTrace,
        );
      }

      throw Error.throwWithStackTrace(
        ShareWhatsAppException(error),
        stackTrace,
      );
    }
  }
}
