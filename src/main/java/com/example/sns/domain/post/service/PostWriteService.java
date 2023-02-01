package com.example.sns.domain.post.service;

import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.mapper.PostMapper;
import com.example.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    final private PostMapper postMapper;
    final private PostRepository postRepository;
    public Long create(PostCommand command)
    {
        Post post = postMapper.toPostEntity(command);
        return postMapper.toPostDto(postRepository.save(post)).id();
    }

    @Transactional
    public void likePost(Long postId) {
        Post post = postRepository.findByById(postId, true).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
