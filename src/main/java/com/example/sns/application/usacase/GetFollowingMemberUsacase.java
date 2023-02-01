package com.example.sns.application.usacase;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.service.FollowReadService;
import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMemberUsacase {
    final private MemberReadService memberReadService;
    final private FollowReadService followReadService;
    public List<MemberDto> excute(Long memberId) {
        /*
            1. fromMemberId = memberID -> Follow List
            2. 1번을 순회하면서 회원정보를 찾으면 된다!
         */
        List<FollowDto> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(FollowDto::toMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
