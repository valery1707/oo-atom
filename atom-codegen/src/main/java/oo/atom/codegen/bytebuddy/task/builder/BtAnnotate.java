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
package oo.atom.codegen.bytebuddy.task.builder;

import java.lang.annotation.Annotation;
import javaslang.control.Try;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import oo.atom.anno.Atom;
import oo.atom.task.Task;

class BtAnnotateAtom implements Atom {

    @Override
    public final Class<? extends Annotation> annotationType() {
        return Atom.class;
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class BtAnnotate implements Task<DynamicType.Builder<?>> {
    private final TypeDescription type;
    private final DynamicType.Builder<?> builder;

    public BtAnnotate(TypeDescription type, DynamicType.Builder<?> builder) {
        this.type = type;
        this.builder = builder;
    }

    @Override
    public final Try<DynamicType.Builder<?>> result() {
        boolean annotationPresent = type.getDeclaredAnnotations().isAnnotationPresent(Atom.class);
        
        return Try.success(
            annotationPresent ?
                builder :
                builder.annotateType(
                        new BtAnnotateAtom()
                )
        );
    }
}
