package co.napsak.api.service;

import co.napsak.api.model.domain.User;
import co.napsak.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class UserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final Scheduler scheduler;

    @Autowired
    public UserDetailsService(UserRepository userRepository,
                              @Qualifier("slowIOScheduler") Scheduler scheduler) {
        this.userRepository = userRepository;
        this.scheduler = scheduler;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.justOrEmpty(userRepository.findByUsername(username))
                .publishOn(scheduler);
    }
}
