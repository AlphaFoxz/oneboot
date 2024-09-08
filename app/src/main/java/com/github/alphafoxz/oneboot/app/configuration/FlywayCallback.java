package com.github.alphafoxz.oneboot.app.configuration;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootConfigException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.flywaydb.core.internal.info.MigrationInfoImpl;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlywayCallback implements Callback {
    private final FlywayAutoConfiguration.FlywayConfiguration flywayConfiguration;

    @Override
    @SneakyThrows
    public boolean supports(Event event, Context context) {
        if (event == Event.BEFORE_CLEAN) {
            log.info("跳过flyway clean阶段，防止误删库");
            return false;
        }
        if (event == Event.BEFORE_EACH_MIGRATE || event == Event.BEFORE_EACH_UNDO || event == Event.BEFORE_REPEATABLES) {
            if (context == null) {
                return true;
            }
            var info = (MigrationInfoImpl) context.getMigrationInfo();
            if (info == null || info.getResolvedMigration() == null) {
                return true;
            }
            //FIXME 打包后路径问题
            var filePath = info.getResolvedMigration().getPhysicalLocation();
            try (var fileReader = FileUtil.getUtf8Reader(filePath)) {
                String line;
                boolean valid = false;
                while ((line = fileReader.readLine()) != null) {
                    if (StrUtil.isBlank(line) || StrUtil.startWith(line, "--")) {
                        continue;
                    }
                    if (ReUtil.isMatch("^[\\s]*set[\\s]+search_path[\\s]*=.*$", line)) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    throw new OnebootConfigException("脚本必须以set search_path = xxx;开头。防止误操作", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return true;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public void handle(Event event, Context context) {
    }

    @Override
    public String getCallbackName() {
        return "";
    }


}
