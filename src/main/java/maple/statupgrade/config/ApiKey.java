package maple.statupgrade.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiKey {
    @Value("${key}")
    private String key;
}
