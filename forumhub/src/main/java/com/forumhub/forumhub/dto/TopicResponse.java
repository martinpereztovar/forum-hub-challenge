package com.forumhub.forumhub.dto;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        String status,
        Long authorId,
        Long courseId
) {}
