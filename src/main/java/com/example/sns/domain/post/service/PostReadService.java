package com.example.sns.domain.post.service;

import com.example.sns.domain.post.dto.DailyPostCount;
import com.example.sns.domain.post.dto.DailyPostCountRequest;
import com.example.sns.domain.post.dto.PostDto;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.mapper.PostMapper;
import com.example.sns.domain.post.repository.PostLikeRepository;
import com.example.sns.domain.post.repository.PostRepository;
import com.example.sns.util.CursorRequest;
import com.example.sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostMapper postMapper;
    final private PostRepository postRepository;
    final private PostLikeRepository postLikeRepository;
    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /*
            반환 값 -> 리스트[일자, 작성회원, 작성 게시물 갯수]
            select
                createdDate, memberId, count(id)
            from Post
            where memberId = :memberId and createdDate between firstDate and lastDate
            group by createdDate memberId
         */
        return postRepository.groupByCreatedDate(request);
    }
    public Page<PostDto> getPosts(Long memberId, Pageable pageable) {
        return postRepository
                .findAllByMemberId(memberId, pageable)
                .map(post -> toPostDto(post, postLikeRepository.count(post.getId())));
    }

    public PostDto getPost(Long postId) {
        Post post = postRepository.findByById(postId, false).orElseThrow();
        Long count = postLikeRepository.count(post.getId());
        return toPostDto(post, count);
    }

    private PostDto toPostDto(Post post, Long count) {
        return new PostDto(
                post.getId(),
                post.getContents(),
                post.getCreatedAt(),
                count
        );
    }

    public PageCursor<PostDto> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<PostDto> posts = findAllBy(memberId, cursorRequest)
                .stream()
                .map(postMapper::toPostDto)
                .toList();
        Long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public PageCursor<PostDto> getPosts(List<Long> memberIds, CursorRequest cursorRequest) {
        List<PostDto> posts = findAllBy(memberIds, cursorRequest)
                .stream()
                .map(postMapper::toPostDto)
                .toList();
        Long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public List<Post> getPosts(List<Long> ids) {
        return postRepository.findByAllByInId(ids);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if(cursorRequest.hasKey()) {
            return postRepository.findAllByMemberIdLessThanIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }
        return postRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
    }

    private List<Post> findAllBy(List<Long> memberIds, CursorRequest cursorRequest) {
        if(cursorRequest.hasKey()) {
            return postRepository.findAllByInMemberIdLessThanIdAndOrderByIdDesc(cursorRequest.key(), memberIds, cursorRequest.size());
        }
        return postRepository.findAllByInMemberIdAndOrderByIdDesc(memberIds, cursorRequest.size());
    }
    private static long getNextKey(List<PostDto> posts) {
        return posts.stream()
                .mapToLong(PostDto::id)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }
}


