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

import java.io.File;

import org.junit.rules.TemporaryFolder;
import org.junit.Assert;

public class PathUtils {

	/**
	 * Returns the file path of the exectuble jar under test
	 */
	public static File getRunnerFile() {
		String filePath = System.getProperty("runnerPath");
		if (filePath != null) {
			File runnerFile = new File(filePath);
			if (runnerFile.exists()) {
				return runnerFile;
			} else {
				Assert.fail("File '" + runnerFile.getPath() + "' specified in system property runnerPath does not exist");
			}
		}
		Assert.fail("File path of org.jacoco.cli.jar must be specified in the system property \"runnerPath\"");
		return null; // we won't get to this line, but the compiler doesn't know that
	}

	/**
	 * Creates a temporary directory
	 */
	public static File newTempDir() {
		TemporaryFolder tempFolder = new TemporaryFolder();
		return tempFolder.getRoot();
	}


}
