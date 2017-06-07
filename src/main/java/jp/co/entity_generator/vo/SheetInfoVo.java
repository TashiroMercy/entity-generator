package jp.co.entity_generator.vo;


import java.util.List;

/**
 * シート情報クラス
 */
public class SheetInfoVo {

	/** パッケージ名 */
	String packageName = "";

	/** テーブル論理名 */
	String psyTableName = "";

	/** テーブル物理名 */
	String lgcTableName = "";

	/** クラス名 */
	String className = "";

	/** select */
	boolean selectFlg = false;

	/** insert */
	boolean insertFlg = false;

	/** update */
	boolean updateFlg = false;

	/** delete */
	boolean deleteFlg = false;

	/** entityList */
	List<EntityInfoExtVo> entityInfoList = null;

	/** customSqlInfoList */
	List<CustomSqlInfoExtVo> customSqlInfoList = null;

	/**
	 * パッケージ名を取得します。
	 * @return パッケージ名
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * パッケージ名を設定します。
	 * @param packageName パッケージ名
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * テーブル論理名を取得します。
	 * @return テーブル論理名
	 */
	public String getPsyTableName() {
		return psyTableName;
	}

	/**
	 * テーブル論理名を設定します。
	 * @param psyTableName テーブル論理名
	 */
	public void setPsyTableName(String psyTableName) {
		this.psyTableName = psyTableName;
	}

	/**
	 * テーブル物理名を取得します。
	 * @return テーブル物理名
	 */
	public String getLgcTableName() {
		return lgcTableName;
	}

	/**
	 * テーブル物理名を設定します。
	 * @param lgcTableName テーブル物理名
	 */
	public void setLgcTableName(String lgcTableName) {
		this.lgcTableName = lgcTableName;
	}

	/**
	 * クラス名を取得します。
	 * @return クラス名
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * クラス名を設定します。
	 * @param className クラス名
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * selectを取得します。
	 * @return select
	 */
	public boolean isSelectFlg() {
		return selectFlg;
	}

	/**
	 * selectを設定します。
	 * @param selectFlg select
	 */
	public void setSelectFlg(boolean selectFlg) {
		this.selectFlg = selectFlg;
	}

	/**
	 * insertを取得します。
	 * @return insert
	 */
	public boolean isInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertを設定します。
	 * @param insertFlg insert
	 */
	public void setInsertFlg(boolean insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * updateを取得します。
	 * @return update
	 */
	public boolean isUpdateFlg() {
		return updateFlg;
	}

	/**
	 * updateを設定します。
	 * @param updateFlg update
	 */
	public void setUpdateFlg(boolean updateFlg) {
		this.updateFlg = updateFlg;
	}

	/**
	 * deleteを取得します。
	 * @return delete
	 */
	public boolean isDeleteFlg() {
		return deleteFlg;
	}

	/**
	 * deleteを設定します。
	 * @param deleteFlg delete
	 */
	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * entityListを取得します。
	 * @return entityList
	 */
	public List<EntityInfoExtVo> getEntityInfoList() {
		return entityInfoList;
	}

	/**
	 * entityListを設定します。
	 * @param entityInfoList entityList
	 */
	public void setEntityInfoList(List<EntityInfoExtVo> entityInfoList) {
		this.entityInfoList = entityInfoList;
	}

	/**
	 * customSqlInfoListを取得します。
	 * @return customSqlInfoList
	 */
	public List<CustomSqlInfoExtVo> getCustomSqlInfoList() {
		return customSqlInfoList;
	}

	/**
	 * customSqlInfoListを設定します。
	 * @param customSqlInfoList customSqlInfoList
	 */
	public void setCustomSqlInfoList(List<CustomSqlInfoExtVo> customSqlInfoList) {
		this.customSqlInfoList = customSqlInfoList;
	}

}
