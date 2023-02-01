package com.example.sns.application.usacase;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.service.FollowReadService;
import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.service.PostWriteService;
import com.example.sns.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatePostUsacase {
    final private PostWriteService postWriteService;
    final private FollowReadService followReadService;
    final private TimelineWriteService timelineWriteService;

    @Transactional
    public Long execute(PostCommand postCommand) {
        Long postId = postWriteService.create(postCommand);
        List<Long> followMemberIds = followReadService
                .getFollowers(postCommand.memberId())
                .stream()
                .map(FollowDto::fromMemberId)
                .toList();
        timelineWriteService.deliveryToTimeline(postId, followMemberIds);
        return postId;
    }

}
