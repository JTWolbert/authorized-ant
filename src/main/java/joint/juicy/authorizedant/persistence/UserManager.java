package joint.juicy.authorizedant.persistence;

import joint.juicy.authorizedant.security.User;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.jooq.codegen.maven.example.tables.Users.USERS;

@Service
@Transactional
@RequiredArgsConstructor
public class UserManager implements IUserManager {

    private final DSLContext ctx;

    @Override
    public int createUser(String userName, String password) {
        return ctx.insertInto(USERS, USERS.USER_NAME, USERS.USER_PASSWORD).values(userName, password).execute();
    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        return Optional.ofNullable(
                ctx.select().from(USERS).where(USERS.USER_NAME.eq(userName)).fetchAny().into(User.class)
        ).orElseThrow(
                () -> new UsernameNotFoundException(userName + " not found")
        );
    }
}
