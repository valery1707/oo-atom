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
package oo.atom.codegen.bytebuddy.plugin;

import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.type.TypeDescription;
import static net.bytebuddy.matcher.ElementMatchers.*;
import oo.atom.anno.Atom;
import oo.atom.codegen.bytebuddy.bt.BtApplyAtomAliasPatch;
import oo.atom.codegen.bytebuddy.bt.BtApplyAtomPatch;
import oo.atom.codegen.bytebuddy.bt.BtApplyIfMatches;
import oo.atom.codegen.bytebuddy.bt.BtConditional;
import oo.atom.codegen.bytebuddy.matchers.AnnotatedAtom;
import oo.atom.codegen.bytebuddy.matchers.AnnotatedNonAtom;
import oo.atom.codegen.bytebuddy.matchers.ConjunctionMatcher;
import oo.atom.codegen.bytebuddy.matchers.ExplicitlyExtendingAnything;


/**
 * Atoms instrumentation plugin, which enforces each class to be Atom-compliant,
 * unless it is explicitly annotated with {@link Atom} annotation.
 * 
 * @author Kapralov Sergey
 */
public class EnforcingAtomPlugin extends TaskPlugin implements Plugin {
    public EnforcingAtomPlugin() {
        super(
            new BtApplyIfMatches(
                new ConjunctionMatcher<TypeDescription>(
                    not(new AnnotatedNonAtom()),
                    not(new AnnotatedAtom()),
                    not(isInterface())
                ),
                new BtConditional(
                    new ExplicitlyExtendingAnything(),
                    new BtApplyAtomAliasPatch(),
                    new BtApplyAtomPatch()
                )
            )
        );
    }
}
