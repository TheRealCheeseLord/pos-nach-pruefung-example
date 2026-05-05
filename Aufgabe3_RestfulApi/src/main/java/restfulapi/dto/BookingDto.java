package restfulapi.dto;

import java.time.LocalDateTime;

public record BookingDto(
        String bookingId,
        String studentName,
        Integer numberOfGuests,
        LocalDateTime desiredDate,
        String partyTheme,
        String status
) {}
