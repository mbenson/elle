/*
 *  Copyright the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.beaucoup4j.elle.unary;

import java.util.Objects;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

/**
 * Extend {@link DoubleUnaryOperator}.
 */
@FunctionalInterface
public interface DoubleUnaryOperator2 extends DoubleUnaryOperator {

    static <T> DoubleUnaryOperator2 of(DoubleUnaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof DoubleUnaryOperator2 ? (DoubleUnaryOperator2) wrapped : d -> wrapped.applyAsDouble(d);
    }

    @Override
    default DoubleUnaryOperator compose(DoubleUnaryOperator before) {
        return of(DoubleUnaryOperator.super.compose(before));
    }

    @Override
    default DoubleUnaryOperator andThen(DoubleUnaryOperator after) {
        return of(DoubleUnaryOperator.super.andThen(after));
    }

    default DoubleSupplier bind(DoubleSupplier d) {
        Objects.requireNonNull(d);
        return () -> applyAsDouble(d.getAsDouble());
    }

}
