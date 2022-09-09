class Message {
  int? id;
  int userid;
  String username;
  String body;
  String timestamp;

  Message({
    required this.userid,
    required this.username,
    required this.body,
    required this.timestamp
  });
}