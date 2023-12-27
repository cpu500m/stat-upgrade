package maple.statupgrade.api;

import lombok.RequiredArgsConstructor;
import maple.statupgrade.api.ref.URLConst;
import maple.statupgrade.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ApiClient {
    private final WebClient webClient;

    /**
     * 캐릭터명을 받아 ocid를 가져온다.
     */
    public String getId(String characterName) {

        String requestURL = URLConst.BASE_URL+"/id";
        String urlString = String.format("%s?character_name={characterName}",requestURL);

        // HTTP GET 요청
        UserDto userDto = webClient.get()
                .uri(urlString, characterName)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block(); // 블록킹 호출, 실제 사용시에는 subscribe 또는 다른 방식의 비동기 호출을 고려

        return userDto.getId();
    }

    /**
     * ocid를 기반으로 장착 장비를 가져온다.
     */
    //TODO
    public String getItem(String id){
        /**
         * - 2023년 12월 21일 데이터부터 조회할 수 있습니다.
         * - 캐릭터 정보 조회 API는 일자별 데이터로 매일 오전 1시부터 전일 데이터 조회가 가능합니다. (예를 들어, 12월 22일 데이터를 조회하면 22일 00시부터 23일의 00시 사이의 데이터가 조회됩니다.)

            +위 정보를 기반으로 LocalDateTime으로 받아서 날짜 비교하고 Query로 보내는 Date를 맞춰주는 작업이 필요.
         */
        String requestURL = URLConst.BASE_URL+"/character/item-equipment";
        String urlString = String.format("%s?ocid={id}&",requestURL);

/*        UserDto userDto = webClient.get()
                .uri(urlString, characterName)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();*/

//        return userDto.getId();
        return null;
    }

}
