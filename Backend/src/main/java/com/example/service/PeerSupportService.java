package com.example.service;

import com.example.dto.PeerMessageDto;
import com.example.entity.PeerMessage;
import com.example.repository.PeerMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeerSupportService {
    private final PeerMessageRepo peerMessageRepo;

    // Post a new message
    public void postMessage(PeerMessageDto dto) {
        PeerMessage message = new PeerMessage();
        message.setContent(dto.getContent());
        message.setUserNickname(dto.getUserNickname());
        message.setTimestamp(LocalDateTime.now());

        peerMessageRepo.save(message);
    }

    // Get all messages (sorted by latest first)
    public List<PeerMessageDto> getAllMessages() {
        return peerMessageRepo.findAllByOrderByTimestampDesc()
                .stream()
                .map(message -> new PeerMessageDto(
                        message.getContent(),
                        message.getUserNickname()
                ))
                .collect(Collectors.toList());
    }
}