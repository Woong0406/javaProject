package projectnull.javaproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projectnull.javaproject.dto.SignUpDTO;
import projectnull.javaproject.entity.User;
import projectnull.javaproject.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public Boolean signUp(SignUpDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isEmpty()) {
            User entity = User.builder()
                    .username(dto.getUsername())
                    .nickName(dto.getNickName())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .build();
            userRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }
}
