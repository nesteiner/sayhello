import 'package:flutter/material.dart';
import 'package:flutter_frontend/api/login.dart';
import 'package:flutter_frontend/page/homepage.dart';
import 'package:provider/provider.dart';

import '../model/globalstate.dart';

class LoginPage extends StatelessWidget {
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(title: Text("Login"),),
      body: buildBody(context),
    );
  }

  Widget buildBody(BuildContext context) {
    final column = Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        TextField(
          controller: usernameController,
          decoration: InputDecoration(
            labelText: "用户名",
            hintText: "用户名",
            prefixIcon: Icon(Icons.person)
          ),
        ),

        TextField(
          controller: passwordController,
          decoration: InputDecoration(
            labelText: "密码",
            hintText: "密码",
            prefixIcon: Icon(Icons.lock)
          ),
        ),

        Consumer(builder: (context, GlobalState state, child) {
          return ElevatedButton(
            child: Text("Login"),
            onPressed: () async {
              final token = await login(username: usernameController.text, password: passwordController.text);
              await state.setToken(token);
              usernameController.text = "";
              passwordController.text = "";

              // Navigator.of(context).pushNamed("home");
              Navigator.of(context).push(MaterialPageRoute(builder: (_) => HomePage()));
            },
          );
        })

      ],
    );

    final padding = Padding(
      padding: EdgeInsets.all(24),
      child: column,
    );

    return padding;
  }
}