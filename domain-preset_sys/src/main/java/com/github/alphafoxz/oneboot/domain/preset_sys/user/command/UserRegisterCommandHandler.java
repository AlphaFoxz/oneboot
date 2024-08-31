package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserAgg;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterCommandHandler {
    private final UserRepo userRepo;

    public UserAgg handle(UserRegisterCommand command) {
        var userAgg = userRepo.findByUsername(command.username());
        userAgg.handleRegister(command);
        userRepo.save(userAgg);
        return userAgg;
    }
}
