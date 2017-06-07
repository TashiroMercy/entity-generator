package jp.co.entity_generator.vo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * カスタムSQL拡張クラス
 */
public class CustomSqlInfoExtVo extends CustomSqlInfoVo {

	/**
	 * パラメータリストを返却
	 *  パラメータをカンマ区切りで分割
	 *  空白で分割してトリム
	 *  Type(型)、Name(名称)に分けてマップに保管
	 */
	public List<Map<String, String>> getParmeterList() {
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

		if (StringUtils.isBlank(parameter)) {
			return paramList;
		}
		// パラメータ（カンマ区切り）をカンマで分解
		List<String> paramArray = Arrays.asList(parameter.split(","));

		// パターン（1つ以上の空白）
		Pattern p = Pattern.compile("[\\s]+");

		for (String param : paramArray) {
			HashMap<String, String> typeMap = new HashMap<String, String>();
			String[] params = p.split(param.trim());

			typeMap.put("Type", params[0]);
			typeMap.put("Name", params[1]);
			paramList.add(typeMap);
		}

		return paramList;
	}

	/**
	 * Select,Update,Insert,Deleteを返却
	 */
	public String getSqlType() {

		String sqlBck = sql.trim().toLowerCase();

		if (sqlBck.startsWith("insert")) {
			return "insert";
		} else if (sqlBck.startsWith("update")) {
			return "update";
		} else if (sqlBck.startsWith("delete")) {
			return "delete";
		} else {
			return "select";
		}
	}

	/**
	 * Select分のResultオプションを返却
	 *  例 resultMap="ResultMap",resultType=int
	 */
	public String getResultOption(String entityName) {

		String resType = resultType.trim();

		// List<エンティティ名> 、エンティティ名の場合
		if (resultType.contains(entityName)) {
			return "resultMap=\"ResultMap\"";
		}

		// List<String> → String
		if (resType.contains("List")) {
			resType = resType.replaceFirst("List<", "").replaceFirst(">$", "");
		}
		// Map<String, Object> → map
		if (resType.toLowerCase().contains("map")) {
			resType = "map";
		}

		return "resultType=\"" + resType + "\"";
	}

	/**
	 * 成型したSQLを返却
	 *  改行後、にインデントを設定
	 */
	public String getCastSql() {

		if (StringUtils.isBlank(sql)) return sql;

		String castSql = sql.replaceAll("\\n", "\n\t\t");

		// 1行目のインデント + 改行を（改行＋インデント）に変換したＳＱＬ
		return "\t\t".concat(castSql);
	}
}
