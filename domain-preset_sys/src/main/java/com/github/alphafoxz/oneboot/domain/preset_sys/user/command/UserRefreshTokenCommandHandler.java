package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserRepo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRefreshTokenCommandHandler {
    private final UserRepo userRepo;

    public TokenVo handle(UserRefreshTokenCommand command) {
        var userAgg = userRepo.findById(command.userId());
        var token = userAgg.handleRefreshToken(command);
        userRepo.save(userAgg);
        return token;
    }
}
