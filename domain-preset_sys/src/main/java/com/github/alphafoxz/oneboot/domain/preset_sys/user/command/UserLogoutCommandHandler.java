package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLogoutCommandHandler {
    private final UserRepo userRepo;

    public void handle(UserLogoutCommand command) {
        var userAgg = userRepo.findById(command.userId());
        userAgg.handleLogout(command);
        userRepo.save(userAgg);
    }
}
