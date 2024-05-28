package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.BookedRoom;
import com.hotel.repository.BookingRepository;
import com.hotel.service.IBookingService;

@Service
public class BookingService implements IBookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveBooking(Long roomId, BookedRoom bookingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
		
	}

	@Override
	public BookedRoom findByBookingConfiramtionCode(String confirmationCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookedRoom> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
