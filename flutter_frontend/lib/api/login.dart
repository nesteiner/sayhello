import 'dart:convert';

import 'package:crypto/crypto.dart';
import "package:dio/dio.dart";

final options = BaseOptions(
  baseUrl: "http://localhost:8082",
);

final dio = Dio(options);

Future<String> login({required String username, required String password}) async {
  password = md5.convert(utf8.encode(password)).toString();

  Response response = await dio.post("/authenticate", data: {
    "username": username,
    "password": password
  });

  return response.data["jwttoken"];
}