package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.BookingRequestDto;
import org.example.evaluations.evaluation.dtos.BookingResponseDto;
import org.example.evaluations.evaluation.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/guest/{guestEmail}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByEmail(@PathVariable("guestEmail") String guestEmail){

        List<BookingResponseDto> bookings = this.bookingService.getAllBookingsPerGuest(guestEmail);

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable("bookingId") Long bookingId){

        BookingResponseDto booking = this.bookingService.getBooking(bookingId);

        return ResponseEntity.ok(booking);

    }

    @PostMapping("")
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto requestDto){

        BookingResponseDto booking = this.bookingService.createBooking(requestDto);

        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> updateBooking(
            @PathVariable("bookingId") Long bookingId,
            @RequestBody BookingRequestDto requestDto)
    {
        BookingResponseDto booking = this.bookingService.replaceBooking(bookingId, requestDto);

        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Boolean> deleteBooking(@PathVariable("bookingId") Long bookingId){

        Boolean result = this.bookingService.deleteBooking(bookingId);

        return ResponseEntity.ok(result);
    }



}
