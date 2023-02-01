package com.example.sns.domain.post.dto;

import java.time.LocalDate;

public record PostDto(
        Long id,
        Long memberId,
        String contents,
        LocalDate createdDate,
        java.time.LocalDateTime createdAt
) {
}
