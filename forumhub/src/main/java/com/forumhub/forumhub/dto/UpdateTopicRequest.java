package com.forumhub.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicRequest(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull Long authorId,
        @NotNull Long courseId
) {}
