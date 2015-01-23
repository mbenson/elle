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
package org.beaucoup4j.elle.binary;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

/**
 * Extend {@link DoubleBinaryOperator}.
 */
@FunctionalInterface
public interface DoubleBinaryOperator2 extends DoubleBinaryOperator {

    static DoubleBinaryOperator2 of(DoubleBinaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof DoubleBinaryOperator2 ? (DoubleBinaryOperator2) wrapped : (d1, d2) -> wrapped
                .applyAsDouble(d1, d2);
    }

    default DoubleUnaryOperator bindLeft(DoubleSupplier left) {
        Objects.requireNonNull(left);
        return right -> applyAsDouble(left.getAsDouble(), right);
    }

    default DoubleUnaryOperator bindRight(DoubleSupplier right) {
        Objects.requireNonNull(right);
        return left -> applyAsDouble(left, right.getAsDouble());
    }

    default DoubleSupplier bind(DoubleSupplier left, DoubleSupplier right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsDouble(left.getAsDouble(), right.getAsDouble());
    }

    default DoubleBinaryOperator compose(DoubleUnaryOperator leftBefore, DoubleUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (d1, d2) -> applyAsDouble(leftBefore.applyAsDouble(d1), rightBefore.applyAsDouble(d2));
    }

    default <T, U> ToDoubleBiFunction<T, U> compose(ToDoubleFunction<? super T> leftBefore,
            ToDoubleFunction<? super U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (t, u) -> applyAsDouble(leftBefore.applyAsDouble(t), rightBefore.applyAsDouble(u));
    }

}
