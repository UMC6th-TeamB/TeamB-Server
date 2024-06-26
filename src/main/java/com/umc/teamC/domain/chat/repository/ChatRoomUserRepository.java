package com.umc.teamC.domain.chat.repository;

import com.umc.teamC.domain.chat.entity.ChatRoom;
import com.umc.teamC.domain.chat.entity.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
    Integer countByChatRoom(ChatRoom chatRoom);
}
