package com.example.music_sharing.service;

import com.example.music_sharing.adapter.MemberAdapter;
import com.example.music_sharing.entity.Board;
import com.example.music_sharing.entity.Genres;
import com.example.music_sharing.entity.Member;
import com.example.music_sharing.entity.dto.BoardDto;
import com.example.music_sharing.repository.BoardRepository;
import com.example.music_sharing.repository.GenresRepository;
import com.example.music_sharing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    private final GenresRepository genresRepository;


    public void saveBoard(BoardDto boardDto, @AuthenticationPrincipal MemberAdapter memberAdapter) {

        LocalDateTime now = LocalDateTime.now();

        Optional<Member> member =  memberRepository.findByUsername(memberAdapter.getUsername());

        Board board = Board.builder()
                .singer(boardDto.getSinger())
                .song(boardDto.getSong())
                .info(boardDto.getInfo())
                .link(boardDto.getLink())
                .nickname(member.get().getNickname())
                .username(member.get().getUsername())
                .localDateTime(now)
                .view(0L)
                .build();

        boardRepository.save(board);

        Optional<Board> boardId = boardRepository.findTopByOrderByIdDesc();

        for(String genre : boardDto.getGenres()) {
            Genres genreEntity = Genres.builder()
                    .boardId(boardId.get().getId())
                    .genre(genre)
                    .build();
            genresRepository.save(genreEntity);
        }
    }

    public List<Board> listBoard() {
        return boardRepository.findAll();
    }

    public Optional<Board> view(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        board.get().setView(board.get().getView() + 1);
        boardRepository.save(board.get());
        return boardRepository.findById(id);
    }

    public List<Genres> genres(Long id) {
        return genresRepository.findByBoardId(id);
    }

    public String boardTime(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(board.get().getLocalDateTime(), now);
        long min = duration.toMinutes();
        String time = "";
        if(min == 0) {
            time = "방금 전";
        } else if(min < 60) {
            time  = min + "분 전";
        } else if(min < 1440) {
            long hour = min / 60;
            time = hour + "시간 전";
        } else if (min < 10080) {
            long day = min / (60 * 24);
            time = day + "일 전";
        } else if(min < 43200) {
            long week = min / (60 * 24 * 7);
            time = week + "주 전";
        } else if(min < 525600) {
            long month = min / (60 * 24 * 30);
            time = month + "달 전";
        } else {
            long year = min / 525600;
            time = year + "년 전";
        }
        return time;
    }

    public List<String> singerBoardTime(String singer) {
        LocalDateTime now = LocalDateTime.now();
        List<Board> board = boardRepository.findAllBySingerContaining(singer);
        List<String> time = new ArrayList<>();
        for(int i = 0; i < board.size(); i++) {
            Duration duration = Duration.between(board.get(i).getLocalDateTime(), now);
            long min = duration.toMinutes();
            if(min == 0) {
                time.add("방금 전");
            } else if(min < 60) {
                time.add(min + "분 전");
            } else if(min < 1440) {
                long hour = min / 60;
                time.add(hour + "시간 전");
            } else if (min < 10080) {
                long day = min / (60 * 24);
                time.add(day + "일 전");
            } else if(min < 43200) {
                long week = min / (60 * 24 * 7);
                time.add(week + "주 전");
            } else if(min < 525600) {
                long month = min / (60 * 24 * 30);
                time.add(month + "달 전");
            } else {
                long year = min / 525600;
                time.add(year + "년 전");
            }
        }

        return time;
    }

    public List<String> songBoardTime(String song) {
        LocalDateTime now = LocalDateTime.now();
        List<Board> board = boardRepository.findAllBySongContaining(song);
        List<String> time = new ArrayList<>();
        for(int i = 0; i < board.size(); i++) {
            Duration duration = Duration.between(board.get(i).getLocalDateTime(), now);
            long min = duration.toMinutes();
            if(min == 0) {
                time.add("방금 전");
            } else if(min < 60) {
                time.add(min + "분 전");
            } else if(min < 1440) {
                long hour = min / 60;
                time.add(hour + "시간 전");
            } else if (min < 10080) {
                long day = min / (60 * 24);
                time.add(day + "일 전");
            } else if(min < 43200) {
                long week = min / (60 * 24 * 7);
                time.add(week + "주 전");
            } else if(min < 525600) {
                long month = min / (60 * 24 * 30);
                time.add(month + "달 전");
            } else {
                long year = min / 525600;
                time.add(year + "년 전");
            }
        }

        return time;
    }

    public List<String> boardTimes() {
        List<Board> board = boardRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        List<String> time = new ArrayList<>();
        for(int i = 0; i < board.size(); i++) {
            Duration duration = Duration.between(board.get(i).getLocalDateTime(), now);
            long min = duration.toMinutes();
            if(min == 0) {
                time.add("방금 전");
            } else if(min < 60) {
                time.add(min + "분 전");
            } else if(min < 1440) {
                long hour = min / 60;
                time.add(hour + "시간 전");
            } else if (min < 10080) {
                long day = min / (60 * 24);
                time.add(day + "일 전");
            } else if(min < 43200) {
                long week = min / (60 * 24 * 7);
                time.add(week + "주 전");
            } else if(min < 525600) {
                long month = min / (60 * 24 * 30);
                time.add(month + "달 전");
            } else {
                long year = min / 525600;
                time.add(year + "년 전");
            }
        }
        return time;
    }

    public List<Board> boardSong(String song) {
        return boardRepository.findAllBySongContaining(song);
    }

    public List<Board> boardSinger(String singer) {
        return boardRepository.findAllBySingerContaining(singer);
    }
}
