package cn.duc.global.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MapperPlugin extends PluginAdapter {

	private static final String DEFAULT_DAO_SUPER_CLASS = "com.xsj.marketing.generator.base.BaseMapper";

	private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "com.xsj.marketing.generator.base.BaseExpandMapper";

	private String daoTargetDir;

	private String daoTargetPackage;

	private String daoSuperClass;

	// 扩展
	private String expandDaoTargetPackage;

	private String expandDaoSuperClass;

	private ShellCallback shellCallback = null;

	public MapperPlugin() {
		shellCallback = new DefaultShellCallback(false);
	}

	public boolean validate(List<String> warnings) {
		daoTargetDir = properties.getProperty("targetProject");
		boolean valid = stringHasValue(daoTargetDir);

		daoTargetPackage = properties.getProperty("targetPackage");
		boolean valid2 = stringHasValue(daoTargetPackage);

		daoSuperClass = properties.getProperty("daoSuperClass");
		if (!stringHasValue(daoSuperClass)) {
			daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
		}

		expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
		expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
		if (!stringHasValue(expandDaoSuperClass)) {
			expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
		}
		return valid && valid2;
	}

	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		JavaFormatter javaFormatter = context.getJavaFormatter();
		List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
		FullyQualifiedJavaType argumentType = null;
		for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
			CompilationUnit unit = javaFile.getCompilationUnit();
			FullyQualifiedJavaType baseModelJavaType = unit.getType();
			if (argumentType == null) {
				argumentType = baseModelJavaType;
			}
			String shortName = baseModelJavaType.getShortName();

			GeneratedJavaFile mapperJavafile = null;
			if (shortName.endsWith("Mapper")) { // 扩展Mapper
				if (stringHasValue(expandDaoTargetPackage)) {
					Interface mapperInterface = new Interface(
							expandDaoTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
					mapperInterface.setVisibility(JavaVisibility.PUBLIC);
					mapperInterface.addJavaDocLine("/**");
					mapperInterface.addJavaDocLine(" * " + shortName + "Expand");
					mapperInterface.addJavaDocLine(" */");

					FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
					daoSuperType.addTypeArgument(argumentType);
					// mapperInterface.addImportedType(daoSuperType);
					mapperInterface.addSuperInterface(daoSuperType);

					mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
					try {
						File mapperDir = shellCallback.getDirectory(daoTargetDir, expandDaoTargetPackage);
						File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
						// 文件不存在
						if (!mapperFile.exists()) {
							mapperJavaFiles.add(mapperJavafile);
						}
					} catch (ShellException e) {
						e.printStackTrace();
					}
				}
				argumentType = null;
			} else if (!shortName.endsWith("Example")) { // CRUD Mapper
				Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");

				mapperInterface.setVisibility(JavaVisibility.PUBLIC);
				mapperInterface.addJavaDocLine("/**");
				mapperInterface.addJavaDocLine(" * create by MyBatis Generator, please don't modify");
				mapperInterface.addJavaDocLine(" */");

				FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
				// 添加泛型支持
				daoSuperType.addTypeArgument(argumentType);
				// mapperInterface.addImportedType(baseModelJavaType);
				// mapperInterface.addImportedType(daoSuperType);
				mapperInterface.addSuperInterface(daoSuperType);

				mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
				mapperJavaFiles.add(mapperJavafile);
			}
		}
		return mapperJavaFiles;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

		XmlElement parentElement = document.getRootElement();
		XmlElement answer = new XmlElement("sql");

		answer.addAttribute(new Attribute("id", introspectedTable.getBaseColumnListId() + "2"));

		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		Iterator<IntrospectedColumn> iter = introspectedTable.getNonBLOBColumns().iterator();
		while (iter.hasNext()) {
			sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + "."
					+ MyBatis3FormattingUtilities.getSelectListPhrase(iter.next()));

			if (iter.hasNext()) {
				sb.append(", ");
			}

			if (sb.length() > 80) {
				answer.addElement(new TextElement(sb.toString()));
				sb.setLength(0);
			}
		}

		if (sb.length() > 0) {
			answer.addElement(new TextElement(sb.toString()));
		}
		parentElement.addElement(answer);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
}
