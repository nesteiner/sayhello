package com.example.backend;

import com.example.backend.model.Message;
import com.example.backend.model.User;
import com.example.backend.repository.MessageRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userDetailService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void fakeUser() {
        userRepository.deleteAll();
        messageRepository.deleteAll();

        User steiner = new User(1L, "steiner", "5f4dcc3b5aa765d61d8327deb882cf99");
        userRepository.save(steiner);
        List<Message> messages = List.of(
                new Message(null, 1L, "steiner", "hello world", null),
                new Message(null, 1L, "steiner", "fuck you", null),
                new Message(null, 1L, "steiner", "holy shit", null)
        );

        messageRepository.saveAll(messages);

        User user1 = new User(2L, "user1", "5f4dcc3b5aa765d61d8327deb882cf99");
        userRepository.save(user1);
        messages = List.of(
                new Message(null, 2L, "user1", "hello world", null),
                new Message(null, 2L, "user1", "fuck you", null),
                new Message(null, 2L, "user1", "holy shit", null)
        );

        messageRepository.saveAll(messages);

        User user2 = new User(3L, "user2", "5f4dcc3b5aa765d61d8327deb882cf99");
        userRepository.save(user2);
        messages = List.of(
                new Message(null, 3L, "user2", "hello world", null),
                new Message(null, 3L, "user2", "fuck you", null),
                new Message(null, 3L, "user2", "holy shit", null)
        );

        messageRepository.saveAll(messages);

        userRepository.findAll().forEach(System.out::println);
        messageRepository.findAll().forEach(System.out::println);
    }

    @Test
    void generateToken() {
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                "steiner",
                "password",
                new ArrayList<>()
        );

        String token = jwtTokenUtil.generateToken(user);
        System.out.println(token);
    }
}
