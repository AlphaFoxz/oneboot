package com.github.alphafoxz.oneboot.meilisearch_starter.service;

import com.github.alphafoxz.oneboot.core.standard.starter.meilisearch.SearchRequestBean;
import com.github.alphafoxz.oneboot.core.standard.starter.meilisearch.SearchResultBean;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResult;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MeilisearchModuleConvert {
    MeilisearchModuleConvert INSTANCE = Mappers.getMapper(MeilisearchModuleConvert.class);

    public SearchResultBean searchResult2Bean(SearchResult searchResult);

    public SearchRequest bean2SearchRequest(SearchRequestBean searchRequestBean);
}
