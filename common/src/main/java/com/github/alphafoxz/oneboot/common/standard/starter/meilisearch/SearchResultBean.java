package com.github.alphafoxz.oneboot.common.standard.starter.meilisearch;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class SearchResultBean {
    ArrayList<HashMap<String, Object>> hits;
    Object facetDistribution;
    int processingTimeMs;
    String query;
    int offset;
    int limit;
    int estimatedTotalHits;
}
