package com.example.music_sharing.controller;


import com.example.music_sharing.adapter.MemberAdapter;
import com.example.music_sharing.entity.Board;
import com.example.music_sharing.entity.Chats;
import com.example.music_sharing.entity.Genres;
import com.example.music_sharing.entity.dto.BoardDto;
import com.example.music_sharing.entity.dto.ChatsDto;
import com.example.music_sharing.service.BoardService;
import com.example.music_sharing.service.ChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final BoardService boardService;

    private final ChatsService chatsService;

    @PostMapping("/api/write")
    public void write(@RequestBody BoardDto boardDto, @AuthenticationPrincipal MemberAdapter memberAdapter) {
        boardService.saveBoard(boardDto, memberAdapter);
    }

    @GetMapping("/api/list")
    public ResponseEntity<List<Board>> list() {
        List<Board> boardList = boardService.listBoard();
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @GetMapping("/api/view/{id}")
    public ResponseEntity<Optional<Board>> view(@PathVariable Long id) {
        Optional<Board> board = boardService.view(id);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping("/api/view/time/{id}")
    public ResponseEntity<String> boardTime(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.boardTime(id), HttpStatus.OK);
    }

    @GetMapping("/api/chat/time/{id}")
    public ResponseEntity<List<String>> chatTime(@PathVariable Long id) {
        return new ResponseEntity<>(chatsService.chatTime(id), HttpStatus.OK);
    }


    @GetMapping("/api/genres/{id}")
    public ResponseEntity<List<Genres>> genres(@PathVariable Long id) {
        List<Genres> genres = boardService.genres(id);
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @PostMapping("/api/chats/write")
    public void chatsInput(@RequestBody ChatsDto chatsDto, @AuthenticationPrincipal MemberAdapter memberAdapter) {
        chatsService.save(chatsDto, memberAdapter);
    }

    @GetMapping("/api/chats/view/{id}")
    public ResponseEntity<List<Chats>> chatsView(@PathVariable Long id) {
        String idx = id.toString();
        List<Chats> chats = chatsService.view(idx);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/api/boards/view/all")
    public ResponseEntity<List<String>> boardTimes() {
        return new ResponseEntity<>(boardService.boardTimes(), HttpStatus.OK);
    }

    @GetMapping("/api/boards/view/singer/{singer}")
    public ResponseEntity<List<Board>> singerView(@PathVariable String singer) {
        return new ResponseEntity<>(boardService.boardSinger(singer), HttpStatus.OK);
    }

    @GetMapping("/api/boards/view/song/{song}")
    public ResponseEntity<List<Board>> songView(@PathVariable String song) {
        return new ResponseEntity<>(boardService.boardSong(song), HttpStatus.OK);
    }

    @GetMapping("/api/boards/view/singer/year/{singer}")
    public ResponseEntity<List<String>> singerYear(@PathVariable String singer) {
        return new ResponseEntity<>(boardService.singerBoardTime(singer), HttpStatus.OK);
    }

    @GetMapping("/api/boards/view/song/year/{song}")
    public ResponseEntity<List<String>> songYear(@PathVariable String song) {
        return new ResponseEntity<>(boardService.songBoardTime(song), HttpStatus.OK);
    }


}
