package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exception.InvalidBookingRequestException;
import com.hotel.model.BookedRoom;
import com.hotel.model.Room;
import com.hotel.repository.BookingRepository;
import com.hotel.service.IBookingService;
import com.hotel.service.IRoomService;

@Service
public class BookingService implements IBookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private IRoomService roomService;
	
	public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
		return bookingRepository.findByRoomId(roomId);
	}

	@Override
	public String saveBooking(Long roomId, BookedRoom bookingRequest) {
		if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
			throw new InvalidBookingRequestException("check-in date must come before check-out date");
		}
		Room room = roomService.getRoomById(roomId).get();
		List<BookedRoom> existingBookings = room.getBookings();
		boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingBookings);
		if (roomIsAvailable) {
			room.addBooking(bookingRequest);
			bookingRepository.save(bookingRequest);
		}else {
			throw new InvalidBookingRequestException("Sorry, This room is not available for the selected dates;");
		}
		return bookingRequest.getBookingConfirmationCode();
	}

	private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
		return existingBookings.stream()
				.noneMatch(existingBooking ->
					bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
					|| bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
					|| (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
					&& bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate()))
					|| (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
					
					&& bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
					|| (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
							
					&& bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
					
					|| (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
					&&	bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
					
					|| (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
					&& bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
						);
	}

	@Override
	public void cancelBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
		
	}

	@Override
	public BookedRoom findByBookingConfiramtionCode(String confirmationCode) {
		return bookingRepository.findByBookingConfirmationCode(confirmationCode);
	}

	@Override
	public List<BookedRoom> getAllBookings() {
		return bookingRepository.findAll();
	}

	
}
