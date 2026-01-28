-- V1__create_core_tables.sql

CREATE TABLE roles (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_roles_name (name)
);

CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_users_email (email)
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user_roles_user
    FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_user_roles_role
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE courses (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  category VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE topics (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  message TEXT NOT NULL,
  created_at DATETIME(6) NOT NULL,
  status VARCHAR(30) NOT NULL,
  author_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_topics_author
    FOREIGN KEY (author_id) REFERENCES users (id),
  CONSTRAINT fk_topics_course
    FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE INDEX idx_topics_author_id ON topics (author_id);
CREATE INDEX idx_topics_course_id ON topics (course_id);
CREATE INDEX idx_topics_created_at ON topics (created_at);
