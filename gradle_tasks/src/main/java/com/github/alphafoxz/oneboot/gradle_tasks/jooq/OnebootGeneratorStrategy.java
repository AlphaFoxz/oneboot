package com.github.alphafoxz.oneboot.gradle_tasks.jooq;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.CatalogDefinition;
import org.jooq.meta.Definition;
import org.jooq.meta.SchemaDefinition;
import org.jooq.tools.StringUtils;

public class OnebootGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String name = getFixedJavaClassName(definition);

        if (name != null)
            return name;
        else
            return getJavaClassName0(definition, mode);
    }

    private String getJavaClassName0(Definition definition, Mode mode) {
        return getJavaClassName0(definition.getOutputName(), mode);
    }

    private String getJavaClassName0(String outputName, Mode mode) {
        StringBuilder result = new StringBuilder();

        // [#4562] Some characters should be treated like underscore
        result.append(StringUtils.toCamelCase(
                outputName.replace(' ', '_')
                        .replace('-', '_')
                        .replace('.', '_')
        ));

        if (mode == Mode.RECORD)
            result.append("Record");
        else if (mode == Mode.DAO)
            result.append("Dao");
        else if (mode == Mode.INTERFACE)
            result.insert(0, "I");
        else if (mode == Mode.POJO)
            result.append("Po");

        return result.toString();
    }

    final String getFixedJavaClassName(Definition definition) {

        // [#2032] Intercept default catalog
        if (definition instanceof CatalogDefinition && ((CatalogDefinition) definition).isDefaultCatalog())
            return "DefaultCatalog";

            // [#2089] Intercept default schema
        else if (definition instanceof SchemaDefinition && ((SchemaDefinition) definition).isDefaultSchema())
            return "DefaultSchema";

        else
            return null;
    }
}
