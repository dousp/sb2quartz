package com.dou.sb2quartz.conf.beetl;

import com.dou.sb2quartz.core.utils.ObjectUtil;

import java.util.Properties;

/**
 *  - beetl配置(如果需要配置别的配置可参照这个形式自己添加)
 *  - 然后在 PropertiesConfig 配置类中注册
 * @author dsp
 */
public class BeetlProperties {

    public static final String BEETLCONF_PREFIX = "beetl";

    private String templatesPath;

    private String delimiterStatementStart;

    private String delimiterStatementEnd;

    private String resourceTagroot;

    private String resourceTagsuffix;

    private String resourceAutoCheck;

    public Properties getProperties() {
        Properties properties = new Properties();
        if (ObjectUtil.isNotEmpty(delimiterStatementStart)) {
            if (delimiterStatementStart.startsWith("\\")) {
                delimiterStatementStart = delimiterStatementStart.substring(1);
            }
            properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
        }
        if (ObjectUtil.isNotEmpty(delimiterStatementEnd)) {
            properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
        } else {
            properties.setProperty("DELIMITER_STATEMENT_END", "null");
        }
        if (ObjectUtil.isNotEmpty(resourceTagroot)) {
            properties.setProperty("RESOURCE.tagRoot", resourceTagroot);
        }
        if (ObjectUtil.isNotEmpty(resourceTagsuffix)) {
            properties.setProperty("RESOURCE.tagSuffix", resourceTagsuffix);
        }
        if (ObjectUtil.isNotEmpty(resourceAutoCheck)) {
            properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck);
        }
        if (ObjectUtil.isNotEmpty(resourceAutoCheck)) {
            properties.setProperty("templatesPath", templatesPath);
        }
        return properties;
    }

    public String getDelimiterStatementStart() {
        return delimiterStatementStart;
    }

    public void setDelimiterStatementStart(String delimiterStatementStart) {
        this.delimiterStatementStart = delimiterStatementStart;
    }

    public String getDelimiterStatementEnd() {
        return delimiterStatementEnd;
    }

    public void setDelimiterStatementEnd(String delimiterStatementEnd) {
        this.delimiterStatementEnd = delimiterStatementEnd;
    }

    public String getResourceTagroot() {
        return resourceTagroot;
    }

    public void setResourceTagroot(String resourceTagroot) {
        this.resourceTagroot = resourceTagroot;
    }

    public String getResourceTagsuffix() {
        return resourceTagsuffix;
    }

    public void setResourceTagsuffix(String resourceTagsuffix) {
        this.resourceTagsuffix = resourceTagsuffix;
    }

    public String getResourceAutoCheck() {
        return resourceAutoCheck;
    }

    public void setResourceAutoCheck(String resourceAutoCheck) {
        this.resourceAutoCheck = resourceAutoCheck;
    }

    public String getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(String templatesPath) {
        this.templatesPath = templatesPath;
    }
}
