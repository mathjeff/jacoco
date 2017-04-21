/*******************************************************************************
 * Copyright (c) 2009, 2017 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jeff Gaston - initial API and implementation
 *    
 *******************************************************************************/
package org.jacoco.cli.test.util;

import java.lang.InterruptedException;
import java.lang.Process;
import java.lang.Runtime;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;

import org.jacoco.cli.test.util.PathUtils;

import org.junit.Assert;

/**
 * Calls the command-line interface, for usage in a test
 */
public class CLIRunner {

	/**
	 * Runs the command-line interface with the given shell argument string
	 */
	public static String run(String arguments) {
		// run the command
		File runnerFile = PathUtils.getRunnerFile();
		String command = "java -jar " + runnerFile.getPath();
		if (arguments.length() > 0) {
			command += " " + arguments;
		}
		// read the output
		StringBuilder outputBuilder = new StringBuilder();
		StringBuilder errorBuilder = new StringBuilder();
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			boolean read = true;
			while (read) {
				read = false;
				String line;
				line = outputReader.readLine();
				if (line != null) {
					outputBuilder.append(line);
					outputBuilder.append("\n");
					read = true;
				}
				line = errorReader.readLine();
				if (line != null) {
					errorBuilder.append(line);
					errorBuilder.append("\n");
					read = true;
				}
			}
			return errorBuilder.toString() + outputBuilder.toString();

		} catch (IOException e) {
			Assert.fail(e.toString());
		} catch (InterruptedException e) {
			Assert.fail(e.toString());
		}
		return outputBuilder.toString();
	}
}
