package com.github.alphafoxz.oneboot.sdk.service.version;

import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SdkVersionStoreService {
    private final GenRestful genRestfulPart = new GenRestful();

    public static class GenRestful implements VersionStore {
        private final File file = FileUtil.file(SdkConstants.SDK_VERSION_PATH + File.separator + "sdk_gen.json");

        {
            init();
        }

        @Override
        @NonNull
        public File getFile() {
            return file;
        }
    }

    public GenRestful genRestfulStore() {
        return genRestfulPart;
    }
}
