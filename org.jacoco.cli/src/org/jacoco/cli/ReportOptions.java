/*******************************************************************************
 * Copyright (c) 2009, 2017 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    John Keeping - initial implementation
 *
 *******************************************************************************/
package org.jacoco.cli;

import java.io.File;

import org.kohsuke.args4j.Option;

/**
 * Options for the "report" command.
 */
public class ReportOptions {

	@Option(name = "--data", required = true, usage = "path to the execution data that was output by JaCoCo")
	private File input;

	@Option(name = "--classes", required = true, usage = "filepath (.jar or directory) containing the classes which were instrumented - NOTE: this must point at the original, non-instrumented class files")
	private File classes;

	@Option(name = "--sources", required = true, usage = "comma-separated list of directories containing the source .java files")
	private String sources;


	@Option(name = "--include", usage = "filter expression of class names to include (default: '*') "
	  + "Individual components can be separated by colons and can contain wildcards. Sample expression: "
	  + "'org.sample.*:org.example.MyCompany' will match (include) classes whose fully qualified classpaths start "
	  + "with 'org.sample.' or equal 'org.example.MyCompany'. A class will be included in the report if and only if "
	  + "it matches at least one inclusion rule and no exclusion rules")
	private String includeExpr = "*";

	@Option(name = "--exclude", usage = "filter expression of class names to exclude (default: '*'). "
	  + "Individual components can be separated by colons and can contain wildcards. Sample expression: "
	  + "'org.sample.*:org.example.MyCompany' will match (exclude) classes whose fully qualified classpaths start "
	  + "with 'org.sample.' or equal 'org.example.MyCompany'. A class will be included in the report if and only if "
	  + "it matches at least one inclusion rule and no exclusion rules")
	private String excludeExpr = "";


	@Option(name = "--title", required = true, usage = "title to give to the report")
	private String title;

	@Option(name = "--csv-out", usage = "file path of CSV report to generate")
	private File csv;

	@Option(name = "--html-out", usage = "directory in which to generate an HTML report")
	private File html;

	@Option(name = "--xml-out", usage = "file path of XML report to generate")
	private File xml;

	@Option(name = "--source-encoding", usage = "encoding of the source files (default: utf-8)")
	private String sourceEncoding = "utf-8";

	@Option(name = "--tabwidth", usage = "width of a TAB in the source files (default: 4)")
	private int tabWidth = 4;

	/**
	 * Gets the input JaCoCo execution data file.
	 * 
	 * @return the input execution data file
	 */
	public File getInput() {
		return input;
	}

	/**
	 * Gets the path to the original class files.
	 * 
	 * @return the path to the class files
	 */
	public File getClasses() {
		return classes;
	}

	/**
	 * Gets the paths to the Java sources.
	 * 
	 * @return the paths to the source directories
	 */
	public String getSources() {
		return sources;
	}

	/**
	 * Gets the title for the coverage report.
	 * 
	 * @return the report title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the path for the CSV format report, if there is one.
	 * 
	 * @return the path for the CSV report, or {@code null} to omit this format
	 */
	public File getCsv() {
		return csv;
	}

	/**
	 * Gets the path for the HTML format report, if there is one.
	 * 
	 * @return the path for the HTML report, or {@code null} to omit this format
	 */
	public File getHtml() {
		return html;
	}

	/**
	 * Gets the path for the XML format report, if there is one.
	 * 
	 * @return the path for the XML report, or {@code null} to omit this format
	 */
	public File getXml() {
		return xml;
	}

	/**
	 * Gets the encoding of the source files.
	 * 
	 * @return the source encoding
	 */
	public String getSourceEncoding() {
		return sourceEncoding;
	}

	/**
	 * Gets the width of a TAB character in the source files.
	 * 
	 * @return the tab width
	 */
	public int getTabWidth() {
		return tabWidth;
	}

	/**
	 * Gets the "include" filter expression.
	 *
	 * @return the include filter expression
	 */
	public String getIncludeExpr() {
		return includeExpr;
	}

	/**
	 * Gets the "exclude" filter expression.
	 * 
	 * @return the exclude filter expression
	 */
	public String getExcludeExpr() {
		return excludeExpr;
	}

}
