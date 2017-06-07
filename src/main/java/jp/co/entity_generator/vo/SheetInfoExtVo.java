package jp.co.entity_generator.vo;


import java.util.Map;

import org.apache.commons.lang.StringUtils;

import jp.co.entity_generator.verocity.util.GeneratorConst;

/**
 * シート情報拡張クラス
 */
public class SheetInfoExtVo extends SheetInfoVo {

	/**
	 * マッパーファイル名
	 *  className末尾にMapperを付与
	 */
	public String getMapperFileName() {
		return className.concat("Mapper");
	}

	/**
	 * マッパー変数名
	 *  className末尾にMapperを付与
	 */
	public String getMapperVariableName() {

		return className.toLowerCase().concat("Mapper");
	}

	/**
	 *  サービスファイル名
	 *  className末尾にServiceを付与
	 */
	public String getServiceFileName() {

		return className + "Service";
	}




	/*
	 * Mapperインターフェースのインポートを取得
	 *   戻り値、パラメータから取得
	 */
	public String getInterfaceImport() {

		StringBuffer importValue = new StringBuffer();

		for (String key : GeneratorConst.IMPORT_REPLACE_MAP.keySet()) {
			boolean addflg = false;

			for (CustomSqlInfoExtVo customSql : this.customSqlInfoList) {
				if (StringUtils.isNotBlank(customSql.getResultType()) && customSql.getResultType().contains(key)) {
					addflg = true;
					break;
				}

				if (!addflg && StringUtils.isNotBlank(customSql.getParameter())
						) {
					for (Map<String, String> param : customSql.getParmeterList()) {
						String type = param.get("Type");

						if (StringUtils.isNotBlank(type) && type.contains(key)) {
							addflg = true;
							break;
						}
					}
				}
			}
			if (addflg) {
				importValue.append("import ").append(GeneratorConst.IMPORT_REPLACE_MAP.get(key)).append(";\r\n");
			}
		}
		return importValue.toString();
	}

	/*
	 * Entityクラスのインポートを取得
	 *   Entityのカラムから取得
	 */
	public String getEntityImport() {

		StringBuffer importValue = new StringBuffer();

		for (String key : GeneratorConst.IMPORT_REPLACE_MAP.keySet()) {

			boolean addflg = false;

			for (EntityInfoExtVo entVo : this.entityInfoList) {
				String javaType = entVo.getJavaType();

				if (StringUtils.isBlank(javaType)) {
					continue;
				}

				if (javaType.contains(key)) {
					addflg = true;
					break;
				}
			}
			if (addflg) {
				importValue.append("import ").append(GeneratorConst.IMPORT_REPLACE_MAP.get(key)).append(";\r\n");
			}
		}
		return importValue.toString();
	}
}
