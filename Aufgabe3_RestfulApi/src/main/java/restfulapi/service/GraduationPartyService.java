package restfulapi.service;

import org.springframework.stereotype.Service;
import restfulapi.command.ReserveBookingCommand;
import restfulapi.command.UpdateBookingCommand;
import restfulapi.dto.BookingDto;

import java.util.List;
import java.util.Optional;

@Service
public interface GraduationPartyService {

    BookingDto reserveBooking(ReserveBookingCommand reserveBookingCommand) throws RuntimeException;

    List<BookingDto> getBookings();

    Optional<BookingDto> getBookingById(String bookingId);

    Optional<BookingDto> updateBooking(String bookingId, UpdateBookingCommand updateBookingCommand);

    Optional<BookingDto> deleteBooking(String bookingId);
}
