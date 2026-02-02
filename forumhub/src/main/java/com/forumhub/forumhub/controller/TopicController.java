package com.forumhub.forumhub.controller;

import com.forumhub.forumhub.dto.CreateTopicRequest;
import com.forumhub.forumhub.dto.TopicResponse;
import com.forumhub.forumhub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponse create(@RequestBody @Valid CreateTopicRequest req) {
        return topicService.create(req);
    }

    @GetMapping
    public Page<TopicResponse> listAll(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable
    ) {
        return topicService.listAll(pageable);
    }
}
