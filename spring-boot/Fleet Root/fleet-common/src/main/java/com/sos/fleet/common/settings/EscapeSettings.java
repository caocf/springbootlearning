package com.sos.fleet.common.settings;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sql.escape")
public class EscapeSettings {
	private static EscapeSettings ESCAPE_SETTINGS;
	private char character;
	private String filterChars;
	private char[] splitChars;
	private static final  String splitSpe = ",";
	private static final  String FMT_STARTS_WITH = "%s%%";
	private static final String FMT_ENDS_WITH = "%%%s";
	private static final String FMT_ANY_WHERE = "%%%s%%";
	@PostConstruct
	private void init() {
		String[] chars = getChars();
		splitChars = new char[chars[chars.length - 1].charAt(0) + 1];
		for (int i = 0; i < chars.length; i++) {
			splitChars[chars[i].charAt(0)] = character;
		}
		ESCAPE_SETTINGS = this;
	}

	public static EscapeSettings instance() {
		return ESCAPE_SETTINGS;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public String getFilterChars() {
		return filterChars;
	}

	private String[] getChars() {
		if (!filterChars.contains(String.valueOf(character))) {
			filterChars = filterChars.concat(splitSpe+character);
		}
		String[] chars = filterChars.split(splitSpe);
		StringBuilder sb = new StringBuilder(0);
		for (int i = 0; i < chars.length; i++) {
			String chs = chars[i];
			if(chs.trim().length()!=1){
				continue;
			}
			if(sb.length()!=0){
				sb.append(splitSpe);
			}
			sb.append(chs.trim());
		}
		chars = sb.toString().split(splitSpe);
		Arrays.sort(chars);
		return chars;
	}

	public static boolean isEscapeChar(char ch) {
		return ch < ESCAPE_SETTINGS.splitChars.length
				&& ESCAPE_SETTINGS.splitChars[ch] == ESCAPE_SETTINGS.character;
	}
	
	public static String getEscapeChars(char ch) {
		if (isEscapeChar(ch)) {
			return String.valueOf(new char[] { ESCAPE_SETTINGS.splitChars[ch],
					ch });
		}
		return String.valueOf(ch);
	}

	public static String getEscapeChars(String text) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			sb.append(isEscapeChar(ch) ? getEscapeChars(ch) : ch);
		}
		return sb.toString();
	}

	public void setFilterChars(String filterChars) {
		this.filterChars = filterChars;
	}
	public static char getEscapeChar(){
		return ESCAPE_SETTINGS.character;
	}
	public static String likeForStartsWith(String value){
		return String.format(FMT_STARTS_WITH, getEscapeChars(value));
	}
	public static String likeForEndsWith(String value){
		return String.format(FMT_ENDS_WITH, getEscapeChars(value));
	}
	public static String likeForAnyWhere(String value){
		return String.format(FMT_ANY_WHERE, getEscapeChars(value));
	}
}
