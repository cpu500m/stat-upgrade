package maple.statupgrade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {

    @JsonProperty(value = "ocid")
    private String id;
}
