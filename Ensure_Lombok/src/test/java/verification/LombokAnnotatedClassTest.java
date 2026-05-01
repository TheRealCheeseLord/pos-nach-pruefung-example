package verification;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LombokAnnotatedClassTest {

    @Test
    public void test() {
        var instance = new LombokAnnotatedClass();
        var i2 = LombokAnnotatedClass.builder().name("test").build();

        assertThat(i2.getName()).isEqualTo("test");
    }
}