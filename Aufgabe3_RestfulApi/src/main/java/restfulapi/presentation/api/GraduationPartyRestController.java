package restfulapi.presentation.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restfulapi.command.ReserveBookingCommand;
import restfulapi.command.UpdateBookingCommand;
import restfulapi.dto.BookingDto;
import restfulapi.service.GraduationPartyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/graduationParty")
public class GraduationPartyRestController {

    @Autowired
    private GraduationPartyService graduationPartyService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingDto> reserveBooking(
            @RequestBody @Valid ReserveBookingCommand reserveBookingCommand
    ) {
        var reserveBooking = graduationPartyService.reserveBooking(reserveBookingCommand);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{bookingId}")
                        .buildAndExpand(reserveBooking.bookingId())
                        .toUri()
        ).body(reserveBooking);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        var found = graduationPartyService.getBookings();
        return found.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(found);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> getBooking(
            @PathVariable String bookingId
    ) {
        return graduationPartyService.getBookingById(bookingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(
            @PathVariable String bookingId,
            @RequestBody @Valid UpdateBookingCommand updateBookingCommand
    ) {
        return graduationPartyService.updateBooking(bookingId, updateBookingCommand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable String bookingId
    ) {
        return graduationPartyService.deleteBooking(bookingId)
                .map(_ ->  ResponseEntity.noContent().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }
}
