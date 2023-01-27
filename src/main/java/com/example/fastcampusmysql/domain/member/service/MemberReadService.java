package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.mapper.MemberMapper;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    final private MemberMapper memberMapper;

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberMapper.getNicknameHistories(memberId);
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        if(ids.isEmpty()) {
            return List.of();
        }
        return memberMapper.getMembers(ids);
    }

    public MemberDto getMember(Long id) {
        return memberMapper.getMember(id);
    }
}
