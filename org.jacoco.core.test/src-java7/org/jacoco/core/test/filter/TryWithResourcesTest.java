/*******************************************************************************
 * Copyright (c) 2009, 2017 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Evgeny Mandrikov - initial API and implementation
 *
 *******************************************************************************/
package org.jacoco.core.test.filter;

import java.io.IOException;

import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.internal.Java9Support;
import org.jacoco.core.test.TargetLoader;
import org.jacoco.core.test.validation.ValidationTestBase;
import org.jacoco.core.test.filter.targets.TryWithResources;
import org.junit.Test;

/**
 * Test of filtering of a bytecode that is generated for a try-with-resources
 * statement.
 */
public class TryWithResourcesTest extends ValidationTestBase {

	public TryWithResourcesTest() {
		super("src-java7", TryWithResources.class);
	}

	/**
	 * {@link TryWithResources#test()}
	 */
	@Test
	public void test() {
		assertLine("test.before", ICounter.FULLY_COVERED);
		// without filter next line covered partly:
		assertLine("test.try", ICounter.FULLY_COVERED);
		assertLine("test.open1", ICounter.FULLY_COVERED);
		assertLine("test.open2", ICounter.FULLY_COVERED);
		assertLine("test.open3", ICounter.FULLY_COVERED);
		assertLine("test.body", ICounter.FULLY_COVERED);
		// without filter next line has branches:
		assertLine("test.close", ICounter.EMPTY);
		assertLine("test.catch", ICounter.NOT_COVERED);
		assertLine("test.finally", ICounter.PARTLY_COVERED);
	}

	/**
	 * {@link TryWithResources#test2()}
	 */
	@Test
	public void test2() {
		assertLine("test2.before", ICounter.FULLY_COVERED);
		// without filter next line covered partly:
		assertLine("test2.try", ICounter.FULLY_COVERED);
		assertLine("test2.open1", ICounter.FULLY_COVERED);
		assertLine("test2.open2", ICounter.FULLY_COVERED);
		assertLine("test2.open3", ICounter.FULLY_COVERED);
		assertLine("test2.body", ICounter.FULLY_COVERED);
		// without filter next line has branches:
		assertLine("test2.close", ICounter.EMPTY);
		assertLine("test2.catch", ICounter.NOT_COVERED);
		assertLine("test2.finally", ICounter.PARTLY_COVERED);
		assertLine("test2.after", ICounter.FULLY_COVERED);
	}

	/**
	 * {@link TryWithResources#returnInBody()}
	 */
	@Test
	public void returnInBody() {
		// without filter next line covered partly:
		assertLine("returnInBody.try", ICounter.FULLY_COVERED);
		assertLine("returnInBody.open", ICounter.FULLY_COVERED);
		// without filter next line has branches:
		// TODO with javac 1.8.0_31 part of return instructions belong to close
		// assertLine("returnInBody.close", ICounter.EMPTY);
		assertLine("returnInBody.return", ICounter.FULLY_COVERED);
	}

	/**
	 * {@link TryWithResources#nested()}
	 */
	@Test
	public void nested() {
		// without filter next line covered partly:
		assertLine("nested.try1", ICounter.FULLY_COVERED);
		assertLine("nested.open1", ICounter.FULLY_COVERED);
		assertLine("nested.catch1", ICounter.NOT_COVERED);

		// without filter next line covered partly:
		assertLine("nested.try2", ICounter.FULLY_COVERED);
		assertLine("nested.body", ICounter.FULLY_COVERED);
		assertLine("nested.catch2", ICounter.NOT_COVERED);
		assertLine("nested.finally2", ICounter.PARTLY_COVERED);

		// next lines not covered on exceptional path:
		assertLine("nested.try3", ICounter.PARTLY_COVERED, 0, 0);
		assertLine("nested.open3", ICounter.PARTLY_COVERED, 0, 0);
		assertLine("nested.body3", ICounter.PARTLY_COVERED, 0, 0);
		assertLine("nested.catch3", ICounter.NOT_COVERED);
		assertLine("nested.finally3", ICounter.PARTLY_COVERED, 0, 0);

		// without filter next lines have branches:
		assertLine("nested.close3", ICounter.EMPTY);
		assertLine("nested.close2", ICounter.EMPTY);
		assertLine("nested.close1", ICounter.EMPTY);
	}

	/*
	 * Corner cases
	 */

	/**
	 * {@link TryWithResources#handwritten()}
	 */
	@Test
	public void handwritten() {
		if (isJDKCompiler) {
			// TODO getPrevious doesn't skip line numbers
			assertLine("handwritten",
					/* partly when ECJ: */ICounter.PARTLY_COVERED);
		}
	}

	/**
	 * {@link TryWithResources#empty()}
	 */
	@Test
	public void empty() throws IOException {
		final boolean java9 = Java9Support.isPatchRequired(
				TargetLoader.getClassDataAsBytes(TryWithResources.class));

		assertLine("empty.try", ICounter.FULLY_COVERED, 0, 0);
		assertLine("empty.open", ICounter.FULLY_COVERED);
		// empty when EJC:
		if (isJDKCompiler) {
			if (java9) {
				assertLine("empty.close", ICounter.FULLY_COVERED, 0, 0);
			} else {
				// branches with javac 7 and 8
				assertLine("empty.close", ICounter.PARTLY_COVERED);
			}
		}
	}

	/**
	 * {@link TryWithResources#throwInBody()}
	 */
	@Test
	public void throwInBody() throws Exception {
		// not filtered
		assertLine("throwInBody.try", ICounter.NOT_COVERED);
		assertLine("throwInBody.close", ICounter.NOT_COVERED);
	}

}
