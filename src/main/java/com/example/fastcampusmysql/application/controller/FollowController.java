package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.CreateFollowMemberUsecase;
import com.example.fastcampusmysql.application.usecase.GetFollowingMemberUsecase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {
    final private CreateFollowMemberUsecase createFollowMemberUsecase;
    final private GetFollowingMemberUsecase getFollowingMemberUsecase;
    @PostMapping("/{fromId}/{toId}")
    public void register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.excute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> register(@PathVariable Long fromId) {
        return getFollowingMemberUsecase.excute(fromId);
    }
}
