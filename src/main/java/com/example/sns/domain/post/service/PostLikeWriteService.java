package com.example.sns.domain.post.service;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.dto.PostDto;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.entity.PostLike;
import com.example.sns.domain.post.mapper.PostMapper;
import com.example.sns.domain.post.repository.PostLikeRepository;
import com.example.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    final private PostMapper postMapper;
    final private PostLikeRepository postLikeRepository;
    public Long create(PostDto postDto, MemberDto memberDto)
    {
        PostLike postLike = PostLike
                .builder()
                .postId(postDto.id())
                .memberId(memberDto.id())
                .build();
        return postLikeRepository.save(postLike).getPostId();
    }
}