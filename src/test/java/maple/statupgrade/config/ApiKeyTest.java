package maple.statupgrade.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring을 통해 정상적으로 key값을 properties 파일에서 읽어오는지를 확인
 */
@SpringBootTest
class ApiKeyTest {
    @Autowired
    private ApiKey apiKey;
    @Test
    public void 설정파일에서_키값_읽어오기() throws Exception{
        //given
        String key = apiKey.getKey();
        //when
        System.out.println("key = " + key);
        Assertions.assertThat(key).isNotNull();
        //then
    }
}