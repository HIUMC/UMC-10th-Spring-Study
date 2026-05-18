package umc10th.assignment.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc10th.assignment.global.security.entity.AuthMember;
import umc10th.assignment.user.code.UserErrorCode;
import umc10th.assignment.user.entity.User;
import umc10th.assignment.user.exception.UserException;
import umc10th.assignment.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user= userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return new AuthMember(user);
    }
}
