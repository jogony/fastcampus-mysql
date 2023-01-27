package com.example.fastcampusmysql.domain.post.mapper;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLSession;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PostMapper {
    final private PostRepository postRepository;

    public PostDto save(PostCommand command) {
        Post post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();
        return toDto(postRepository.save(post));
    }
    public PostDto toDto(Post post) {
        return new PostDto(post.getId(), post.getMemberId(), post.getContents(), post.getCreatedDate(), post.getCreatedAt());
    }

    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }
}
