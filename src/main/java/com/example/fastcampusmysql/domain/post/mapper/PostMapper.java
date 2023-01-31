package com.example.fastcampusmysql.domain.post.mapper;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
import com.example.fastcampusmysql.domain.post.entity.Post;
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
            @Mapping(target = "createdAt", ignore = true)
    })
    Post toPostEntity(PostCommand command);
}