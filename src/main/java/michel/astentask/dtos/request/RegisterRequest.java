package michel.astentask.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
    
    private String name;
    private String email;
    private String password;

    public RegisterRequest(){}

    public RegisterRequest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
