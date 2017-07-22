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
package oo.atom.codegen.bytebuddy.task.equals;

import javaslang.control.Try;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import oo.atom.task.TChained;
import oo.atom.task.Task;
import oo.atom.codegen.bytebuddy.branching.BIfAcmp;
import oo.atom.codegen.bytebuddy.branching.BMark;

class SmtIfEqualByReferenceTask implements Task<StackManipulation> {

    private final boolean equals;
    private final StackManipulation sm;

    public SmtIfEqualByReferenceTask(boolean equals, StackManipulation sm) {
        this.equals = equals;
        this.sm = sm;
    }

    @Override
    public final Try<StackManipulation> result() {
        final Label checkEnd = new Label();
        return Try.success(
                new StackManipulation.Compound(
                        new BIfAcmp(equals, checkEnd),
                        sm,
                        new BMark(checkEnd)
                )
        );
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SmtIfEqualByReference extends TChained<StackManipulation> implements Task<StackManipulation> {
    public SmtIfEqualByReference(boolean equals, Task<StackManipulation> task) {
        super(task, sm -> new SmtIfEqualByReferenceTask(equals, sm));
    }
}