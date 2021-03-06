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

import io.vavr.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.r.RInferred;
import oo.atom.r.Result;

class SmtFieldsEqualityInference implements Result.Inference<StackManipulation> {

    private final TypeDescription type;

    public SmtFieldsEqualityInference(TypeDescription type) {
        this.type = type;
    }

    @Override
    public final Result<StackManipulation> result() {
        return new SmtCombined(
            List.of(type)
                .flatMap(TypeDescription::getDeclaredFields)
                .filter(f -> !f.isStatic())
                .map(f -> new SmtCheckFieldEquality(type, f))
                .toJavaArray(SmtCheckFieldEquality.class)
        );
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SmtCheckFieldsEquality extends RInferred<StackManipulation> implements StackManipulationToken {
    public SmtCheckFieldsEquality(final TypeDescription type) {
        super(new SmtFieldsEqualityInference(type));
    }
}
