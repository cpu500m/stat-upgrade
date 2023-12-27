package maple.statupgrade.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiClientTest {
    @Autowired
    private ApiClient apiClient;

    @Test
    public void 주입이제대로됐는가() throws Exception{
        assertThat(apiClient).isNotNull();
    }
    @Test
    @DisplayName(value = "Ocid_제대로_가져오는가")
    public void getIdTest() throws Exception{
        //given
        String characterName = "폭력적인딜링";
        //when
        String ocid = apiClient.getId(characterName);
        System.out.println("ocid = " + ocid);

        //then
        assertThat(ocid).isNotNull();
    }
    @Test
    @DisplayName(value = "장착아이템_제대로_가져오는가")
    public void getItemTest() throws Exception{
        //given
        String s = LocalDate.now().toString();
        System.out.println("s = " + s);
        //when

        //then
    }
}