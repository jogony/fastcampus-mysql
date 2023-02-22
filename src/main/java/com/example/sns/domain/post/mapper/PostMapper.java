package com.example.sns.domain.post.mapper;

import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.dto.PostDto;
import com.example.sns.domain.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostDto toPostDto(Post post);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "likeCount", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    Post toPostEntity(PostCommand command);
}