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

import java.util.UUID;
import net.bytebuddy.description.type.TypeDescription;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class AllMethodsAreFinalTest {
    @Test
    public void trueIfTypeContainsNoNonFinalMethods() {
        assertThat(
                new AllMethodsAreFinal().matches(
                        new TypeDescription.ForLoadedType(Foo.class)
                )
        ).isTrue();
    }
    
    @Test
    public void matcherIgnoresBridgeMethods() {
        assertThat(
                new AllMethodsAreFinal().matches(
                        new TypeDescription.ForLoadedType(Baz.class)
                )
        ).isTrue();
    }
    
    @Test
    public void matcherIgnoresStaticMethods() {
        assertThat(
                new AllMethodsAreFinal().matches(
                        new TypeDescription.ForLoadedType(Haz.class)
                )
        ).isTrue();
    }
    
    @Test
    public void matcherSupportsEnum() {
        assertThat(
                new AllMethodsAreFinal().matches(
                        new TypeDescription.ForLoadedType(Faz.class)
                )
        ).isTrue();
    }
    
    @Test
    public void falseIfTypeContainsAtLeastOneNonFinalMethod() {
        assertThat(
                new AllMethodsAreFinal().matches(
                        new TypeDescription.ForLoadedType(Bar.class)
                )
        ).isFalse();
    }
    
    
    private static class Foo {
        public final void method1() {}
        public final void method2() {}
        public final void method3() {}
    }
    
    private static class Bar {
        public final void method1() {}
        public void method2() {}
        public final void method3() {}
    }
    
    private static interface G<T> {
        void method(T value);
    }
    
    private static class Baz implements G<UUID> {
        @Override
        public final void method(UUID value) {}
    }
    
    private static enum Faz {
        ONE(1), TWO(2), THREE(3);
        
        private final int origin;

        private Faz(int origin) {
            this.origin = origin;
        }

        public final int getOrigin() {
            return origin;
        }
    }
    
    private static class Haz {
        private static void method() {}
    }
}