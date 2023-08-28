import 'dart:io';

import 'package:mime/mime.dart';
import 'package:sosame/mime_type.dart';
import 'package:sosame/exceptions/mime_type_exceptions.dart';

extension MimeTypeExtension on File {
  String mimeType(List<MimeType> supportedMimeTypes) {
    final mimeType = lookupMimeType(path);

    if (mimeType == null) {
      throw const MimeTypeNotFound("mime type not found");
    }

    var isMimeTypeSupported = false;

    for (var supportedMimeType in supportedMimeTypes) {
      if (mimeType.startsWith(supportedMimeType.path)) {
        isMimeTypeSupported = true;
        break;
      }
    }

    if (!isMimeTypeSupported) {
      throw MimeTypeNotFound("mime type $mimeType not supported");
    }

    return mimeType;
  }

  void validateMimeType(List<MimeType> supportedMimeTypes) {
    final mimeType = lookupMimeType(path);

    if (mimeType == null) {
      throw const MimeTypeNotFound("mime type not found");
    }

    var isMimeTypeSupported = false;

    for (var supportedMimeType in supportedMimeTypes) {
      if (mimeType.startsWith(supportedMimeType.path)) {
        isMimeTypeSupported = true;
        break;
      }
    }

    if (!isMimeTypeSupported) {
      throw MimeTypeNotFound("mime type $mimeType not supported");
    }
  }
}
