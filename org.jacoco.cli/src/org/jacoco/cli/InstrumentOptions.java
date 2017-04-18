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
 * Options for offline instrumentation task.
 */
public class InstrumentOptions {

	@Option(name = "--src", required = true, usage = "filepath to instrument. Can be a single class file, a directory, or a .jar")
	private File src;

	@Option(name = "--dest", required = true, usage = "file path at which to place instrumented class files. The value of <dest> should be a directory if <src> is a directory, otherwise it should be a file")
	private File dest;

	@Option(name = "--include", usage = "filter expression of class names to include (default: '*') "
	  + "Individual components can be separated by colons and can contain wildcards. Sample expression: "
	  + "'org.sample.*:org.example.MyCompany' will match (include) classes whose fully qualified classpaths start "
	  + "with 'org.sample.' or equal 'org.example.MyCompany'. A class will be instrumented if and only if it matches "
	  + "at least one inclusion rule and no exclusion rules")
	private String includeExpr = "*";

	@Option(name = "--exclude", usage = "filter expression of class names to exclude (default: '*') "
	  + "Individual components can be separated by colons and can contain wildcards. Sample expression: "
	  + "'org.sample.*:org.example.MyCompany' will match (exclude) classes whose fully qualified classpaths start "
	  + "with 'org.sample.' or equal 'org.example.MyCompany'. A class will be instrumented if and only if it matches "
	  + "at least one inclusion rule and no exclusion rules")
	private String excludeExpr = "";

	/**
	 * Gets the directory containing the class files to be instrumented.
	 * 
	 * @return the input class file directory
	 */
	public File getSource() {
		return src;
	}

	/**
	 * Gets the directory into which to write instrumented class files.
	 * 
	 * @return the output class file directory
	 */
	public File getDestdir() {
		return dest;
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
