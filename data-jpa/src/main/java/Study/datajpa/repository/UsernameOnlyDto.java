package Study.datajpa.repository;

public class UsernameOnlyDto {

    private final  String username;

    public UsernameOnlyDto(String username){ // 매개변수 이름이 중요
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
