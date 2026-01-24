package com.forumhub.forumhub.repository;

import com.forumhub.forumhub.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {}
