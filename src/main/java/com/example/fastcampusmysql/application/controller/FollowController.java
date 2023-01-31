package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usacase.CreateFollowMemberUsacase;
import com.example.fastcampusmysql.application.usacase.GetFollowingMemberUsacase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {
    final private CreateFollowMemberUsacase createFollowMemberUsacase;
    final private GetFollowingMemberUsacase getFollowingMemberUsacase;
    @PostMapping("/{fromId}/{toId}")
    public void register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsacase.excute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> register(@PathVariable Long fromId) {
        return getFollowingMemberUsacase.excute(fromId);
    }
}
