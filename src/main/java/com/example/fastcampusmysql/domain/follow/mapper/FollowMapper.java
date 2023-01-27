package com.example.fastcampusmysql.domain.follow.mapper;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class FollowMapper {
    final private FollowRepository followRepository;

    public List<FollowDto> findAllByMemberId(Long memberId) {
        return followRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public FollowDto toDto(Follow follow) {
        return new FollowDto(follow.getId(), follow.getFromMemberId(), follow.getToMemberId(), follow.getCreatedAt());
    }

    public void save(MemberDto fromMember, MemberDto toMember) {
        Follow follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();
        followRepository.save(follow);
    }
}
