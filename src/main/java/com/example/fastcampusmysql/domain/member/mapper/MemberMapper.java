package com.example.fastcampusmysql.domain.member.mapper;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MemberMapper {

    final private MemberRepository memberRepository;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(this::toMemberNicknameHistoryDto)
                .toList();
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        return memberRepository
                .findAllbyIdIn(ids)
                .stream()
                .map(this::toDto)
                .toList();
    }
    public MemberDto getMember(Long id) {
        return toDto(memberRepository.findById(id).orElseThrow());
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
    private MemberNicknameHistoryDto toMemberNicknameHistoryDto(MemberNicknameHistory history) {
        return new MemberNicknameHistoryDto(
                history.getId(),
                history.getMemberId(),
                history.getNickname(),
                history.getCreatedAt()
        );
    }

    public MemberDto save(RegisterMemberCommand command) {
        Member member = Member
                .builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        return toDto(memberRepository.save(member));
    }

    public MemberDto changeNickname(Long memberId, String nickname) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        return toDto(memberRepository.save(member));
    }

    public void saveMemberNicknameHistory(MemberDto member) {
        MemberNicknameHistory history = MemberNicknameHistory
                .builder()
                .memberId(member.id())
                .nickname(member.nickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }
}
