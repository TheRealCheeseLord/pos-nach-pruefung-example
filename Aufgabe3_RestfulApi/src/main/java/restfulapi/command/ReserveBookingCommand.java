package restfulapi.command;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReserveBookingCommand(
        @NotNull String studentName,
        @NotNull @Min(1) @Max(50) Integer numberOfGuests,
        @NotNull @Future LocalDateTime desiredDate,
        @NotNull String partyTheme
) {}
