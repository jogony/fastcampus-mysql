package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.mapper.FollowMapper;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    final private FollowMapper followMapper;
    final private FollowRepository followRepository;

    public List<FollowDto> getFollowings(Long memberId) {
        return followRepository
                .findAllByFromMemberId(memberId)
                .stream()
                .map(followMapper::toFollowDto)
                .toList();
    }

    public List<FollowDto> getFollowers(Long memberId) {
        return followRepository
                .findAllByToMemberId(memberId)
                .stream()
                .map(followMapper::toFollowDto)
                .toList();
    }
}
