import 'package:equatable/equatable.dart';

class PackageExceptions extends Equatable implements Exception {
  /// {@macro user_failure}
  const PackageExceptions(this.error);

  /// The error which was caught.
  final Object error;

  @override
  List<Object?> get props => [error];
}

class InstagramNotFound extends PackageExceptions {
  /// {@macro user_failure}
  const InstagramNotFound(super.error);
}

class ThreadsNotFound extends PackageExceptions {
  /// {@macro user_failure}
  const ThreadsNotFound(super.error);
}

class TwitterNotFound extends PackageExceptions {
  /// {@macro user_failure}
  const TwitterNotFound(super.error);
}

class FacebookNotFound extends PackageExceptions {
  /// {@macro user_failure}
  const FacebookNotFound(super.error);
}

class WhatsAppNotFound extends PackageExceptions {
  /// {@macro user_failure}
  const WhatsAppNotFound(super.error);
}
