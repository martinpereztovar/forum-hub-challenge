-- V2__seed_initial_data.sql
-- Seed initial roles, a sample user, and a sample course

INSERT INTO roles (name) VALUES
  ('ROLE_USER'),
  ('ROLE_ADMIN');

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@forumhub.dev', '$2a$10$7Qn2wW0S7WQ7pYl5pQ0x6eGm0oV3q5oN8l8kP7v9gB9Qm9p3wK8cS');

-- The password hash above is a BCrypt hash for: Admin123!

INSERT INTO courses (name, category) VALUES
  ('Spring Boot', 'Backend');
