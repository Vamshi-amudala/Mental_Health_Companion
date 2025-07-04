package com.example.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PeerMessageDto;
import com.example.service.PeerSupportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/peer")
@RequiredArgsConstructor
public class PeerSupportController {
	
	private final PeerSupportService supportservice;
	
	@PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody PeerMessageDto dto) {
        supportservice.postMessage(dto);
        return ResponseEntity.ok("Message posted successfully");
    }
	@GetMapping("/messages")
	public ResponseEntity<List<PeerMessageDto>> receivemessages(){
		List<PeerMessageDto> messages = supportservice.getAllMessages();
		return ResponseEntity.ok(messages);
	}
}
