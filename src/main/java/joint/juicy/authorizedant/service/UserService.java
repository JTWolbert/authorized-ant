package joint.juicy.authorizedant.service;

import joint.juicy.authorizedant.persistence.UserManager;
import joint.juicy.authorizedant.security.JwtTokenUtil;
import joint.juicy.authorizedant.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserManager userManager;
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int createUser(String name, String password) {
        if (
                name.length() < 3
        ) {
            throw new RuntimeException("8ec60146be3811edafa10242ac120002");
        }
        if (
                password.length() < 3
        ) {
            throw new RuntimeException("c02e683abe3911edafa10242ac120002");
        }
        if (
                name.length() + password.length() > 64
        ) {
            throw new RuntimeException("98037004be3811edafa10242ac120002");
        }
        try {
            return userManager.createUser(
                    name,
                    passwordEncoder.encode(password)
            );
        } catch (Exception e) {
            throw new RuntimeException("9df999cabe3811edafa10242ac120002");
        }
    }

    @Override
    public String loginUser(String name, String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, password)
        );
        User user = (User) authentication.getPrincipal();
        return jwtTokenUtil.generateAccessToken(user);
    }
}
