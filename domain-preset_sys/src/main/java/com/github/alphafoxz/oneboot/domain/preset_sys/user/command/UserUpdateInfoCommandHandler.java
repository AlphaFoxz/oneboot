package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateInfoCommandHandler {
    private final UserRepo userRepo;

    public void handle(UserUpdateInfoCommand command) {
        var userAgg = UserRepo.getInstance().findById(command.userId());
        userAgg.handleUpdateInfo(command);
        userRepo.save(userAgg);
    }
}
