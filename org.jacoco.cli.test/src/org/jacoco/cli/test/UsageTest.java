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
package org.jacoco.cli.test;

import org.junit.Assert;
import org.junit.Test;

import org.jacoco.cli.test.util.CLIRunner;

/**
 * Tests usage messages of the command-line interface
 */
public class UsageTest {

	private void assertResponseContains(String message, String substring) {
		if (!message.contains(substring)) {
			Assert.fail("Response message must contain '" + substring + "'\nbut was '" + message + "'\ninstead.");
		}
	}
	@Test
	public void testNoArgs() {
		String result = CLIRunner.run("");
		assertResponseContains(result, "usage");
		assertResponseContains(result, "instrument");
		assertResponseContains(result, "report");
	}

	@Test
	public void testCommandWithMissingArgs() {
		String result = CLIRunner.run("instrument");
		assertResponseContains(result, "is required");
	}

	@Test
	public void testCommandWithInvalidOption() {
		String result = CLIRunner.run("instrument --this-option-does-not-exist");
		assertResponseContains(result, "\"--this-option-does-not-exist\" is not a valid option");
	}

	@Test
	public void testCommandWithMissingOperand() {
		String result = CLIRunner.run("instrument --dest");
		assertResponseContains(result, "Option \"--dest\" takes an operand");
	}

}
