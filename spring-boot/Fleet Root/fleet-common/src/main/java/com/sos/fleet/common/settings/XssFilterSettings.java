package com.sos.fleet.common.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.sos.fleet.common.filter.properties.FilterProperties;
@ConfigurationProperties(prefix="filter.XssFilter")
@Configuration
public class XssFilterSettings extends FilterProperties {

}
