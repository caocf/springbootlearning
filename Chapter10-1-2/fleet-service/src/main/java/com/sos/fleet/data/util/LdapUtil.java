package com.sos.fleet.data.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Transient;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.util.StringUtils;

import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.common.domain.ldap.UserLdapDomain;
import com.sos.fleet.common.settings.SecuritySettings;

public class LdapUtil {
	
	private static Log log =  LogFactory.getLog(LdapUtil.class);
	public static ModificationItem[] buildModificationItem(final Object entity,final boolean filterEmpty) {
		final Attributes mi = new BasicAttributes();
		ReflectionUtils.doWithFields(entity.getClass(), new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				Attribute attribute = field.getAnnotation(Attribute.class);
				String name = attribute == null ? field.getName() : attribute
						.name();
				mi.put(name, field.get(entity));
			}
		}, new FieldFilter() {
			@Override
			public boolean matches(Field field) {
				Object val;
				try {
					val = field.get(entity);
				} catch (Exception e) {
					log.error(e.getMessage());
					return false;
				} 
				String valStr ;
				val = val instanceof String?val!=null?(valStr=String.valueOf(val).trim()).length()==0?null:valStr:val:val;
 				return !Modifier.isStatic(field.getModifiers())
						&& field.getAnnotation(Transient.class) == null&&(filterEmpty?val!=null:true);
			}
		});
		return buildModificationItem(mi);
	}

	/**
	 * @Author : JUNJZHU
	 * @Date : 2012-11-16
	 * @param attrs
	 * @return item
	 */
	public static ModificationItem[] buildModificationItem(Attributes attributes) {
		ModificationItem[] item = new ModificationItem[attributes.size()];
		NamingEnumeration<String> ids = attributes.getIDs();
		for (int i = 0; ids.hasMoreElements(); i++) {
			String id = ids.nextElement();
			item[i] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					attributes.get(id));
		}
		return item;
	}

	/**
	 * Mapping properties' name to attributes name.
	 * 
	 * @Author : JUNJZHU
	 * @Date : 2012-12-10
	 * @param map
	 * @return attrs
	 */
	public static Attributes property2Attribute(Attributes attrs,
			Map<String, Object> map) {
		Set<Entry<String, Object>> set = map.entrySet();
		for (Iterator<Entry<String, Object>> it = set.iterator(); it.hasNext();) {
			Entry<String, Object> en = it.next();
			String key = en.getKey();
			Object value = en.getValue();
			if (value != null && (StringUtils.hasText(value.toString()))) {
				attrs.put(key, value);
			}
		}
		return attrs;
	}
	
	public static UserLdapDomain biuldLdapUser(UserDomain user) {
		
		UserLdapDomain ldapuser = new UserLdapDomain();
		ldapuser.setCompanyName(user.getFleetDomain().getName());
		ldapuser.setMail(user.getEmail());
		ldapuser.setMobile(user.getMobile());
		ldapuser.setName(user.getUserName());
		ldapuser.setNickname(user.getUserName());
		ldapuser.setIsEnabled(StatusKeys.LDAP_ENABLED);
		ldapuser.setFirstLogin(StatusKeys.LDAP_FIRST_LOGIN);
		ldapuser.setSodwType(SecuritySettings.SODW_TYPE_USER);
		ldapuser.setUid(user.getUserName());
		ldapuser.setUserPassword(user.getUserName());
		
		return ldapuser;
	}
}
