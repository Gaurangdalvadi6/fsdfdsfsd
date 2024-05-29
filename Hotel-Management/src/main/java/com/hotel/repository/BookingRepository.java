package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.BookedRoom;

@Repository
public interface BookingRepository extends JpaRepository<BookedRoom, Long>{
    
	List<BookedRoom> findByRoomId(Long roomId);

	BookedRoom findByBookingConfirmationCode(String confirmationCode);
}
