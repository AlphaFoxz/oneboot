package com.github.alphafoxz.oneboot.sdk.service.gen;

import com.github.alphafoxz.oneboot.common.configuration.CommonProperties;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootGenCodeException;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ResourceUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.restful.enums.SdkCrudServiceTypeEnum;
import jakarta.annotation.Resource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.ResultQuery;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;

/**
 * 实现生成CURD service
 */
@Service
public class SdkGenCrudService {
    @Resource
    DSLContext dslContext;
    @Resource
    CommonProperties commonProperties;

    /**
     * 生成
     *
     * @param moduleName 模块名称
     * @param poName     表名称
     * @param force      是否强制生成（覆盖原有文件）
     */
    public void generateTableCrud(@NonNull String moduleName, @NonNull String poName, int serviceType, @NonNull Boolean force) {
        String formatStr;
        if (serviceType == SdkCrudServiceTypeEnum.ABAC_CACHED.getValue()) {
            formatStr = ResourceUtil.getResourceObj("classpath:sdk/abac_cached_crud_service.java.vm").readUtf8Str();
        } else if (serviceType == SdkCrudServiceTypeEnum.CACHED.getValue()) {
            formatStr = ResourceUtil.getResourceObj("classpath:sdk/cached_crud_service.java.vm").readUtf8Str();
        } else {
            throw new OnebootGenCodeException("未定义的CURD service类型，请确认传参", HttpStatus.BAD_REQUEST);
        }
        String fileName = StrUtil.upperFirst(poName) + "Crud.java";
        String basePackage = commonProperties.getBasePackage();
        String path = new StringJoiner(File.separator)
                .add(SdkConstants.PROJECT_ROOT_PATH)
                .add(moduleName)
                .add("src")
                .add("main")
                .add("java")
                .add(basePackage.replaceAll("[.]", Matcher.quoteReplacement(File.separator)))
                .add(moduleName).add("service")
                .add("crud")
                .add(fileName).toString();
        FileUtil.mkParentDirs(path);
        Map<String, String> param = MapUtil.newHashMap();
        param.put("basePackage", basePackage);
        param.put("moduleName", moduleName);
        param.put("poName", poName);
        param.put("PoName", StrUtil.upperFirst(poName));
        param.put("PO_NAME", StrUtil.toUnderlineCase(poName).toUpperCase());
        String code = StrUtil.format(formatStr, param);
        if (FileUtil.exist(path) && !force) {
            return;
        }
        FileUtil.writeUtf8String(code, path);
    }

    /**
     * 生成整个模块的全部表
     *
     * @param moduleName 模块名称
     * @param force      是否强制生成（覆盖原有文件）
     */
    public void generateModuleCrud(String moduleName, int serviceType, Boolean force) {
        ResultQuery<Record> records = dslContext.resultQuery("SELECT tablename FROM pg_tables WHERE schemaname = ?;", moduleName);
        records.forEach(record -> {
            String table_name = record.getValue("tablename", String.class);
            generateTableCrud(moduleName, StrUtil.toCamelCase(table_name), serviceType, force);
        });
    }
}
