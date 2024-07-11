package com.example.social_network.repository;

import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Story;
import com.example.social_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
    List<Chat> findByUsers_Id(@Param("userId") Integer userId);

    @Query("select c from Chat c where :user member of c.users and :reqUser member of c.users")
    Chat findChatByUsers(@Param("user") User user, @Param("reqUser") User reqUser);

}
