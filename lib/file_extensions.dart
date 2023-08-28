import 'dart:io';

import 'package:sosame/exceptions/file_exceptions.dart';

extension FileExtensions on File {
  Future<void> validateExistingFile() async {
    if (!await exists()) {
      throw const FileNotFound("file image not found");
    }
  }
}
