package com.sos.portal.scheduler.mail;



import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author eric.wang
 */
public class FreemarkConfigFactory {
	static Log log=LogFactory.getLog(FreemarkConfigFactory.class);
	private static Configuration freemarker_cfg ;
	
	/**
	 * 
	 * @Author      :      weichu wang
	 * @Date        :      2013-1-9
	 * @return
	 */
	public static Configuration getFreemarkerConfiguration(){
		if(freemarker_cfg==null){
			freemarker_cfg=new Configuration();
			freemarker_cfg.setObjectWrapper(new DefaultObjectWrapper());
			freemarker_cfg.setEncoding(Locale.getDefault(), "utf-8");
			// search and load freemarker template.
			freemarker_cfg.setClassForTemplateLoading(FreemarkConfigFactory.class,"/");
		}
		return freemarker_cfg;
	}
	
	/**
	 * parse template to string
	 * @Author      :      weichu wang
	 * @Date        :      2013-1-9
	 * @param templateFile
	 * @param language
	 * @param param
	 * @return
	 */
	public static String  parseTemplate(String templateFile, String language, Map param) {
        String parsedContent = null;
        if (StringUtils.isNotEmpty(templateFile)) {
            Configuration freemarker_cfg = FreemarkConfigFactory.getFreemarkerConfiguration();
            Template template = null;
            try {
                template =  freemarker_cfg.getTemplate(templateFile, new Locale(language), "UTF-8");
                parsedContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, param);
            } catch (IOException e) {
                log.error(ExceptionUtils.getMessage(e));
            } catch (TemplateException e) {
                log.error(ExceptionUtils.getMessage(e));
            }
        }
        return parsedContent != null ? parsedContent : "";
    }
	
	
}
