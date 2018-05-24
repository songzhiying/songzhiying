package cn.duc.global.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class BaseClassNamePlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeTableName(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeTableName(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		makeTableName(topLevelClass, introspectedTable);
		return true;
	}

	protected void makeTableName(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addAnnotation("@BaseClassName(\"" + topLevelClass.getType().getShortName() + "\")");
		topLevelClass.addImportedType("cn.duc.global.base.BaseClassName");

	}
}
