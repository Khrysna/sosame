import 'package:equatable/equatable.dart';

class SosameExceptions extends Equatable implements Exception {
  /// {@macro user_failure}
  const SosameExceptions(this.error);

  /// The error which was caught.
  final Object error;

  @override
  List<Object?> get props => [error];
}

class ShareInstagramStoryException extends SosameExceptions {
  /// {@macro user_failure}
  const ShareInstagramStoryException(super.error);
}

class ShareInstagramFeedException extends SosameExceptions {
  /// {@macro user_failure}
  const ShareInstagramFeedException(super.error);
}

class ShareThreadsException extends SosameExceptions {
  /// {@macro user_failure}
  const ShareThreadsException(super.error);
}

class ShareFacebookStoryException extends SosameExceptions {
  /// {@macro user_failure}
  const ShareFacebookStoryException(super.error);
}

class ShareTwitterException extends SosameExceptions {
  /// {@macro user_failure}
  const ShareTwitterException(super.error);
}

class ShareWhatsAppException extends SosameExceptions {
  /// {@macro user_failure}
  const ShareWhatsAppException(super.error);
}
