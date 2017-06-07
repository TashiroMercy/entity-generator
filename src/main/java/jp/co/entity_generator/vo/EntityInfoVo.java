package jp.co.entity_generator.vo;

/**
 * エンティティ情報クラス
 */
public class EntityInfoVo {

	/** カラム論理名 */
	String psyCalName = "";

	/** カラム物理名 */
	String lgcCalName = "";

	/** 型 */
	String type = "";

	/** サイズ */
	String size = "";

	/** 必須 */
	boolean requiredFlg = false;

	/** 初期値 */
	String deaule = "";

	/** PK */
	boolean pkFlg = false;

	/**
	 * カラム論理名を取得します。
	 * @return カラム論理名
	 */
	public String getPsyCalName() {
		return psyCalName;
	}

	/**
	 * カラム論理名を設定します。
	 * @param psyCalName カラム論理名
	 */
	public void setPsyCalName(String psyCalName) {
		this.psyCalName = psyCalName;
	}

	/**
	 * カラム物理名を取得します。
	 * @return カラム物理名
	 */
	public String getLgcCalName() {
		return lgcCalName;
	}

	/**
	 * カラム物理名を設定します。
	 * @param lgcCalName カラム物理名
	 */
	public void setLgcCalName(String lgcCalName) {
		this.lgcCalName = lgcCalName;
	}

	/**
	 * 型を取得します。
	 * @return 型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 型を設定します。
	 * @param type 型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * サイズを取得します。
	 * @return サイズ
	 */
	public String getSize() {
		return size;
	}

	/**
	 * サイズを設定します。
	 * @param size サイズ
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 必須を取得します。
	 * @return 必須
	 */
	public boolean isRequiredFlg() {
		return requiredFlg;
	}

	/**
	 * 必須を設定します。
	 * @param requiredFlg 必須
	 */
	public void setRequiredFlg(boolean requiredFlg) {
		this.requiredFlg = requiredFlg;
	}

	/**
	 * 初期値を取得します。
	 * @return 初期値
	 */
	public String getDeaule() {
		return deaule;
	}

	/**
	 * 初期値を設定します。
	 * @param deaule 初期値
	 */
	public void setDeaule(String deaule) {
		this.deaule = deaule;
	}

	/**
	 * PKを取得します。
	 * @return PK
	 */
	public boolean isPkFlg() {
		return pkFlg;
	}

	/**
	 * PKを設定します。
	 * @param pkFlg PK
	 */
	public void setPkFlg(boolean pkFlg) {
		this.pkFlg = pkFlg;
	}

}
