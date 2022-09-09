import 'package:flutter/material.dart';
import 'package:flutter_frontend/api/message.dart';
import 'message.dart';

class GlobalState extends ChangeNotifier {
  late String token;
  late List<Message> allMessages;
  late List<Message> myMessages;

  GlobalState(String token) {
    this.token = token;
    this.allMessages = [];
    this.myMessages = [];
  }

  Future<void> setToken(String token) async {
    this.token = token;
    this.allMessages = await findAllMessages(token);
    this.myMessages = await findMatchedMessages(token);
  }

  void addMessage(Message message) {
    this.allMessages.add(message);
    this.myMessages.add(message);
    notifyListeners();
  }
}