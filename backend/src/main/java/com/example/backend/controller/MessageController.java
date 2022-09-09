package com.example.backend.controller;

import ch.qos.logback.core.LogbackException;
import com.example.backend.exception.LoginException;
import com.example.backend.exception.MessageArgumentException;
import com.example.backend.model.Message;
import com.example.backend.model.MessageRequest;
import com.example.backend.model.User;
import com.example.backend.service.MessageService;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtTokenUtil;
import com.example.backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public Result<List<Message>> findMatched(HttpServletRequest request) throws LoginException {
        String username = jwtTokenUtil.getUsernameFromRequest(request);
        Optional<User> optionalUser = userService.findOne(username);
        return optionalUser.map(user -> Result.Ok("messages", messageService.findAllByUserid(user.getId())))
                .orElseThrow(() -> new LoginException("no such user"));
    }

    @GetMapping("/all")
    public Result<List<Message>> findAll() {
        return Result.Ok("all messages", messageService.findAll());
    }

    @PostMapping
    public Result<Message> insertOne(@RequestBody MessageRequest message, HttpServletRequest request) throws LoginException {
        String username = jwtTokenUtil.getUsernameFromRequest(request);
        Optional<User> optionalUser = userService.findOne(username);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Long userid = user.getId();
            Message insertMessage = new Message(null, user.getId(), user.getName(), message.getBody(), null);
            return Result.Ok("insert ok", messageService.insertOne(insertMessage));
//            if(!(message.getUserid().equals(userid) && message.getUsername().equals(username))) {
//                throw new MessageArgumentException("id and name not match");
//            } else {
//                return Result.Ok("insert ok", messageService.insertOne(message));
//            }
        } else {
            throw new LoginException("in MessageController: no such user");
        }
    }
}
