package com.example.music_sharing.entity;


import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String singer;

    private String song;

    private String info;

    private String link;

    private String username;

    private String nickname;

    private Long view;

    private LocalDateTime localDateTime;

}
