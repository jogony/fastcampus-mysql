package com.example.sns.domain.member.mapper;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.dto.MemberNicknameHistoryDto;
import com.example.sns.domain.member.dto.RegisterMemberCommand;
import com.example.sns.domain.member.entity.Member;
import com.example.sns.domain.member.entity.MemberNicknameHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto toMemberDto(Member member);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    Member toMemberEntity(RegisterMemberCommand command);
    MemberNicknameHistoryDto toMemberNicknameHistoryDto(MemberNicknameHistory memberNicknameHistory);

    @Mappings({
            @Mapping(target = "memberId", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    MemberNicknameHistory toMemberNicknameHistoryEntity(MemberDto memberDto);

    @Mappings({
            @Mapping(target = "memberId", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    MemberNicknameHistory toMemberNicknameHistoryEntity(Member member);
}
