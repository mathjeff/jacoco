/*******************************************************************************
 * Copyright (c) 2009, 2017 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jeff Gaston - initial implementation
 *
 *******************************************************************************/
package org.jacoco.cli;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.ICoverageVisitor;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.runtime.IExecutionDataAccessorGenerator;
import org.jacoco.core.runtime.WildcardMatcher;
import org.objectweb.asm.ClassReader;


// TODO: probably this class should be a filterer passed in to Analyzer rather than a subclass -
// That way we can preemptively prevent having a problem involving wishing for multiple inheritance
// (which isn't even really a well-defined request anyway).
// However, FilteredInstrumenter inherits from Instrumenter, so FilteredAnalyzer does the same for
// now. Hopefully a peer reviewer will get back with some feedback about this before this gets
// merged.
class FilteredAnalyzer extends Analyzer {

	private final WildcardMatcher include;
	private final WildcardMatcher exclude;

	public FilteredAnalyzer(final ExecutionDataStore executionData,
			final ICoverageVisitor coverageVisitor,
			final WildcardMatcher include, final WildcardMatcher exclude) {
		super(executionData, coverageVisitor);
		this.include = include;
		this.exclude = exclude;
	}

	@Override
	protected boolean includeClass(final ClassReader reader) {
		return this.includeClass(reader.getClassName());
	}

	private boolean includeClass(final String internalClassName) {
		final String className = internalClassName.replace('/', '.');
		boolean shouldInclude = include.matches(className) && !exclude.matches(className);
		return shouldInclude;
	}

}
