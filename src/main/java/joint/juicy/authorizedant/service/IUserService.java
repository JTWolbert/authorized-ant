package joint.juicy.authorizedant.service;

public interface IUserService {
    int createUser(String email, String password);

    String loginUser(String email, String password);
}
