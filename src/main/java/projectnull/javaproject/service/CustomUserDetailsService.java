package projectnull.javaproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projectnull.javaproject.dto.CustomUserDetails;
import projectnull.javaproject.entity.User;
import projectnull.javaproject.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        log.info("username             " + username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
        throw new
                UsernameNotFoundException("ㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
    }

}
