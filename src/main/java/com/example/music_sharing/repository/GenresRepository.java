package com.example.music_sharing.repository;

import com.example.music_sharing.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Long> {

    List<Genres> findByBoardId(Long id);

}
