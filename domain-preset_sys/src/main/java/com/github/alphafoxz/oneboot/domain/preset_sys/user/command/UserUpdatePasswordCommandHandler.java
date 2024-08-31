package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdatePasswordCommandHandler {
    private final UserRepo userRepo;

    public void handle(UserUpdatePasswordCommand command) {
        var userAgg = userRepo.findById(command.userId());
        userAgg.handleUpdatePassword(command);
        userRepo.save(userAgg);
    }
}
