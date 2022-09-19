package com.zfkj.demo.common.config.reqloghandel;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-04-09 00:23
 **/
public class EnableServletRequestLogImportSelector implements ImportSelector {
    public EnableServletRequestLogImportSelector() {
    }

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{ServletRequestLogConfiguration.class.getName()};
    }
}
