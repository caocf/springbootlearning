package com.sos.tpl.common.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.sos.tpl.common.web.filter.properties.FilterProperties;
@ConfigurationProperties(prefix="filter.XssFilter")
@Configuration
public class XssFilterSettings extends FilterProperties {

}
