package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PeerMessage;
@Repository
public interface PeerMessageRepo extends JpaRepository<PeerMessage, Long>{

}
