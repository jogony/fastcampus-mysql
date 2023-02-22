package com.example.sns.application.usacase;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.service.FollowReadService;
import com.example.sns.domain.post.dto.PostDto;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.entity.Timeline;
import com.example.sns.domain.post.service.PostReadService;
import com.example.sns.domain.post.service.TimelineReadService;
import com.example.sns.util.CursorRequest;
import com.example.sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsacase {
    final private FollowReadService followReadService;
    final private PostReadService postReadService;
    final private TimelineReadService timelineReadService;
    public PageCursor<PostDto> execute(Long memberId, CursorRequest cursorRequest) {
        /*
            1. memberId -> follow 조회
            2. 1번 결과로 게시글 조회
         */
        List<FollowDto> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings
                .stream()
                .map(FollowDto::toMemberId)
                .toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
         /*
            1. Timeline 조회
            2. 1번에 해당하는 게시물을 조회한다.
         */
        PageCursor<Timeline> pageTimelines = timelineReadService.getTimelines(memberId, cursorRequest);
        List<Long> postIds = pageTimelines.body()
                .stream()
                .map(Timeline::getPostId)
                .toList();
        List<Post> posts = postReadService.getPosts(postIds);
        return new PageCursor<>(pageTimelines.nextCursorRequest(), posts);
    }
}
