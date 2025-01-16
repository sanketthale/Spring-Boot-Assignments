package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.BookingRequestDto;
import org.example.evaluations.evaluation.dtos.BookingResponseDto;
import org.example.evaluations.evaluation.dtos.RoomRequestDto;
import org.example.evaluations.evaluation.models.Booking;
import org.example.evaluations.evaluation.models.Guest;
import org.example.evaluations.evaluation.models.Room;
import org.example.evaluations.evaluation.models.RoomType;
import org.example.evaluations.evaluation.repos.BookingRepo;
import org.example.evaluations.evaluation.repos.RoomRepo;
import org.example.evaluations.evaluation.repos.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class StorageBookingService implements IBookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private GuestRepo guestRepo;

    @Autowired
    private RoomRepo roomRepo;


    @Override
    public BookingResponseDto getBooking(Long bookingId) {

        Optional<Booking> bookingOptional = this.bookingRepo.findById(bookingId);

        if(bookingOptional.isEmpty()) return null;

        return this.from(bookingOptional.get());

    }

    @Override
    public List<BookingResponseDto> getAllBookingsPerGuest(String guestEmail) {

        Optional<Guest> guestOptional = this.guestRepo.findByEmail(guestEmail);

        if(guestOptional.isEmpty()) return null;

        Guest guest = guestOptional.get();

        List<Booking> bookings = this.bookingRepo.findBookingsByGuest(guest);

        List<BookingResponseDto> bookingResponseDtos = new ArrayList<>();

        for(Booking booking: bookings){
            bookingResponseDtos.add(this.from(booking));
        }


        return bookingResponseDtos;
    }

    @Override
    public BookingResponseDto replaceBooking(Long bookingId, BookingRequestDto bookingRequestDto) {

        Boolean isDeleted = this.deleteBooking(bookingId);

        if(!isDeleted) return null;

        return this.createBooking(bookingRequestDto);


    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {

        Booking booking = new Booking();

        Optional<Guest> guestOptional = guestRepo.findByEmail(bookingRequestDto.getCustomerEmail());

        Guest guest;
        if (guestOptional.isEmpty()) {
            guest = new Guest();
            guest.setName(bookingRequestDto.getCustomerName());
            guest.setEmail(bookingRequestDto.getCustomerEmail());
        } else{
            guest = guestOptional.get();
            guest.setName(bookingRequestDto.getCustomerName());
        }

        booking.setGuest(guest);



        List<Room> rooms = new ArrayList<>();
        Double roomsBill = 0.0;
        for(RoomRequestDto requestDto: bookingRequestDto.getRoomRequestDtos()){
            Room room = this.from(requestDto);
            room.setBooking(booking);
            rooms.add(room);
            roomsBill += room.getRent();
        }

//        List<Room> savedRooms = this.roomRepo.saveAll(rooms);


        booking.setRooms(rooms);

        Date checkInDate = this.from(bookingRequestDto.getCheckInDate());
        Date checkOutDate= this.from(bookingRequestDto.getCheckOutDate());

        long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;


        Double totalBill = roomsBill * (diff);
        booking.setTotalBill(totalBill);

        booking.setCheckOutDate(checkOutDate);
        booking.setCheckInDate(checkInDate);

        Booking savedBooking = this.bookingRepo.save(booking);

        return this.from(savedBooking);
    }

    @Override
    public Boolean deleteBooking(Long bookingId) {

        if (bookingRepo.existsById(bookingId)) {
            bookingRepo.deleteById(bookingId);
            return true;
        }
        return false;

    }

    private BookingResponseDto from(Booking booking) {
        BookingResponseDto responseDto = new BookingResponseDto();
        responseDto.setBookingId(booking.getId());
        responseDto.setRooms(booking.getRooms());
        responseDto.setGuest(booking.getGuest());
        responseDto.setTotalBill(booking.getTotalBill());
        responseDto.setCheckOutDate(booking.getCheckOutDate());
        responseDto.setCheckInDate(booking.getCheckInDate());
        return responseDto;
    }

    private Room from(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        room.setRoomType(roomRequestDto.getRoomType());
        if(roomRequestDto.getRoomType().equals(RoomType.DELUXE)) {
            room.setRent(1000D * roomRequestDto.getRoomCount());
        }else if(roomRequestDto.getRoomType().equals(RoomType.SUPER_DELUXE)) {
            room.setRent(1500D * roomRequestDto.getRoomCount());
        }else if(roomRequestDto.getRoomType().equals(RoomType.SUITE)) {
            room.setRent(2500D * roomRequestDto.getRoomCount());
        }

        return room;
    }

    private Date from(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.parse(date);
        }catch (ParseException exception) {
            return null;
        }
    }
}
