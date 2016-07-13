package com.magus.isis.common.utils.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述: <br>
 * 创建时间: 2016年3月23日 下午4:06:18 <br>
 * 
 * @author 仲李
 * @version
 */
public class DateFormat {

	private static final Logger Logger = LoggerFactory.getLogger(DateFormat.class);

	private static final String DateFormatPattern = "yyyy-MM-dd";

	private static final String DefaultDateFormatPattern = "yyyy-MM-dd HH:mm:ss";

	private static final String DateFormatPatternStyle1 = "yyyyMMddHHmmss";

	static final String DateFormatPatternStyle2 = "yyyyMMddHHmmssSSS";

	private DateFormat() {

	}

	public static final Date parseDate(String str) {
		if (StringUtils.isBlank(str))
			return null;
		try {
			return new SimpleDateFormat(DateFormatPattern).parse(str);
		} catch (ParseException e) {
			Logger.error("DateFormat异常", e);
		}
		return null;
	}

	public static final Date parseDefaultDate(String str) {
		if (StringUtils.isBlank(str))
			return null;
		try {
			return new SimpleDateFormat(DefaultDateFormatPattern).parse(str);
		} catch (ParseException e) {
			Logger.error("DateFormat异常", e);
		}
		return null;
	}

	public static final Date parseDateStyle1(String str) {
		if (StringUtils.isBlank(str))
			return null;
		try {
			return new SimpleDateFormat(DateFormatPatternStyle1).parse(str);
		} catch (ParseException e) {
			Logger.error("DateFormat异常", e);
		}
		return null;
	}

	/**
	 * 自定义date文本输出格式 方法描述: <br>
	 * 创建时间: 2016年5月4日 上午9:50:43 <br>
	 * 
	 * @param date
	 * @param format
	 * @return 返回值：String <br>
	 *
	 * @author 仲李
	 * @version
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		return dateStr;
	}
}
