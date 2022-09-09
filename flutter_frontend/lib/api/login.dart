import "package:dio/dio.dart";
import 'package:flutter_frontend/model/message.dart';
import 'package:flutter_frontend/model/result.dart';

final options = BaseOptions(
  baseUrl: "http://localhost:8082",
);

final dio = Dio(options);

Future<String> login({required String username, required String password}) async {
    Response response = await dio.post("/authenticate", data: {
      "username": username,
      "password": password
    });

    return response.data["jwttoken"];
}