package com.nikamilon.api;

import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.model.dictionary.UserRole;
import com.nikamilon.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Deprecated(forRemoval = true)
//При выкатке в прод напсиать в скрипте ликуибазе юзера админа
@Component
@AllArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.findByEmail("admin@mail.com").isEmpty()) {
            UserEntity admin = UserEntity.builder()
                    .name("admin")
                    .password("admin")
                    .email("admin@admin.com")
                    .build();
            userRepository.save(admin);
        }
    }
}
