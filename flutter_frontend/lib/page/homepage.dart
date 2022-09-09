import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../api/message.dart';
import '../model/globalstate.dart';
import '../model/message.dart';

class HomePage extends StatelessWidget {
  TextEditingController textEditingController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return DefaultTabController(length: 2, child: Scaffold(
        appBar: AppBar(
          title: Text("HomePage"),
          bottom: TabBar(
            tabs: [
              Tab(text: "全部"),
              Tab(text: "我的",)
            ],
          ),
        ),
        body: TabBarView(
          children: [
            buildWithAllMessages(context),
            buildWithMyMessages(context)
          ],
        ))
    );
  }

  Widget buildWithAllMessages(BuildContext context) {
    return Column(
      children: [
        buildInput(context),
        Expanded(
          child: buildAllMessages(context)
        )
      ],
    );
  }

  Widget buildWithMyMessages(BuildContext context) {
    return Column(
      children: [
        buildInput(context),
        Expanded(
          child: buildMyMessages(context)
        )
      ],
    );
  }

  Widget buildInput(BuildContext context) {
    final input = Consumer(
      builder: (context, GlobalState state, child) {
        return Column(
          children: [
            TextField(
              controller: textEditingController,
              maxLines: 8,
              decoration: InputDecoration(
                hintText: "Enter your text here"
              ),
            ),

            ElevatedButton(
              child: Text("Send"),
              onPressed: () async {
                try {
                  final token = state.token;
                  Message message = await sendMessage(
                      textEditingController.text, token);
                  state.addMessage(message);
                  textEditingController.text = "";
                } on DioError catch(error) {
                  await showDialog(context: context, builder: (context) {
                    return AlertDialog(
                      title: Text("错误发生"),
                      content: Text(error.response!.data["message"]),
                      actions: [
                        TextButton(
                          child: Text("确认"),
                          onPressed: () {
                            if(error.response!.statusCode == 401) {
                              Navigator.of(context).popUntil((route) => route.isFirst);
                            } else if(error.response!.statusCode == 400) {
                              textEditingController.text = "";
                              Navigator.of(context).pop();
                            }
                          },
                        )
                      ],
                    );
                  });
                }
              },
            )
          ],
        );
      },
    );

    return input;
  }

  Widget buildAllMessages(BuildContext context) {
    return Consumer(builder: (context, GlobalState state, child) {
      final messages = state.allMessages;
      // return Column(
      //   children: messages.map<Widget>((x) => buildMessage(context, x)).toList(),
      // );

      return ListView.builder(
          itemCount: messages.length,
          itemBuilder: (context, index) {
              return buildMessage(context, messages[index]);
          }
      );

    });
  }

  Widget buildMyMessages(BuildContext context) {
    return Consumer(builder: (context, GlobalState state, child) {
      final messages = state.myMessages;
      return Column(
        children: messages.map<Widget>((x) => buildMessage(context, x)).toList(),
      );
    });
  }

  Widget buildMessage(BuildContext context, Message message) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(message.username, style: TextStyle(fontWeight: FontWeight.w500),),
            Text(message.timestamp)
          ],
        ),

        Text(message.body)
      ],
    );
  }
}