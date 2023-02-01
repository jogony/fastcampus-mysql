package com.example.sns.domain.follow.mapper;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);
    FollowDto toFollowDto(Follow follow);
}
