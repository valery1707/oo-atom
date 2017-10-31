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
package oo.atom.codegen.bytebuddy.bt;

import java.lang.annotation.Annotation;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import oo.atom.r.RSuccess;
import oo.atom.r.Result;

/**
 *
 * @author Kapralov Sergey
 */
public class BtAnnotate implements BuilderTransition {
    private final Annotation annotation;

    public BtAnnotate(Annotation annotation) {
        this.annotation = annotation;
    }
    
    @Override
    public final Result<Builder<?>> transitionResult(Builder<?> source, TypeDescription type) {
        boolean annotationPresent = type.getDeclaredAnnotations().isAnnotationPresent(
            annotation.annotationType()
        );
        
        return new RSuccess<>(
            annotationPresent ?
                source :
                source.annotateType(
                    annotation
                )
        );
    }
}