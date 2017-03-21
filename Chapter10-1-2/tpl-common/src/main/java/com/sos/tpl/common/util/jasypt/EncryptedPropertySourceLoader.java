package com.sos.tpl.common.util.jasypt;

import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


/**
 * This class is a replacement for the default Spring PropertySourceLoader. It has the capability of detecting
 * and decrypting encrypted properties via Jasypt Encryption Library.
 * The decryption password must be provided via an environment variable or via a System property. The name of the property can be {@code PROPERTY_ENCRYPTION_PASSWORD} or {@code property.encryption.password}.
 * For more information see http://www.jasypt.org/ and http://www.jasypt.org/spring31.html
 * For Spring Boot integration the default {@link PropertySourceLoader} configuration was overriden by
 * META-INF/spring.factories file.
 *
 * @see org.springframework.boot.env.PropertySourceLoader
 */

public class EncryptedPropertySourceLoader implements PropertySourceLoader, PriorityOrdered {
   /* private static final String ENCRYPTION_PASSWORD_ENVIRONMENT_VAR_NAME_UNDERSCORE = "PROPERTY_ENCRYPTION_PASSWORD";
    private static final String ENCRYPTION_PASSWORD_ENVIRONMENT_VAR_NAME_DOT = "property.encryption.password";
    private static final String ENCRYPTION_PASSWORD_NOT_SET = "ENCRYPTION_PASSWORD_NOT_SET";*/
     static final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
     /*    public EncryptedPropertySourceLoader() {
       encryptor.setPassword(getPasswordFromEnvAndSystemProperties());
    }
*/
   /* private String getPasswordFromEnvAndSystemProperties() {
        String password = System.getenv(ENCRYPTION_PASSWORD_ENVIRONMENT_VAR_NAME_UNDERSCORE);
        if (password == null) {
            password = System.getenv(ENCRYPTION_PASSWORD_ENVIRONMENT_VAR_NAME_DOT);
            if (password == null) {
                password = System.getProperty(ENCRYPTION_PASSWORD_ENVIRONMENT_VAR_NAME_UNDERSCORE);
                if (password == null) {
                    password = System.getProperty(ENCRYPTION_PASSWORD_ENVIRONMENT_VAR_NAME_DOT);
                    if (password == null) {
                        password = ENCRYPTION_PASSWORD_NOT_SET;
                    }
                }
            }
        }
        return password;
    }*/

    @Override
    public String[] getFileExtensions() {
    	return new String[] { "properties", "xml" };
    }

    @Override
    public PropertySource<?> load(final String name, final Resource resource, final String profile) throws
            IOException {
        if (profile == null) {
            //load the properties
            final Properties props = PropertiesLoaderUtils.loadProperties(resource);
            if (!props.isEmpty()) {
                //create the encryptable properties property source
                return new EncryptablePropertiesPropertySource(name, props, encryptor);
            }
        }

        return null;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
    /*
     * =============================================================================
     * 
     *   Copyright (c) 2007-2010, The JASYPT team (http://www.jasypt.org)
     * 
     *   Licensed under the Apache License, Version 2.0 (the "License");
     *   you may not use this file except in compliance with the License.
     *   You may obtain a copy of the License at
     * 
     *       http://www.apache.org/licenses/LICENSE-2.0
     * 
     *   Unless required by applicable law or agreed to in writing, software
     *   distributed under the License is distributed on an "AS IS" BASIS,
     *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     *   See the License for the specific language governing permissions and
     *   limitations under the License.
     * 
     * =============================================================================
     */

    /**
     * 
     * @since 1.9.0
     * 
     * @author Daniel Fern&aacute;ndez
     * 
     */
    public  static class EncryptablePropertiesPropertySource 
            extends PropertiesPropertySource {
    	private static final String KEY = "jasypt.key";
        private static final String ALGORITHM = "jasypt.algorithm";

        public EncryptablePropertiesPropertySource(final String name, final EncryptableProperties props) {
            super(name, props);
        }

        public EncryptablePropertiesPropertySource(final String name, final Properties props, final TextEncryptor encryptor) {
            super(name, processProperties(props, encryptor));
        }

        public EncryptablePropertiesPropertySource(final String name, final Properties props, final StringEncryptor encryptor) {
            super(name, processProperties(props, encryptor));
        }

        
        private static Properties processProperties(final Properties props, final TextEncryptor encryptor) {
            if (props == null) {
                return null;
            }
            if (props instanceof EncryptableProperties) {
                throw new IllegalArgumentException(
                        "Properties object already is an " + EncryptableProperties.class.getName() + 
                        " object. No encryptor should be specified.");
            }
            final EncryptableProperties encryptableProperties = new EncryptableProperties(encryptor);
            encryptableProperties.putAll(props);
            return encryptableProperties;
        }

        
        private static Properties processProperties(final Properties props, final StringEncryptor encryptor) {
            if (props == null) {
                return null;
            }
            if(encryptor instanceof StandardPBEStringEncryptor){
            	StandardPBEStringEncryptor sp = (StandardPBEStringEncryptor)encryptor;
            	if(props.containsKey(ALGORITHM)){
            		sp.setAlgorithm(props.getProperty(ALGORITHM));
            	}
            	if(props.containsKey(KEY)){
            		sp.setPassword(props.getProperty(KEY));
            	}
            }
            if (props instanceof EncryptableProperties) {
                throw new IllegalArgumentException(
                        "Properties object already is an " + EncryptableProperties.class.getName() + 
                        " object. No encryptor should be specified.");
            }
            final EncryptableProperties encryptableProperties = new EncryptableProperties(encryptor);
            encryptableProperties.putAll(props);
            return encryptableProperties;
        }
        
    }

}