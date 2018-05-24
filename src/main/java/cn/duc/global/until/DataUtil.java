package cn.duc.global.until;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public final class DataUtil {

	private DataUtil() {
	}

	/**
	 * 十进制字节数组转十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static final String byte2hex(byte[] b) { // 一个字节数，转成16进制字符串
		StringBuilder hs = new StringBuilder(b.length * 2);
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString(); // 转成大写
	}

	/**
	 * 十六进制字符串转十进制字节数组
	 * 
	 * @param b
	 * @return
	 */
	public static final byte[] hex2byte(String hs) {
		byte[] b = hs.getBytes();
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个十进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 这个方法可以通过与某个类的class文件的相对路径来获取文件或目录的绝对路径。 通常在程序中很难定位某个相对路径，特别是在B/S应用中。
	 * 通过这个方法，我们可以根据我们程序自身的类文件的位置来定位某个相对路径。
	 * 比如：某个txt文件相对于程序的Test类文件的路径是../../resource/test.txt，
	 * 那么使用本方法Path.getFullPathRelateClass("../../resource/test.txt",Test.class)
	 * 得到的结果是txt文件的在系统中的绝对路径。
	 * 
	 * @param relatedPath
	 *        相对路径
	 * @param cls
	 *        用来定位的类
	 * @return 相对路径所对应的绝对路径
	 * @throws IOException
	 *         因为本方法将查询文件系统，所以可能抛出IO异常
	 */
	public static final String getFullPathRelateClass(String relatedPath, Class<?> cls) {
		String path = null;
		if (relatedPath == null) {
			throw new NullPointerException();
		}
		String clsPath = getPathFromClass(cls);
		File clsFile = new File(clsPath);
		String tempPath = clsFile.getParent() + File.separator + relatedPath;
		File file = new File(tempPath);
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 获取class文件所在绝对路径
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 */
	public static final String getPathFromClass(Class<?> cls) {
		String path = null;
		if (cls == null) {
			throw new NullPointerException();
		}
		URL url = getClassLocationURL(cls);
		if (url != null) {
			path = url.getPath();
			if ("jar".equalsIgnoreCase(url.getProtocol())) {
				try {
					path = new URL(path).getPath();
				} catch (MalformedURLException e) {
				}
				int location = path.indexOf("!/");
				if (location != -1) {
					path = path.substring(0, location);
				}
			}
			File file = new File(path);
			try {
				path = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 判断对象是否Empty(null或元素为0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类<br>
	 * DataUtil.isEmpty(null) = true <br>
	 * DataUtil.isEmpty("") = true <br>
	 * DataUtil.isEmpty(" ") = true <br>
	 * 
	 * @param pObj
	 *        待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static final boolean isEmpty(Object pObj) {
		if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).trim().length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection<?>) {
			if (((Collection<?>) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map<?, ?>) {
			if (((Map<?, ?>) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * JS输出含有\n的特殊处理
	 * 
	 * @param pStr
	 * @return
	 */
	public static final String replace4JsOutput(String pStr) {
		pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
		pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		pStr = pStr.replace(" ", "&nbsp;");
		return pStr;
	}

	/**
	 * 分别去空格
	 * 
	 * @param paramArray
	 * @return
	 */
	public static final String[] trim(String[] paramArray) {
		if (ArrayUtils.isEmpty(paramArray)) {
			return paramArray;
		}
		String[] resultArray = new String[paramArray.length];
		for (int i = 0; i < paramArray.length; i++) {
			String param = paramArray[i];
			resultArray[i] = StringUtils.trim(param);
		}
		return resultArray;
	}

	/**
	 * 获取类的class文件位置的URL
	 * 
	 * @param cls
	 * @return
	 */
	private static URL getClassLocationURL(final Class<?> cls) {
		if (cls == null)
			throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			if (cs != null)
				result = cs.getLocation();
			if (result != null) {
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar")
								|| result.toExternalForm().endsWith(".zip"))
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/")
									.concat(clsAsResource));
						else if (new File(result.getFile()).isDirectory())
							result = new URL(result, clsAsResource);
					} catch (MalformedURLException ignore) {
					}
				}
			}
		}
		if (result == null) {
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource)
					: ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}

	/** 初始化设置默认值 */
	public static final <K> K ifNull(K k, K defaultValue) {
		if (k == null) {
			return defaultValue;
		}
		return k;
	}

	public static void removeMapNullValue(Map<String, Object> params, String key) {
		if (!params.containsKey(key)) {
			return;
		}
		String value = valueOfString(params.remove(key));
		if (!StringUtils.isEmpty(value)) {
			params.put(key, value);
		}
	}

	public static String valueOfString(Object obj) {
		return obj == null ? null : String.valueOf(obj);
	}

	public static String MD5(String str) {
		return DigestUtils.md5Hex(str);
	}

	public static String MD5(byte[] bytes) {
		return DigestUtils.md5Hex(bytes);
	}

	public static boolean stringToBoolean(String str) {
		if (str == "true") {
			return true;
		}
		if (str == null) {
			return false;
		}
		if (str.length() == 1) {
			if (StringUtils.equals(str, "0")) {
				return false;
			} else if (StringUtils.equals(str, "1")) {
				return true;
			} else if (StringUtils.equals(str.toLowerCase(), "t")) {
				return true;
			} else if (StringUtils.equals(str.toLowerCase(), "f")) {
				return false;
			} else if (StringUtils.equals(str.toLowerCase(), "n")) {
				return false;
			}
		}
		return BooleanUtils.toBoolean(str);
	}

	public static String stringSort(String str) {
		str = StringUtils.trim(str);
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		String[] split = StringUtils.split(str, ",");
		Arrays.sort(split);
		String string = ArrayUtils.toString(split);
		return StringUtils.substring(string, 1, string.length() - 1);
	}

	public static Set<String> stringAsSet(String str, String... separatorChars) {
		String separator = ",";
		if (!ArrayUtils.isEmpty(separatorChars)) {
			separator = separatorChars[0];
		}
		String[] split = StringUtils.split(str, separator);
		if (!ArrayUtils.isEmpty(split)) {
			HashSet<String> hashSet = new HashSet<>();
			hashSet.addAll(Arrays.asList(split));
			return hashSet;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Object[] arrayDifference(Object[] arr1, Object[] arr2) {
		if (ArrayUtils.isEmpty(arr1)) {
			return null;
		}
		if (ArrayUtils.isEmpty(arr2)) {
			return arr1;
		}
		Set<Object> set1 = new HashSet<>();
		set1.addAll(Arrays.asList(arr1));
		set1.remove(null);
		if (set1.isEmpty()) {
			return null;
		}
		Set<Object> set2 = new HashSet<>();
		set2.addAll(Arrays.asList(arr2));
		set2.remove(null);
		if (set2.isEmpty()) {
			return arr1;
		}
		List<Object> removeAll = ListUtils.removeAll(set1, set2);
		if (removeAll.isEmpty()) {
			return null;
		}
		return removeAll.toArray((Object[]) Array.newInstance(removeAll.get(0).getClass(), 0));
	}

	public static String snakeCase(String str) {
		String snakeCaseStr = ((PropertyNamingStrategy.SnakeCaseStrategy) PropertyNamingStrategy.SNAKE_CASE)
				.translate(str);
		return StringUtils.isEmpty(snakeCaseStr) ? snakeCaseStr : snakeCaseStr.toUpperCase();
	}

	public static String camelCase(String str) {
		return JavaBeansUtil.getCamelCaseString(str, false);
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		if (StringUtils.isEmpty(phoneNumber)) {
			return false;
		}
		return phoneNumber.matches("(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}");
	}
}