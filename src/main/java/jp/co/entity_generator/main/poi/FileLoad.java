package jp.co.entity_generator.main.poi;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.entity_generator.verocity.SrcMaker;
import jp.co.entity_generator.verocity.util.GeneratorConst;
import jp.co.entity_generator.vo.CustomSqlInfoExtVo;
import jp.co.entity_generator.vo.EntityInfoExtVo;
import jp.co.entity_generator.vo.SheetInfoExtVo;


public class FileLoad {

	Logger logger = LogManager.getLogger(this.getClass().getName());

	private String packageName = "";

	public FileLoad() {}

	/**
	 * ディレクトリを再帰的に読む
	 * @param folderPath
	 */
	public void loadFolder(File dir) {

		File[] files = dir.listFiles();

		if (files == null) {
			return;
		}
		for (File file : files) {
			if (!file.exists()) {
				continue;
			} else if (file.isDirectory()) {
				loadFolder(file);
			} else if (file.isFile()) {
				loadFile(file);
			}
		}
	}

	/**
	 * ファイル読込
	 * @param folderPath
	 */
	private void loadFile(File file) {
		try {

			if (!file.getName().endsWith(".xlsx") || file.getName().startsWith("~")) {
				logger.info("ファイル読込対象外：" + file.getName());
				return;
			}

			logger.info("ファイル名：" + file.getName());

			// ワークブックに取り込む
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			for (int s = 0; s < wb.getNumberOfSheets(); ++s) {
				XSSFSheet sheet = wb.getSheetAt(s);

				logger.info("シート名：" + sheet.getSheetName());
				if (sheet.getSheetName().startsWith("パッケージ")) {

					// パッケージ名保管
					loadPackageSheet(sheet);
					continue;
				} else if (sheet.getSheetName().startsWith("×")) {
					logger.info("シート名：「" + sheet.getSheetName() + "」は読み飛ばし");
					continue;
				} else {

					// ソースファイル作成
					createSrcFile(sheet);
				}
			}
			wb.close();
			fis.close();

		} catch (Exception e) {
			logger.info("Exception：write(File file)");
			e.printStackTrace();
		}
	}

	/*
	 * パッケージ名を保管
	 *
	 * @param XSSFSheet sheet
	 */
	private void loadPackageSheet(XSSFSheet sheet) {

		// パッケージ名を保管 (セル(1,2)＝C2)
		packageName = getCellVal(sheet.getRow(1).getCell(2));
		logger.info("package : " + packageName);

	}

	/*
	 * ソースファイル作成
	 *
	 * @param XSSFSheet sheet
	 */
	private void createSrcFile(XSSFSheet sheet) {

		// シート情報
		SheetInfoExtVo sheetInfoVo = getSheetInfo(sheet);

		// パッケージ名
		sheetInfoVo.setPackageName(packageName);

		// エンティティ情報
		int blankRowIdx = setEntityInfoVoList(sheet, sheetInfoVo);

		logger.debug(String.valueOf(blankRowIdx + 1) + "行目以降よりカスタムＳＱＬ情報を取得");

		// カスタムSQL情報 (blankRowIdx以降の行より抽出)
		setcustomSqlInfoList(sheet, sheetInfoVo, blankRowIdx);

		// ファイル作成
		SrcMaker srcMaker = new SrcMaker();

		srcMaker.createSrc(sheetInfoVo);

	}

	/*
	 * シート情報
	 *
	 * @param XSSFSheet sheet
	 */
	private SheetInfoExtVo getSheetInfo(XSSFSheet sheet) {

		SheetInfoExtVo sheetInfoVo = new SheetInfoExtVo();

		XSSFRow row = sheet.getRow(GeneratorConst.SHEET_ROW_INDEX);

		// テーブル論理名 (A2)
		sheetInfoVo.setPsyTableName(getCellVal(row.getCell(0)));

		// テーブル物理名 (B2)
		sheetInfoVo.setLgcTableName(getCellVal(row.getCell(1)));

		// クラス名 (C2)
		sheetInfoVo.setClassName(getCellVal(row.getCell(2)));

		// select (D2)
		sheetInfoVo.setSelectFlg(StringUtils.isNotBlank(getCellVal(row.getCell(3))));

		// insert (E2)
		sheetInfoVo.setInsertFlg(StringUtils.isNotBlank(getCellVal(row.getCell(4))));

		// update (F2)
		sheetInfoVo.setUpdateFlg(StringUtils.isNotBlank(getCellVal(row.getCell(5))));

		// delete (G2)
		sheetInfoVo.setDeleteFlg(StringUtils.isNotBlank(getCellVal(row.getCell(6))));

		return sheetInfoVo;
	}

	/*
	 * エンティティ情報を設定
	 *
	 * @param XSSFSheet sheet
	 * @return int BlankRowIndex;
	 */
	private int setEntityInfoVoList(XSSFSheet sheet, SheetInfoExtVo sheetInfoVo) {

		int rowIdx = GeneratorConst.ENTITY_ROW_INDEX;
		List<EntityInfoExtVo> entityList = new ArrayList<EntityInfoExtVo>();

		if ("No.".equals(getCellVal(sheet.getRow(2).getCell(0)))) {

			while (sheet.getRow(rowIdx) != null && StringUtils.isNotBlank(getCellVal(sheet.getRow(rowIdx).getCell(0)))) {
				EntityInfoExtVo entityInfoVo = new EntityInfoExtVo();
				XSSFRow row = sheet.getRow(rowIdx);

				// カラム論理名 (B4～)
				entityInfoVo.setPsyCalName(getCellVal(row.getCell(1)));
				// カラム物理名 (C4～)
				entityInfoVo.setLgcCalName(getCellVal(row.getCell(2)));
				// 型 (D4～)
				entityInfoVo.setType(getCellVal(row.getCell(3)));
				// サイズ (E4～)
				entityInfoVo.setSize(getCellVal(row.getCell(4)));
				// 必須 (F4～)
				entityInfoVo.setRequiredFlg(StringUtils.isNotBlank(getCellVal(row.getCell(5))));
				// 初期値 (G4～)
				entityInfoVo.setDeaule(getCellVal(row.getCell(6)));
				// PK (H4～)
				entityInfoVo.setPkFlg(StringUtils.isNotBlank(getCellVal(row.getCell(7))));

				entityList.add(entityInfoVo);

				rowIdx++;
			}
		}
		sheetInfoVo.setEntityInfoList(entityList);

		return rowIdx;
	}

	/*
	 * カスタムＳＱＬ情報を設定
	 *
	 * @param XSSFSheet sheet
	 * @param SheetInfoExtVo sheetInfoVo
	 * @param int blankRowIdx
	 */
	private void setcustomSqlInfoList(XSSFSheet sheet, SheetInfoExtVo sheetInfoVo, int blankRowIdx) {
		List<CustomSqlInfoExtVo> customSqlInfoList = new ArrayList<CustomSqlInfoExtVo>();

		// blankRowIdx以降の行よりカスタムＳＱＬ情報を取得
		blankRowIdx++;

		while (sheet.getRow(blankRowIdx) != null && StringUtils.isNotBlank(getCellVal(sheet.getRow(blankRowIdx).getCell(0)))
				&& StringUtils.isNotBlank(getCellVal(sheet.getRow(blankRowIdx).getCell(1)))) {
			
			CustomSqlInfoExtVo customSqlInfoVo = new CustomSqlInfoExtVo();

			// コメント
			customSqlInfoVo.setComment(getCellVal(sheet.getRow(blankRowIdx).getCell(1)));

			// メソッド名
			blankRowIdx++;
			customSqlInfoVo.setMethodName(getCellVal(sheet.getRow(blankRowIdx).getCell(1)));

			// パラメータ
			blankRowIdx++;
			customSqlInfoVo.setParameter(getCellVal(sheet.getRow(blankRowIdx).getCell(1)));

			// 戻り値
			blankRowIdx++;
			customSqlInfoVo.setResultType(getCellVal(sheet.getRow(blankRowIdx).getCell(1)));

			// SQL
			blankRowIdx++;
			customSqlInfoVo.setSql(getCellVal(sheet.getRow(blankRowIdx).getCell(1)));

			customSqlInfoList.add(customSqlInfoVo);

			// 次行 + 空行
			blankRowIdx = blankRowIdx + 2;
		}

		sheetInfoVo.setCustomSqlInfoList(customSqlInfoList);
	}

	/**
	 * セルの値を取得する。
	 * @param cell セル
	 * @return セルの値(文字列型)
	 */
	private String getCellVal(Cell cell) {

		try {
			switch (cell.getCellType()) {

			case Cell.CELL_TYPE_STRING:

				// 文字列
				return cell.getRichStringCellValue().getString();

			case Cell.CELL_TYPE_NUMERIC:

				// 日付
				if (DateUtil.isCellDateFormatted(cell)) {
					return String.valueOf(cell.getDateCellValue());
				}

				// 数値
				return String.valueOf(cell.getNumericCellValue());

			case Cell.CELL_TYPE_FORMULA:

				// 数式
				return String.valueOf(cell.getCellFormula());

			case Cell.CELL_TYPE_BOOLEAN:

				return String.valueOf(cell.getBooleanCellValue());

			default:
				return null;
			}
		} catch (NullPointerException npe) {
			return null;
		}
	}

}
