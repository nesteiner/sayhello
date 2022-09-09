import 'package:flutter/material.dart';

class TestPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("TestPage"),),
      body: buildBody(context),
    );
  }

  Widget buildBody(BuildContext context) {
    return ListView.builder(
        itemCount: 100,
        itemBuilder: (context, index) {
          return ListTile(title: Text("${index}"),);
        });
  }
}