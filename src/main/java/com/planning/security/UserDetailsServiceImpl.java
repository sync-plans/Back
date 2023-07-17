package com.planning.security;

import com.planning.user.entity.User;
import com.planning.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @NonNull
    private final UserRepository userRepsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("@@@@@@@@@@@@" + username);
        User user = userRepsitory.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));
        return new UserDetailsImpl(user);
    }
}
