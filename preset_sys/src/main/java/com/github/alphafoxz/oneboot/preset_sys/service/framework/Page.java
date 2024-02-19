package com.github.alphafoxz.oneboot.preset_sys.service.framework;

import com.github.alphafoxz.oneboot.core.standard.restful.CustomPage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
public class Page<T> implements CustomPage {
    public static <T> Page<T> from(org.springframework.data.domain.Page<T> page) {
        return new Page<>(page.getContent(), page.getPageable(), page.getTotalElements());
    }

    private Boolean success = true;
    private DataBean<T> data;

    public Page(List<T> content, Pageable pageable, Long total) {
        data = new DataBean<>();
        data.setList(content);
        data.setTotal(total);
        data.setPageSize(pageable.getPageSize());
        data.setCurrentPage(pageable.getPageNumber() + 1);
    }

    @Getter
    @Setter
    public static class DataBean<T> {
        private List<T> list;
        private Long total;
        private Integer pageSize;
        private Integer currentPage;
    }
}
