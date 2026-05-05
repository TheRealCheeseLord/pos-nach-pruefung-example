package restfulapi.presentation.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GlobalControllerAdviceTest {

    private final GlobalControllerAdvice globalControllerAdvice = new GlobalControllerAdvice();

    @Test
    void can_return_valid_problem_detail_for_validation_exception() {

        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("Validation Failed");

        var problemDetail = globalControllerAdvice.handleMethodArgumentNotValidException(
                methodArgumentNotValidException
        );

        assertThat(problemDetail).isNotNull();
        assertThat(problemDetail.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertNotNull(problemDetail.getBody());
        assertThat(problemDetail.getBody().getDetail()).isEqualTo("Validation Failed");
    }

    @Test
    void can_return_valid_problem_detail_for_throwable() {
        Throwable throwable = mock(Throwable.class);
        when(throwable.getMessage()).thenReturn("Internal Error");

        var problemDetail = globalControllerAdvice.handleThrowable(throwable);

        assertThat(problemDetail).isNotNull();
        assertThat(problemDetail.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertNotNull(problemDetail.getBody());
        assertThat(problemDetail.getBody().getDetail()).isEqualTo("Internal Error");
    }

}
