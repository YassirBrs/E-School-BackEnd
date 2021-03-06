package com.virtualpairprogrammers.roombooking.rest;

import java.util.List;

import com.virtualpairprogrammers.roombooking.ApiClass.ApiBooking;
import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.virtualpairprogrammers.roombooking.data.BookingRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Booking;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class RestBookingController {
	
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	UserRepository userRepository;
	@GetMapping("/booking")
	public List<Booking> getBooking(){return bookRepo.findAll();}

//	@GetMapping("/booking/{dateInString}")
//	public List<Booking> getBookingByDate(@PathVariable("dateInString") String date){
//		Date dateInSql=Date.valueOf(date);
//		return bookRepo.findAllByDate(dateInSql);
//	}
	
	@DeleteMapping("/deleteBooking/{id}")
	public void deleteBooking(@PathVariable("id")int id) {
		bookRepo.deleteById(id);
	}
	
	@GetMapping("/booking/{id}")
	public Booking getBookingById(@PathVariable("id") int id) {
		return bookRepo.findById(id).get();
	}

	@PostMapping("/booking/add")
	public String save(@RequestBody ApiBooking apiBooking) {
		Booking booking=new Booking();
		booking.setDate(apiBooking.getDate());
		booking.setStartTime(java.sql.Time.valueOf(apiBooking.getStart() + ":00"));
		booking.setEndTime(java.sql.Time.valueOf(apiBooking.getEnd() + ":00"));
		booking.setTitle(apiBooking.getTitle());
		booking.setRoom(roomRepository.findById(apiBooking.getRoomId()).get());
		booking.setUser(userRepository.findById(apiBooking.getUserId()).get());

//		//Check if new reservation can be created
//		boolean bookingStatus = bookingService.getBookingStatus(booking);
		List<Booking> book1 = bookRepo.findByRoom_IdAndDateAndStartTimeBetween(booking.getRoom().getId(),booking.getDate(),booking.getStartTime(),booking.getEndTime());
		List<Booking> book2 = bookRepo.findByRoom_IdAndDateAndStartTimeIsLessThanEqualAndEndTimeIsGreaterThanEqual(booking.getRoom().getId(),booking.getDate(),booking.getStartTime(),booking.getStartTime());

		//If status is true create a new reservation
		if(book1.isEmpty()&&book2.isEmpty()) {
			bookRepo.save(booking);
			return "oui";
		}else {
			return "non";
		}
	}
	@PutMapping("/booking/{id}")
	public String updateBooking(@PathVariable("id") int id,@RequestBody ApiBooking apiBooking) {
		Booking b= bookRepo.findById(id).get();
		b.setRoom(roomRepository.findById(apiBooking.getRoomId()).get());
		b.setUser(userRepository.findById(apiBooking.getUserId()).get());
		b.setDate(apiBooking.getDate());
		b.setEndTime(java.sql.Time.valueOf(apiBooking.getEnd()));
		b.setStartTime(java.sql.Time.valueOf(apiBooking.getStart()));
		b.setTitle(apiBooking.getTitle());

		List<Booking> book1 = bookRepo.findByRoom_IdAndDateAndStartTimeBetween(b.getRoom().getId(),b.getDate(),b.getStartTime(),b.getEndTime());
		List<Booking> book2 = bookRepo.findByRoom_IdAndDateAndStartTimeIsLessThanEqualAndEndTimeIsGreaterThanEqual(b.getRoom().getId(),b.getDate(),b.getStartTime(),b.getStartTime());


		if(book1.isEmpty()&&book2.isEmpty()) {
			bookRepo.save(b);
			return "oui";
		}else {
			return "non";
		}
	}
}
