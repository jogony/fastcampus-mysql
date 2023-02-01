package com.example.sns.domain.member.service;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.dto.MemberNicknameHistoryDto;
import com.example.sns.domain.member.mapper.MemberMapper;
import com.example.sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    final private MemberMapper memberMapper;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    final private MemberRepository memberRepository;

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(memberMapper::toMemberNicknameHistoryDto)
                .toList();
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        if(ids.isEmpty()) {
            return List.of();
        }
        return memberRepository
                .findAllbyIdIn(ids)
                .stream()
                .map(memberMapper::toMemberDto)
                .toList();
    }

    public MemberDto getMember(Long id) {
        return memberMapper.toMemberDto(memberRepository.findById(id).orElseThrow());
    }
}
