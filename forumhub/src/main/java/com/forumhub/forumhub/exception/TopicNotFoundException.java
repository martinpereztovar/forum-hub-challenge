package com.forumhub.forumhub.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(Long id) {
        super("Topic not found: " + id);
    }
}
