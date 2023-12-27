package maple.statupgrade.config;

import lombok.RequiredArgsConstructor;
import maple.statupgrade.api.ref.URLConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 발급받은 API-KEY를 singleton으로 관리.
 */
@Configuration
public class WebClientConfig {

    @Value("${key}")
    private String apiKey;
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(URLConst.BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("x-nxopen-api-key", apiKey)
                .build();
    }
}
