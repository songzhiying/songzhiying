/**
 * Copyright 2016 CRAFYSMAN. All Rights Reserved.
 */
package cn.duc.global.until;

import org.apache.commons.lang.StringUtils;

public class FieldUtil {

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @param orderByStr
	 * @return
	 */
	public static final String orderByStrFormat(String orderByStr) {
		String[] orderBy = StringUtils.split(orderByStr, " ");

		if (orderBy != null) {
			for (int ii = 0; ii < orderBy.length; ii++) {
				String property = orderBy[ii];
				for (int i = 0; i < property.length(); i++) {
					char c = property.charAt(i);
					if (Character.isUpperCase(c)) {
						property = property.substring(0, i) + "_" + Character.toLowerCase(c)
								+ property.substring(i + 1, property.length());
					}
				}
				orderBy[ii] = property;

			}
			String result = "";
			for (int i = 0; i < orderBy.length; i++) {
				result += orderBy[i] + " ";
			}
			return result;
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(orderByStrFormat("projectName desc"));
	}
}
