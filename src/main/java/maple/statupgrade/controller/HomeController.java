package maple.statupgrade.controller;

import lombok.RequiredArgsConstructor;
import maple.statupgrade.api.ApiClient;
import maple.statupgrade.service.SpecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final SpecService specService;
    private final ApiClient apiClient;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/name/{characterName}")
    public String specRecommand(@PathVariable(name = "characterName") String name, Model model){
        // 1. 외부 API로 부터 캐릭터 id값을 받아옴.
        String id = apiClient.getId(name);
        // 2. id값을 이용하여 해당 캐릭터의 장착 장비 정보를 받아온다.
        // 3. item을 분석한다 (SpecService 내 메서드 작성)
        // 4. Model에 정보를 담아 뷰로 보낸다
        // 5. thymeleaf를 이용하여 출력
        return null;
    }
}
