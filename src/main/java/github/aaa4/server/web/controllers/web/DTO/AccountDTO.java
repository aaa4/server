package github.aaa4.server.web.controllers.web.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.aaa4.server.entity.Account;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO implements Serializable{


    public AccountDTO(Account account){
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
    }

    public AccountDTO(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;


}
