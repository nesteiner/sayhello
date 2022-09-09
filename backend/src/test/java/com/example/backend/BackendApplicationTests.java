package com.example.backend;

import com.example.backend.model.Message;
import com.example.backend.model.User;
import com.example.backend.repository.MessageRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void fakeUser() {
        userRepository.deleteAll();
        messageRepository.deleteAll();

        User steiner = new User(1L, "steiner", "$2a$10$l2oI4vvvhto6g0uAtHcCG.QaybPO.MeJ0R12QP5iIQSS/0tH/G.Am");
        userRepository.save(steiner);
        List<Message> messages = List.of(
                new Message(null, 1L, "steiner", "hello world", null),
                new Message(null, 1L, "steiner", "fuck you", null),
                new Message(null, 1L, "steiner", "holy shit", null)
        );

        messageRepository.saveAll(messages);

        User user1 = new User(2L, "user1", "$2a$10$SpUB.jsZ1KTqrj8tsuNvz.JwZvN2TJWaTZ0xMYD6.uRHOT.AZ5GSa");
        userRepository.save(user1);
        messages = List.of(
                new Message(null, 2L, "user1", "hello world", null),
                new Message(null, 2L, "user1", "fuck you", null),
                new Message(null, 2L, "user1", "holy shit", null)
        );

        messageRepository.saveAll(messages);

        User user2 = new User(3L, "user2", "$2a$10$LPrfKJ.LkUA63ILVw2EGR.lD4oB4s.GJAevJt60RR82hmzgmyC/Rm");
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
}
