/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.description.type.TypeDescription;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtIfNotEqualTest extends TestsSuite {
    public SmtIfNotEqualTest() {
        super(
            new TestCase(
                "arrays are compared using deep equality",
                new AssertTokensToRepresentIdenticalBytecode(
                    new SmtIfNotEqual(
                        new TypeDescription.ForLoadedType(Object[].class),
                        new SmtReturnInteger(42)
                    ),
                    new SmtIfNotDeeplyEqual(
                        new SmtReturnInteger(42)
                    )
                )
            ),
            new TestCase(
                "single values are compared using value equality",
                new AssertTokensToRepresentIdenticalBytecode(
                    new SmtIfNotEqual(
                        new TypeDescription.ForLoadedType(Object.class),
                        new SmtReturnInteger(42)
                    ),
                    new SmtIfNotEqualByValue(
                        new SmtReturnInteger(42)
                    )
                )
            )
        );
    }
}
