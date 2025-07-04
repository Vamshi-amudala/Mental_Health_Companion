package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SelfCareActivityDto;
import com.example.service.SelfCareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SelfCareController {

    private final SelfCareService selfCareService;

	@PostMapping("/selfcare")
	public ResponseEntity<String> addActivity(@Valid @RequestBody SelfCareActivityDto dto){
		selfCareService.createActivity(dto);
		System.out.println("Activity Added Sucessfully");
		return ResponseEntity.ok("Activity Added Sucessfully");
	}
	
	@GetMapping("/selfcare/{id}")
	public ResponseEntity<List<SelfCareActivityDto>> getUsersActivity(@PathVariable("id") Long userid) {
	    return ResponseEntity.ok(selfCareService.getUserActivities(userid));
	}
	
	@PutMapping("/selfcare/{id}/{status}")
	public void updateActivity(@PathVariable("id") Long userid,@PathVariable("status") Boolean completed ){
		selfCareService.updateActivityStatus(userid, completed);;
		
	}

}
