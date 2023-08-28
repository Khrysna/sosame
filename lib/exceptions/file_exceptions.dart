import 'package:equatable/equatable.dart';

class FileException extends Equatable implements Exception {
  /// {@macro user_failure}
  const FileException(this.error);

  /// The error which was caught.
  final Object error;

  @override
  List<Object?> get props => [error];
}

class FileNotFound extends FileException {
  /// {@macro user_failure}
  const FileNotFound(super.error);
}
