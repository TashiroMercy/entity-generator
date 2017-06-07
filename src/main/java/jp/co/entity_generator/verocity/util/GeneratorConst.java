package jp.co.entity_generator.verocity.util;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class GeneratorConst {

	// シート情報行のインデックス番号
	public static final int SHEET_ROW_INDEX = 1;

	// エンティティ情報行(スタート行)のインデックス番号
	public static final int ENTITY_ROW_INDEX = 3;

	// 出力ディレクトリ
	public static final String OUTPUT_BASE_DIRE = "out/";

	// 未実装  import用マップ
	public static final Map<String, String> IMPORT_REPLACE_MAP;
	static {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("List", "java.util.List");
		map.put("BigDecimal", "java.math.BigDecimal");
		map.put("Date", "java.util.Date");
		map.put("Map", "java.util.Map");
		IMPORT_REPLACE_MAP = Collections.unmodifiableMap(map);
	}
}
