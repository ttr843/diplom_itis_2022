package ru.itis.websiteservice.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.user.entity.User;
import ru.itis.pashin.website.common.service.repository.UserRepository;

import java.util.Optional;

import static ru.itis.pashin.website.common.model.service.enumeration.ServiceErrorCode.ERROR_CODE_0000;

@Service(value = "SiteUserDerailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UsernameNotFoundException(ERROR_CODE_0000.getMessage());
    }
}
