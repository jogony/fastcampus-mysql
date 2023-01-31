package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.mapper.MemberMapper;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {

    final private MemberMapper memberMapper;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    final private MemberRepository memberRepository;
    public MemberDto create(RegisterMemberCommand command) {
        /*
            목표     - 회원정보를 등록한다.(이메일, 닉네임, 생년월일)을 등록한다
                     - 닉네임은 10자를 넘길 수 없다.
            파라미터 : memberRegisterCommand
            val member = Member.of(memberRegisterCommand)
            memberRepository.save(member)
        */
        Member member = memberMapper.toMemberEntity(command);
        Member savedMember = memberRepository.save(member);
        MemberNicknameHistory history = memberMapper.toMemberNicknameHistoryEntity(savedMember);
        memberNicknameHistoryRepository.save(history);
        return memberMapper.toMemberDto(savedMember);
    }

    public void changeNickName(Long memberId, String nickname) {
        /*
            1. 회원의 이름을 변경
            2. 변경 내역을 저장
         */
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        // 변경내역 히스토리를 저장한다.
        MemberNicknameHistory history = memberMapper.toMemberNicknameHistoryEntity(member);
        memberNicknameHistoryRepository.save(history);
    }
}
