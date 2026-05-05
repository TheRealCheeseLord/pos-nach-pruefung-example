package restfulapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import restfulapi.service.GraduationPartyService;

@SpringBootTest
class RestfulApiApplicationTest {

    // Needed because it's an interface, for the context text not to fail
    @MockitoBean
    private GraduationPartyService graduationPartyService;


    @Test
    void contextLoads() {
        // successful when no exception is throw
    }
}
