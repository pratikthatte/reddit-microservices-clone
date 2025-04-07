package com.reddit.vote_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddit.vote_service.entity.TargetType;
import com.reddit.vote_service.entity.Vote;
import com.reddit.vote_service.entity.VoteType;

public interface VoteRepository extends JpaRepository<Vote,Long>{
	Long countByTargetIdAndTargetTypeAndVoteType(Long targetId, VoteType voteType, TargetType targetType);
}
