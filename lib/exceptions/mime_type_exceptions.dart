import 'package:equatable/equatable.dart';

class MimeTypeException extends Equatable implements Exception {
  /// {@macro user_failure}
  const MimeTypeException(this.error);

  /// The error which was caught.
  final Object error;

  @override
  List<Object?> get props => [error];
}

class MimeTypeNotFound extends MimeTypeException {
  /// {@macro user_failure}
  const MimeTypeNotFound(super.error);
}
