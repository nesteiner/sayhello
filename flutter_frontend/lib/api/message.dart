import 'package:dio/dio.dart';
import '../model/message.dart';

final options = BaseOptions(
  baseUrl: "http://localhost:8082",
);

final dio = Dio(options);

Future<List<Message>> findAllMessages(String token) async {
  Response response = await dio.get(
      "/message/all",
      options: Options(
          headers: {
            "Authorization": "Bearer ${token}"
          }
      ));

  List<dynamic> list = response.data["data"];
  return list
      .map<Message>((x) => Message(userid: x["userid"], username: x["username"], body: x["body"], timestamp: x["timestamp"]))
      .toList();
}

Future<List<Message>> findMatchedMessages(String token) async {
  Response response = await dio.get(
      "/message",
      options: Options(
          headers: {
            "Authorization": "Bearer ${token}"
          }
      ));

  List<dynamic> list = response.data["data"];
  return list
      .map<Message>((x) => Message(userid: x["userid"], username: x["username"], body: x["body"], timestamp: x["timestamp"]))
      .toList();
}

Future<Message> sendMessage(String body, String token) async {
  Response response = await dio.post(
    "/message",
    data: {
      "body": body,
    },
    options: Options(
      headers: {
        "Authorization": "Bearer ${token}"
      }
    )
  );

  Map<String, dynamic> json = response.data["data"];
  Message message = Message(
    userid: json["userid"],
    username: json["username"],
    body: json["body"],
    timestamp: json["timestamp"]
  );

  return message;
}