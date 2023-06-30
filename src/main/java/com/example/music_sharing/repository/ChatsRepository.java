package com.example.music_sharing.repository;

import com.example.music_sharing.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, Long> {
    List<Chats> findAllByBoardId(String id);

    List<Chats> findAllById(Long id);
}
