package com.hotel.service;

import java.util.List;

import com.hotel.model.BookedRoom;

public interface IBookingService {

	String saveBooking(Long roomId, BookedRoom bookingRequest);

	void cancelBooking(Long bookingId);

	BookedRoom findByBookingConfiramtionCode(String confirmationCode);

	List<BookedRoom> getAllBookings();

}
