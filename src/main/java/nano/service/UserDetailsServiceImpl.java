package nano.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nano.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AccountService service;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account acc = service.getAccountByUsername(username);
		List<GrantedAuthority> roles = buildRoles(acc.getRole());
        return userWithRoles(acc, roles);
		
	}
	private List<GrantedAuthority> buildRoles(String role) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + role));
        return roles;
    }

    private UserDetails userWithRoles(Account acc, List<GrantedAuthority> roles) {
        return new org.springframework.security.core.userdetails.User(
               acc.getUsername(), acc.getPassword(), true, true, true, true, roles);
    }

}
