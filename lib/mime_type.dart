enum MimeType {
  image('image/'),
  text('text/'),
  video('video/');

  const MimeType(this.path);

  final String path;
}
