package com.forumhub.forumhub.service;

import com.forumhub.forumhub.domain.Topic;
import com.forumhub.forumhub.domain.TopicStatus;
import com.forumhub.forumhub.dto.CreateTopicRequest;
import com.forumhub.forumhub.dto.TopicResponse;
import com.forumhub.forumhub.dto.UpdateTopicRequest;
import com.forumhub.forumhub.exception.TopicNotFoundException;
import com.forumhub.forumhub.repository.CourseRepository;
import com.forumhub.forumhub.repository.TopicRepository;
import com.forumhub.forumhub.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(TopicRepository topicRepository,
                        UserRepository userRepository,
                        CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public TopicResponse create(CreateTopicRequest req) {

        String normalizedTitle = req.title().trim();
        String normalizedMessage = req.message().trim();

        if (topicRepository.existsByTitleAndMessage(normalizedTitle, normalizedMessage)) {
            throw new IllegalArgumentException("Topic already exists with the same title and message");
        }

        var author = userRepository.findById(req.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        var course = courseRepository.findById(req.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        var topic = new Topic();
        topic.setTitle(normalizedTitle);
        topic.setMessage(normalizedMessage);
        topic.setCreatedAt(LocalDateTime.now());
        topic.setStatus(TopicStatus.OPEN);
        topic.setAuthor(author);
        topic.setCourse(course);

        var saved = topicRepository.save(topic);

        return new TopicResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getMessage(),
                saved.getCreatedAt(),
                saved.getStatus().name(),
                saved.getAuthor().getId(),
                saved.getCourse().getId()
        );
    }

    public Page<TopicResponse> listAll(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(t -> new TopicResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getMessage(),
                        t.getCreatedAt(),
                        t.getStatus().name(),
                        t.getAuthor().getId(),
                        t.getCourse().getId()
                ));
    }

    public TopicResponse getById(Long id) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));

        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt(),
                topic.getStatus().name(),
                topic.getAuthor().getId(),
                topic.getCourse().getId()
        );
    }

    public TopicResponse update(Long id, UpdateTopicRequest req) {

        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));

        String normalizedTitle = req.title().trim();
        String normalizedMessage = req.message().trim();

        // Prevent duplicates (same rule as create)
        boolean duplicateExists = topicRepository.existsByTitleAndMessage(normalizedTitle, normalizedMessage);

        if (duplicateExists) {
            boolean sameAsCurrent =
                    normalizedTitle.equals(topic.getTitle()) &&
                            normalizedMessage.equals(topic.getMessage());

            if (!sameAsCurrent) {
                throw new IllegalArgumentException("Topic already exists with the same title and message");
            }
        }

        var author = userRepository.findById(req.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        var course = courseRepository.findById(req.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        topic.setTitle(normalizedTitle);
        topic.setMessage(normalizedMessage);
        topic.setAuthor(author);
        topic.setCourse(course);

        var saved = topicRepository.save(topic);

        return new TopicResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getMessage(),
                saved.getCreatedAt(),
                saved.getStatus().name(),
                saved.getAuthor().getId(),
                saved.getCourse().getId()
        );
    }

    public void delete(Long id) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));

        topicRepository.delete(topic);
    }

}
