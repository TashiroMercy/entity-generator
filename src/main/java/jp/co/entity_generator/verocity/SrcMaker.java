package jp.co.entity_generator.verocity;


import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import jp.co.entity_generator.verocity.util.FileUtil;
import jp.co.entity_generator.verocity.util.GeneratorConst;
import jp.co.entity_generator.vo.SheetInfoExtVo;


public class SrcMaker {

	Logger logger = LogManager.getLogger(this.getClass().getName());

	// Mapper(XML)テンプレートファイル
	private static final String TEMPLATE_XML = "jp/co/entity_generator/verocity/resource/MapperXML.vm";

	// Mapper(interface)テンプレートファイル
	private static final String TEMPLATE_INTERFACE = "jp/co/entity_generator/verocity/resource/Interface.vm";

	// Entityテンプレートファイル
	private static final String TEMPLATE_ENTITY = "jp/co/entity_generator/verocity/resource/Entity.vm";

	/** Service テンプレートファイル*/
	private static final String TEMPLATE_SERVICE = "jp/co/entity_generator/verocity/resource/Service.vm";

	public SrcMaker() {}

	/*
	 * ソースファイル作成
	 * @param SheetInfoExtVo sheetInfoVo
	 */
	public void createSrc(SheetInfoExtVo sheetInfoVo) {

		try {

			// プロパティーファイルを読み込む
			Properties p = new Properties();

			p.setProperty("resource.loader", "class");
			p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			p.setProperty("input.encoding", "UTF-8");
			Velocity.init(p);
			String basePath = getBasePath(sheetInfoVo.getPackageName());
			StringBuffer fileNm = null;

			// Mapper(Xml)作成
			fileNm = new StringBuffer(basePath);
			fileNm.append("mapper/").append(sheetInfoVo.getClassName()).append("Mapper.xml");
			create(sheetInfoVo, TEMPLATE_XML, fileNm.toString());

			// Mapper(interface)作成
			fileNm = new StringBuffer(basePath);
			fileNm.append("mapper/").append(sheetInfoVo.getClassName()).append("Mapper.java");
			create(sheetInfoVo, TEMPLATE_INTERFACE, fileNm.toString());

			// Entity作成
			fileNm = new StringBuffer(basePath);
			fileNm.append("entity/").append(sheetInfoVo.getClassName()).append(".java");
			create(sheetInfoVo, TEMPLATE_ENTITY, fileNm.toString());

			// Service 作成
			fileNm = new StringBuffer(basePath);
			fileNm.append("service/").append(sheetInfoVo.getClassName()).append("Service.java");
			create(sheetInfoVo, TEMPLATE_SERVICE, fileNm.toString());
		} catch (Exception e) {
			logger.info("Exception：SrcMaker.MakeTest(SheetInfoExtVo sheetInfoVo)");
			e.printStackTrace();
		}
	}

	/*
	 * Mapper(Xml)作成
	 * @param SheetInfoExtVo sheetInfoVo
	 */
	private void create(SheetInfoExtVo sheetInfoVo, String templateFile, String creFileName) throws Exception {

		// VelocityContextを生成
		VelocityContext vc = new VelocityContext();

		// マージ後データ格納用
		StringWriter w = new StringWriter();

		try {

			vc.put("sheetInfoVo", sheetInfoVo);

			// テンプレートファイルを読み込み
			Template template = Velocity.getTemplate(templateFile, "UTF-8");


			// テンプレートとデータをマージする
			template.merge(vc, w);

			// ファイルに出力
			File ofl = new File(creFileName);

			FileUtil.fileWriter(w.toString(), ofl);

			logger.info("ファイルに出力 : ".concat(creFileName));

		} finally {
			if( w != null ) w.close();
		}
	}

	private String getBasePath(String packageName) {

		StringBuffer basePath = new StringBuffer(GeneratorConst.OUTPUT_BASE_DIRE);
		String[] packageArray = packageName.split("\\.");

		for (String pacagePath : packageArray) {
			basePath.append(pacagePath).append("/");
		}
		return basePath.toString();
	}
}
