package joint.juicy.authorizedant.security;

import org.jooq.codegen.maven.example.tables.records.UsersRecord;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User extends UsersRecord implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setEmail(String email) {
        this.setUserEmail(email);
    }

    public void setId(int id) {
        this.setUserPk(id);
    }

    public int getId() {
        return getUserPk();
    }

    public String getEmail() {
        return getUserEmail();
    }

    @Override
    public String getPassword() {
        return getUserPassword();
    }

    @Override
    public String getUsername() {
        return getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
