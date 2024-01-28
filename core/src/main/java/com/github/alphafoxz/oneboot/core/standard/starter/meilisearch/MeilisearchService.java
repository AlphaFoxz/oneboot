package com.github.alphafoxz.oneboot.core.standard.starter.meilisearch;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

public interface MeilisearchService {
    void createApiKey();

    @Nullable
    String getApiKey();

    boolean addDocuments(@NonNull String index, @NonNull List<?> documents);

    boolean addDocuments(@NonNull String index, @NonNull String documentsJsonArr);

    boolean deleteIndex(@NonNull String index);

    boolean deleteDocuments(@NonNull String index, @NonNull List<String> documentsIds);

    @Nullable
    <T extends Serializable> T getDocument(@NonNull String index, @NonNull String id, @NonNull Class<T> targetClass);

    SearchResultBean search(@NonNull String index, @NonNull String str);
}
