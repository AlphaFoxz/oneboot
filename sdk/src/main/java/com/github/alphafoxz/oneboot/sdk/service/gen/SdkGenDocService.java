package com.github.alphafoxz.oneboot.sdk.service.gen;

import com.deepoove.poi.XWPFTemplate;
import com.github.alphafoxz.oneboot.core.configuration.CoreProperties;
import com.github.alphafoxz.oneboot.core.exceptions.OnebootGenException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 生成文档service
 */
@Service
public class SdkGenDocService {
    @Resource
    private CoreProperties coreProperties;

    public File generateWordApi(String moduleName) {
        String scanPackage = coreProperties.getBasePackage() + "." + moduleName + ".gen.restful.apis";
        Set<Class<?>> classes = ClassUtil.scanPackage(scanPackage);
        List<ApiBean> apiList = CollUtil.newArrayList();
        for (Class<?> clazz : classes) {
            ApiBean apiBean = new ApiBean();
            Tag annotation = clazz.getAnnotation(Tag.class);
            apiBean.setTitle(annotation.description());
            apiList.add(apiBean);
        }
        File file = FileUtil.createTempFile(".docx.tmp", true);
        cn.hutool.core.io.resource.Resource templateRes = ResourceUtil.getResourceObj("classpath:sdk/doc/api_template.docx");
        Map<String, Object> context = MapUtil.newHashMap();
        context.put("apiList", apiList);
        XWPFTemplate temp = XWPFTemplate.compile(templateRes.getStream()).render(context);
        try {
            temp.writeToFile(file.getAbsolutePath());
        } catch (IOException e) {
            throw new OnebootGenException("写文件失败" + file.getAbsolutePath(), HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return file;
    }
}

@Setter
@Getter
class ApiBean {
    String title;
}