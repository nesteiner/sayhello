package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long userid;

    @Column(length = 64, nullable = false)
    String username;

    @Column(length = 124 * 4, nullable = false)
    String body;

    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    Timestamp timestamp;
}
