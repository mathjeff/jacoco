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

import java.util.Arrays;
import java.io.IOException;

/**
 * Main class for the command-line tools.
 */
public class Main {

        private static String usage = "usage: jacoco-cli.jar <command> [<args>]\n"
		+ "\n"
		+ "Available commands:\n"
		+ "\n"
		+ "   instrument\n"
		+ "   report\n";

	/**
	 * Entry point for the command line application.
	 * 
	 * @param args
	 *            Arguments to the application.
	 */
	public static void main(final String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println(usage);
			System.exit(1);
		}
		final String command = args[0];
		final String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
		if ("instrument".equals(command)) {
			Instrument.main(commandArgs);
		} else if ("report".equals(command)) {
			Report.main(commandArgs);
		} else {
			System.err.println("no such command: " + command);
			System.exit(1);
		}
	}

}
