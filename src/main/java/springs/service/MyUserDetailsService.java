package springs.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import springs.config.UserPrincipal;
import springs.dto.UserDto;
import springs.repository.UserRepo;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepo rep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user=rep.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new UserPrincipal(user);
	}

}
