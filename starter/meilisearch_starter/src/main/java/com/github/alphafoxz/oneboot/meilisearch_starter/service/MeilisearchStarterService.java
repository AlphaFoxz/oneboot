package com.github.alphafoxz.oneboot.meilisearch_starter.service;

import com.github.alphafoxz.oneboot.common.standard.starter.meilisearch.MeilisearchService;
import com.github.alphafoxz.oneboot.common.standard.starter.meilisearch.SearchRequestBean;
import com.github.alphafoxz.oneboot.common.standard.starter.meilisearch.SearchResultBean;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import com.github.alphafoxz.oneboot.meilisearch_starter.MeilisearchStarterProperties;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.Key;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.TaskStatus;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MeilisearchStarterService implements MeilisearchService {
    @Resource
    private MeilisearchModuleConvertor meilisearchModuleConvertor;
//    @Resource
//    private MeilisearchStarterProperties meilisearchStarterProperties;
    @Resource
    private Client client;

    @Override
    public void createApiKey() {
        long t = System.currentTimeMillis();
        Date expDate = new Date(t + 1000 * 60 * 60 * 24 * 365);
        Key keyInfo = new Key();
        keyInfo.setDescription("Add documents: Products API key");
        keyInfo.setActions(new String[]{"*"});
        keyInfo.setIndexes(new String[]{"*"});
        keyInfo.setExpiresAt(expDate);
        try {
            getClient().createKey(keyInfo);
        } catch (MeilisearchException e) {
            log.error("createApiKey error: ", e);
        }
    }

    @Nullable
    @Override
    public String getApiKey() {
        Key[] keys = null;
        try {
            keys = getClient().getKeys().getResults();
        } catch (MeilisearchException e) {
            throw new RuntimeException(e);
        }
        if (ArrayUtil.isEmpty(keys)) {
            return null;
        }
        return keys[0].getKey();
    }

    @Override
    public boolean addDocuments(@NonNull String index, @NonNull List<?> documents) {
        return addDocuments(index, JSONUtil.toJsonStr(documents));
    }

    @Override
    public boolean addDocuments(@NonNull String index, @NonNull String documentsJsonArr) {
        try {
            TaskStatus status = getClient().index(index).addDocuments(documentsJsonArr).getStatus();
            return TaskStatus.SUCCEEDED.equals(status);
        } catch (MeilisearchException e) {
            log.error("addDocuments error: ", e);
        }
        return false;
    }

    @Override
    public boolean deleteIndex(@NonNull String index) {
        try {
            TaskStatus status = getClient().deleteIndex(index).getStatus();
            return TaskStatus.SUCCEEDED.equals(status);
        } catch (MeilisearchException e) {
            log.error("deleteIndex error: ", e);
        }
        return false;
    }

    @Override
    public boolean deleteDocuments(@NonNull String index, @NonNull List<String> documentsIds) {
        try {
            TaskStatus status = getClient().index(index).deleteDocuments(documentsIds).getStatus();
            return TaskStatus.SUCCEEDED.equals(status);
        } catch (MeilisearchException e) {
            log.error("deleteDocuments error: ", e);
        }
        return false;
    }

    @Override
    public <T extends Serializable> T getDocument(@NonNull String index, @NonNull String id, @NonNull Class<T> targetClass) {
        try {
            return getClient().index(index).getDocument(id, targetClass);
        } catch (MeilisearchException e) {
            log.error("getDocument error: ", e);
        }
        return null;
    }

    @Override
    @Nullable
    public SearchResultBean search(@NonNull String index, @NonNull String str) {
        try {
            //TODO 测试str是否支持空，如果是空就应该返回无条件查询列表？
            SearchResult search = getClient().index(index).search(str);
            return meilisearchModuleConvertor.searchResult2Bean(search);
        } catch (MeilisearchException e) {
            log.error("search error: ", e);
        }
        return null;
    }

    public SearchResultBean search(@NonNull String index, @NonNull SearchRequestBean searchRequest) {
        try {
            SearchResult search = (SearchResult) getClient().index(index).search(meilisearchModuleConvertor.bean2SearchRequest(searchRequest));
            return meilisearchModuleConvertor.searchResult2Bean(search);
        } catch (MeilisearchException e) {
            log.error("search error: ", e);
        }
        return null;
    }

    private Client getClient() {
        return client;
//        return new Client(new Config(meilisearchStarterProperties.getUrl(), meilisearchStarterProperties.getMasterKey()));
    }
}
