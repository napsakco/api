package co.napsak.api.service;

import co.napsak.api.model.domain.User;
import co.napsak.api.model.exception.NotFoundException;
import co.napsak.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Scheduler scheduler;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Qualifier("slowIOScheduler") Scheduler scheduler) {
        this.userRepository = userRepository;
        this.scheduler = scheduler;
    }

}
