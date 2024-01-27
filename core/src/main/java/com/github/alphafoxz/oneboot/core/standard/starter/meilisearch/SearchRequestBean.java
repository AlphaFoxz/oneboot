package com.github.alphafoxz.oneboot.core.standard.starter.meilisearch;

import cn.hutool.json.JSONObject;
import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@Accessors(chain = true)
public class SearchRequestBean {
    private String q;
    private Integer offset;
    private Integer limit;
    private String[] attributesToRetrieve;
    private String[] attributesToCrop;
    private Integer cropLength;
    private String cropMarker;
    private String highlightPreTag;
    private String highlightPostTag;
    private MatchingStrategy matchingStrategy;
    private String[] attributesToHighlight;
    private String[] attributesToSearchOn;
    private String[] filter;
    private String[][] filterArray;
    private Boolean showMatchesPosition;
    private String[] facets;
    private String[] sort;
    protected Integer page;
    protected Integer hitsPerPage;

    /**
     * Constructor for SearchRequest for building search queries with the default values: offset: 0,
     * limit: 20, attributesToRetrieve: ["*"], attributesToCrop: null, cropLength: 200,
     * attributesToHighlight: null, filter: null, showMatchesPosition: false, facets: null, sort:
     * null
     *
     * @param q Query String
     */
    public SearchRequestBean(String q) {
        this();
        this.q = q;
    }

    /**
     * Method to set the Query String
     *
     * <p>This method is an alias of setQ()
     *
     * @param q Query String
     * @return SearchRequest
     */
    public SearchRequestBean setQuery(String q) {
        return setQ(q);
    }

    /**
     * Method that returns the JSON String of the SearchRequest
     *
     * @return JSON String of the SearchRequest query
     */
    @Override
    public String toString() {
        JSONObject jsonObject =
                new JSONObject()
                        .set("q", this.q)
                        .set("offset", this.offset)
                        .set("limit", this.limit)
                        .set("attributesToRetrieve", this.attributesToRetrieve)
                        .set("cropLength", this.cropLength)
                        .set("cropMarker", this.cropMarker)
                        .set("highlightPreTag", this.highlightPreTag)
                        .set("highlightPostTag", this.highlightPostTag)
                        .set(
                                "matchingStrategy",
                                this.matchingStrategy == null
                                        ? null
                                        : this.matchingStrategy.toString())
                        .set("showMatchesPosition", this.showMatchesPosition)
                        .set("facets", this.facets)
                        .set("sort", this.sort)
                        .set("page", this.page)
                        .set("hitsPerPage", this.hitsPerPage)
                        .putOpt("attributesToCrop", this.attributesToCrop)
                        .putOpt("attributesToHighlight", this.attributesToHighlight)
                        .putOpt("attributesToSearchOn", this.attributesToSearchOn)
                        .putOpt("filter", this.filter)
                        .putOpt("filter", this.filterArray);

        return jsonObject.toString();
    }
}
