package jp.co.entity_generator.verocity.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomStringUtil {

	/**
	 * スネークケース表記をローワーキャメルケース表記へ
	 *
	 * @param wr 文字列
	 * @param outputFile 出力ファイルのパス
	 *
	 */
	public static String snakeToCamel(String targetStr) {
		Pattern p = Pattern.compile("_([a-z])");
		Matcher m = p.matcher(targetStr.toLowerCase());

		StringBuffer sb = new StringBuffer(targetStr.length());

		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
