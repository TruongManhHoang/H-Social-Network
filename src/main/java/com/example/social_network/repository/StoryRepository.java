package com.example.social_network.repository;

import com.example.social_network.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    public List<Story> findByUserId(Integer userId);


}
