package com.yunapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
public class StringUtils {
	public static JSONParser jsonParser = new JSONParser();
	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
	
	public static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
    
	public static JSONObject parseJsonObject(String s) throws ParseException {
		if(isBlank(s))
			return new JSONObject();
		return (JSONObject) jsonParser.parse(s);
	}
	
	public static String toJson(Object o) {
		String result = gson.toJson(o);
		if("null".equals(result))
			return null;
		return result;
	}
	
	public static <T> T fromJson(String s, Class<T> classOfT) {
		T result = null;
		
		if("null".equals(s) || isBlank(s))	return result;
		try {
			result = gson.fromJson(s, classOfT);
		} catch(JsonSyntaxException e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	public static Object parseStringToObject(String s) {
		Object result = null;
		if(isBlank(s))	return result;
		try {
			result = jsonParser.parse(s);
		} catch(ParseException e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	public static <T> Optional<T> oFromJson(String s, Class<T> classOfT) {
		T result = null;
		
		if("null".equals(s))	return Optional.empty();
		try {
			result = gson.fromJson(s, classOfT);
		} catch(JsonSyntaxException e) {
			log.error(e.getMessage());
		}
		return Optional.ofNullable(result);
	}
	
	public static String join(Iterator iterator, String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return ObjectUtils.toString(first);
        }

        // two or more elements
        StrBuilder buf = new StrBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }
	
	public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }
	
}
