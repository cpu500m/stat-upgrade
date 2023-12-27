package maple.statupgrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import maple.statupgrade.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ConnectTest {
    @Test
    public void 오픈API_연결_테스트() throws Exception{
        //given
        try {
            // WebClient 인스턴스 생성
            WebClient webClient = WebClient.create("https://open.api.nexon.com");

            // API 엔드포인트 경로 지정
            String apiEndpoint = "/maplestory/v1/id";

            // URL 파라미터 추가
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("character_name", URLEncoder.encode("폭력적인딜링", StandardCharsets.UTF_8));

            // 헤더 추가
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-nxopen-api-key", "발급키");
            headers.add("content-type", "application/json");

            // API 호출 및 응답을 받아오는 코드
            String response = webClient.get()
                    .uri(uriBuilder -> UriComponentsBuilder.fromPath(apiEndpoint)
                            .queryParam("character_name", queryParams.get("character_name"))
                            .build()
                            .toUri())
                    .headers(h -> h.putAll(headers))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // 응답 데이터 출력
            System.out.println("API 응답: " + response);
        }catch (Exception e){
            e.printStackTrace();
        }
        //when

        //then
    }

    @Test
    public void WebClient없이_테스트() throws Exception{
        try {
            String API_KEY = "발급키";
            String characterName = URLEncoder.encode("폭력적인딜링", StandardCharsets.UTF_8);

            String urlString = "https://open.api.nexon.com/maplestory/v1/id?character_name=" + characterName;
            URL url = new URL(urlString);

            // HTTP connection 설정
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if(responseCode == 200) {
                // responseCode 200 정상응답
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                // responseCode 200 이외의 코드가 반환되었을 경우
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(response.toString(), User.class);
            System.out.println(user.getId());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void WebClient_테스트() throws Exception {
        try {
            String API_KEY = "발급키";
            String characterName = "폭력적인딜링"; // 이미 인코딩된 문자열 사용

            String baseUrl = "https://open.api.nexon.com/maplestory/v1/id";

            WebClient webClient = WebClient.builder()
                    .baseUrl(baseUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader("x-nxopen-api-key", API_KEY)
                    .build();

            String urlString = String.format("%s?character_name={characterName}", baseUrl);

            // HTTP GET 요청
            User user = webClient.get()
                    .uri(urlString, characterName)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block(); // 블록킹 호출, 실제 사용시에는 subscribe 또는 다른 방식의 비동기 호출을 고려

            System.out.println(user.getId());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
