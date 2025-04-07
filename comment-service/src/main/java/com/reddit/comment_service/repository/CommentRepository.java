package com.reddit.comment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reddit.comment_service.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

	List<Comment> findAllByPostId(Long postId);

	List<Comment> findAllByUserId(Long userId);
	
}
