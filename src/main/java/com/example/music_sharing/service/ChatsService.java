package com.example.music_sharing.service;

import com.example.music_sharing.adapter.MemberAdapter;
import com.example.music_sharing.entity.Chats;
import com.example.music_sharing.entity.Member;
import com.example.music_sharing.entity.dto.ChatsDto;
import com.example.music_sharing.repository.ChatsRepository;
import com.example.music_sharing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final ChatsRepository chatsRepository;

    private final MemberRepository memberRepository;

    public void save(ChatsDto chatsDto, MemberAdapter memberAdapter) {

        LocalDateTime now = LocalDateTime.now();

        Optional<Member> member =  memberRepository.findByUsername(memberAdapter.getUsername());

        Chats chats = Chats.builder()
                .boardId(chatsDto.getBoardId())
                .nickname(member.get().getNickname())
                .chat(chatsDto.getChat())
                .localDateTime(now)
                .build();

        chatsRepository.save(chats);
    }

    public List<Chats> view(String id) {
        return chatsRepository.findAllByBoardId(id);
    }

    public List<String> chatTime(Long id) {
        String idx = id.toString();
        List<Chats> chat = chatsRepository.findAllByBoardId(idx);
        LocalDateTime now = LocalDateTime.now();
        List<String> time = new ArrayList<>();
        for(int i = 0; i < chat.size(); i++) {
            Duration duration = Duration.between(chat.get(i).getLocalDateTime(), now);
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

}
