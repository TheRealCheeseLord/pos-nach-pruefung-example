package restfulapi.presentation.api;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import restfulapi.command.ReserveBookingCommand;
import restfulapi.command.UpdateBookingCommand;
import restfulapi.dto.BookingDto;
import restfulapi.service.GraduationPartyService;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GraduationPartyRestController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GraduationPartyRestControllerTest {

    @MockitoBean
    private GraduationPartyService graduationPartyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void can_reserve_booking() throws Exception {
        ReserveBookingCommand reserveBookingCommand = new ReserveBookingCommand(
                "student1",
                10,
                LocalDateTime.now().plusDays(10),
                "theme"
        );

        BookingDto bookingDto = new BookingDto(
                "123",
                reserveBookingCommand.studentName(),
                reserveBookingCommand.numberOfGuests(),
                reserveBookingCommand.desiredDate(),
                reserveBookingCommand.partyTheme(),
                "created"
        );

        when(graduationPartyService.reserveBooking(any())).thenReturn(bookingDto);

        mockMvc.perform(post("/api/graduationParty/bookings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(reserveBookingCommand)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(bookingDto.bookingId()))
                .andExpect(jsonPath("$.studentName").value(bookingDto.studentName()));
    }

    @Test
    void can_get_bookings() throws Exception {
        BookingDto bookingDto = new BookingDto(
                "123",
                "student1",
                10,
                LocalDateTime.now().plusDays(10),
                "theme",
                "created"
        );

        when(graduationPartyService.getBookings()).thenReturn(List.of(bookingDto));

        mockMvc.perform(get("/api/graduationParty/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookingId").value(bookingDto.bookingId()));
    }

    @Test
    void get_bookings_returns_no_content_on_empty_list() throws Exception {
        when(graduationPartyService.getBookings()).thenReturn(List.of());

        mockMvc.perform(get("/api/graduationParty/bookings"))
                .andExpect(status().isNoContent());
    }

    @Test
    void can_get_booking_by_id() throws Exception {
        BookingDto bookingDto = new BookingDto(
                "123",
                "student1",
                10,
                LocalDateTime.now().plusDays(10),
                "theme",
                "created"
        );

        when(graduationPartyService.getBookingById(any())).thenReturn(Optional.of(bookingDto));

        mockMvc.perform(get("/api/graduationParty/bookings/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(bookingDto.bookingId()));
    }

    @Test
    void get_booking_by_id_returns_not_found() throws Exception {
        when(graduationPartyService.getBookingById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/graduationParty/bookings/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void can_update_booking() throws Exception {
        UpdateBookingCommand updateBookingCommand = new UpdateBookingCommand(
                "studentNew",
                15,
                LocalDateTime.now().plusDays(15),
                "themeNew"
        );

        BookingDto bookingDto = new BookingDto(
                "123",
                updateBookingCommand.studentName(),
                updateBookingCommand.numberOfGuests(),
                updateBookingCommand.desiredDate(),
                updateBookingCommand.partyTheme(),
                "updated"
        );

        when(graduationPartyService.updateBooking(any(), any())).thenReturn(Optional.of(bookingDto));

        mockMvc.perform(put("/api/graduationParty/bookings/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateBookingCommand)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(bookingDto.bookingId()))
                .andExpect(jsonPath("$.studentName").value(bookingDto.studentName()))
                .andExpect(jsonPath("$.status").value(bookingDto.status()));
    }

    @Test
    void update_booking_returns_not_found() throws Exception {
        UpdateBookingCommand updateBookingCommand = new UpdateBookingCommand(
                "studentNew",
                15,
                LocalDateTime.now().plusDays(15),
                "themeNew"
        );

        when(graduationPartyService.updateBooking(any(), any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/graduationParty/bookings/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateBookingCommand)))
                .andExpect(status().isNotFound());
    }

    @Test
    void can_cancel_booking() throws Exception {
        BookingDto bookingDto = new BookingDto(
                "123",
                "student1",
                10,
                LocalDateTime.now().plusDays(10),
                "theme",
                "created"
        );

        when(graduationPartyService.deleteBooking(any())).thenReturn(Optional.of(bookingDto));

        mockMvc.perform(delete("/api/graduationParty/bookings/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void cancel_booking_returns_not_found() throws Exception {

        when(graduationPartyService.deleteBooking(any())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/graduationParty/bookings/123"))
                .andExpect(status().isNotFound());
    }
}
