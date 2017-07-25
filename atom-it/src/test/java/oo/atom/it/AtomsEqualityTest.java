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
package oo.atom.it;

import oo.atom.tests.AssertAtomsAreEqual;
import oo.atom.tests.AssertAtomsAreNotEqual;
import oo.atom.tests.AssertionsSuite;


/**
 *
 * @author Kapralov Sergey
 */
public class AtomsEqualityTest extends AssertionsSuite {
    public AtomsEqualityTest() {
        super(
            new AssertAtomsAreEqual(
                "different atom objects with same fields are equal", 
                new Foo(4), 
                new Foo(4)
            ),
            new AssertAtomsAreNotEqual(
                "atom objects with different fields are not equal", 
                new Foo(4),
                new Foo(5)
            ),
            new AssertAtomsAreNotEqual(
                "atoms of different types are not equal", 
                new Foo(4),
                new Bar(4)
            ),
            new AssertAtomsAreEqual(
                "alias atom and basis atom with same constructor arguments are equal",
                new Fooo(),
                new Foo(42)
            )
        );
    }
}
