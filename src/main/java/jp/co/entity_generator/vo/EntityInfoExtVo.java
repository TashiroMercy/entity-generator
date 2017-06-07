package jp.co.entity_generator.vo;


import jp.co.entity_generator.verocity.util.CustomStringUtil;

/**
 * エンティティ情報拡張クラス
 */
public class EntityInfoExtVo extends EntityInfoVo {

	/**
	 * カラム物理名のキャメルケース文字を返却
	 */
	public String getProperty() {
		return CustomStringUtil.snakeToCamel(lgcCalName);
	}

	/**
	 * カラム物理名のキャメルケース文字を返却(1文字目大文字)
	 */
	public String getPropertyFirstUpper() {

		String property = CustomStringUtil.snakeToCamel(lgcCalName);

		if (property != null) {

			property = property.substring(0, 1).toUpperCase() + property.substring(1);
		}
		return property;
	}

	public String getJdbcType() {

		if (this.type == null) {
			return null;
		}
		String jdbcType = type.toLowerCase();

		if (jdbcType.startsWith("char")) {
			return "CHAR";
		} else if (jdbcType.startsWith("varchar")) {
			return "VARCHAR";
		} else if (jdbcType.startsWith("nvarchar")) {
			return "NVARCHAR";
		} else if (jdbcType.startsWith("date")) {
			return "TIMESTAMP";
		} else if (jdbcType.startsWith("number")) {
			return "DECIMAL";
		} else {
			return type;
		}
	}

	public String getJavaType() {

		if (this.type == null) {
			return null;
		}
		String javaType = type.toLowerCase();

		if (javaType.startsWith("char")) {
			return "String";
		} else if (javaType.startsWith("varchar")) {
			return "String";
		} else if (javaType.startsWith("nvarchar")) {
			return "String";
		} else if (javaType.startsWith("date")) {
			return "Date";
		} else if (javaType.startsWith("number")) {
			return "BigDecimal";
		}

		return javaType;
	}

}
