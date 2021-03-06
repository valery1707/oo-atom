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
package oo.atom.codegen.bytebuddy.matchers.atomspec;

import java.util.function.Supplier;
import net.bytebuddy.description.type.TypeDescription;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeDoesNotMatch;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeMatches;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class HasNoStaticMethodsTest extends TestsSuite {
    public HasNoStaticMethodsTest() {
        super(
            new TestCase(
                "match type with no static methods",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Foo.class),
                    new HasNoStaticMethods()
                )
            ),
            new TestCase(
                "match type with lambdas",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Baz.class),
                    new HasNoStaticMethods()
                )
            ),
            new TestCase(
                "match enumeration types",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Faz.class),
                    new HasNoStaticMethods()
                )
            ),
            new TestCase(
                "mismatch type with at least one static method",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Bar.class),
                    new HasNoStaticMethods()
                )
            ),
            new TestCase(
                "mismatch enumeration with at least one static method",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Naz.class),
                    new HasNoStaticMethods()
                )
            )
        );
    }

    private static class Foo {

        private static final Object item = new Object();
    }

    private static class Bar {
        private static final Object item() {
            return new Object();
        }
    }
    
    private static class Baz {
        private final Object item() {
            Supplier supplier = () -> new Object();
            return supplier.get();
        }
    }
    
    private enum Faz {
        ONE(1), TWO(2), THREE(3);
        
        private final int origin;

        private Faz(int origin) {
            this.origin = origin;
        }

        public final int getOrigin() {
            return origin;
        }
    }

    private enum Naz {
        ONE, TWO, THREE;

        public static final void staticMethod() {
        }
    }
}
