package joint.juicy.authorizedant.rest;

import joint.juicy.authorizedant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserJson userJson) {
        try {
            userService.createUser(userJson.name(), userJson.password());
            log.info("{} created", userJson.name());
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            log.error("ERROR {} not created : {}", userJson.name(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UserJson userJson) {
        try {
            String jwt = userService.loginUser(userJson.name(), userJson.password());
            log.info("{} authenticated", userJson.name());
            return ResponseEntity.ok().body(jwt);
        } catch (Exception exception) {
            log.info("ERROR {} not authenticated : {}", userJson.name(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    record UserJson(String name, String password) {
    }
}
