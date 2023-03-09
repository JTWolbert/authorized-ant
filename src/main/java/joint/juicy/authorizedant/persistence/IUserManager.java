package joint.juicy.authorizedant.persistence;

import joint.juicy.authorizedant.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.jooq.codegen.maven.example.tables.Users.USERS;

public interface IUserManager extends UserDetailsService {

    int createUser(String email, String password);

    @Override
    User loadUserByUsername(String userEmail) throws UsernameNotFoundException;
}
