/*
 * Copyright (c) 2016, 2021, Red Hat, Inc. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.github.ympavlov.study.jmm.darkmagic.prosuming;

import org.openjdk.jcstress.annotations.State;

/*
 * Testable holder object. Implementations consist of an issue to test
 */
@State
public interface Holder
{
	void set(State v);

	State get();

	/*
	 * Set -1 to get 42
	 * Set 2 to get 456
	 */
	class State
	{
		int x1, x2, x3, x4;
		public State(int v) {
			x1 = v; x2 = v; x3 = v; x4 = v;
		}
		public int get() {
			return x4 + x3 + x2 + x1;
		}
		/*	x1 = v;
			x2 = 2*v;
			x3 = 3*x2;
			x4 = x2 * x3;
		}
		public int get() {
			return (x4 * x2 + x3 * 3) * x1;
			//return x1;
		}*/
	}
}
