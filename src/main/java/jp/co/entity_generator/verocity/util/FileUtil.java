package jp.co.entity_generator.verocity.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class FileUtil {

	/**
	 * 文字列をファイルに出力
	 *
	 * @param wr 文字列
	 * @param outputFile 出力ファイルのパス
	 *
	 */
	public static void fileWriter(String wr, File outputFile) throws IOException {

		PrintWriter pw = null;
		try {

			// ディレクトリが存在しなければ作成
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}

			// ファイル作成
			// FileWriter filewriter = new FileWriter(outputFile);
			// BufferedWriter bw = new BufferedWriter(filewriter);
			// PrintWriter pw = new PrintWriter(bw);
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8")));

			pw.print(wr);

		} finally {
			if (pw != null) pw.close();
		}
	}
}
