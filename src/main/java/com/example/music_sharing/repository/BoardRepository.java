package com.example.music_sharing.repository;

import com.example.music_sharing.entity.Board;
import com.example.music_sharing.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findTopByOrderByIdDesc();

    List<Board> findAllBySingerContaining(String singer);

    List<Board> findAllBySongContaining(String song);
}
