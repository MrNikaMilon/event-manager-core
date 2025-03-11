package com.nikamilon.api.security;

import com.nikamilon.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventManagerUserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public EventManagerUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email.formatted("Not found user with email: ")));
        log.info("Found user: {}", foundUser);
        return EventManagerUserDetails.builder()
                .user(foundUser)
                .build();
    }
}
