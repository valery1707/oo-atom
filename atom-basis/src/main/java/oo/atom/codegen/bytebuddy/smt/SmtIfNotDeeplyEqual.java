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

import java.util.Objects;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import oo.atom.codegen.bytebuddy.smt.c.Condition;


/**
 *
 * @author Kapralov Sergey
 */
class SmtIfNotDeeplyEqual extends SmtCombined implements StackManipulationToken  {
    private static final StackManipulation INVOKE_DEEPEQUALS;
    
    static {
        try {
            INVOKE_DEEPEQUALS = MethodInvocation.invoke(
                new MethodDescription.ForLoadedMethod(
                    Objects.class.getMethod("deepEquals", Object.class, Object.class)
                )
            );
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public SmtIfNotDeeplyEqual(StackManipulationToken smt) {
        super(
            new SmtStatic(INVOKE_DEEPEQUALS),
            new SmtIf(
                Condition.IS_FALSE,
                smt
            )
        );
    }
}