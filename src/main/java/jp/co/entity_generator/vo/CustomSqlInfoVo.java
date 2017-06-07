package jp.co.entity_generator.vo;

/**
 * カスタムSQLクラス
 */
public class CustomSqlInfoVo {

	/** コメント */
	String comment = "";

	/** メソッド名 */
	String methodName = "";

	/** パラメータ */
	String parameter = "";

	/** 戻り値 */
	String resultType = "";

	/** SQL */
	String sql = "";

	/**
	 * commentを取得します。
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * commentを設定します。
	 * @param comment comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * methodNameを取得します。
	 * @return methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * methodNameを設定します。
	 * @param methodName methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * parameterを取得します。
	 * @return parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * parameterを設定します。
	 * @param parameter parameter
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * resultTypeを取得します。
	 * @return resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * resultTypeを設定します。
	 * @param resultType resultType
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * sqlを取得します。
	 * @return sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * sqlを設定します。
	 * @param sql sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

}
