package com.reddit.post_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddit.post_service.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{
	List<Post> findAllBySubredditId(Long subredditId);
	List<Post> findAllByUserId(Long userId);
	Post findBySubredditId(Long subredditId);
}
