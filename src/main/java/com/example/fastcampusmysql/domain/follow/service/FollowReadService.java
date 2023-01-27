package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.mapper.FollowMapper;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    final private FollowMapper followMapper;

    public List<FollowDto> getFollowings(Long memberId) {
        return followMapper.findAllByMemberId(memberId);
    }
}
