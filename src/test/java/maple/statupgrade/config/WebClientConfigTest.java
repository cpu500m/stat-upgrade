package maple.statupgrade.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebClientConfigTest {
    @Autowired
    WebClient webClient;

    @Test
    public void WebClient_등록하는가() throws Exception{
        Assertions.assertThat(webClient).isNotNull();
    }
}