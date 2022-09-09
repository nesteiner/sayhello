class Result<T> {
  String status;
  String message;
  T data;

  Result({
    required this.status,
    required this.message,
    required this.data
  });
}