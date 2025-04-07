package com.reddit.subreddit_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddit.subreddit_service.entity.Subreddit;

public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
	
}
