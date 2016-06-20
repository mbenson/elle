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
import java.util.function.ToDoubleFunction;

import org.beaucoup4j.elle.unary.LDoubleUnaryOperator;

/**
 * Extend {@link DoubleBinaryOperator}.
 */
@FunctionalInterface
public interface LDoubleBinaryOperator extends DoubleBinaryOperator {

    static LDoubleBinaryOperator of(DoubleBinaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LDoubleBinaryOperator ? (LDoubleBinaryOperator) wrapped : (d1, Ld) -> wrapped
                .applyAsDouble(d1, Ld);
    }

    default LDoubleUnaryOperator bindLeft(DoubleSupplier left) {
        Objects.requireNonNull(left);
        return right -> applyAsDouble(left.getAsDouble(), right);
    }

    default LDoubleUnaryOperator bindRight(DoubleSupplier right) {
        Objects.requireNonNull(right);
        return left -> applyAsDouble(left, right.getAsDouble());
    }

    default DoubleSupplier bind(DoubleSupplier left, DoubleSupplier right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsDouble(left.getAsDouble(), right.getAsDouble());
    }

    default LDoubleBinaryOperator composeOperators(DoubleUnaryOperator leftBefore, DoubleUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (d1, Ld) -> applyAsDouble(leftBefore.applyAsDouble(d1), rightBefore.applyAsDouble(Ld));
    }

    default <T, U> LToDoubleBiFunction<T, U> composeFunctions(ToDoubleFunction<? super T> leftBefore,
            ToDoubleFunction<? super U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (t, u) -> applyAsDouble(leftBefore.applyAsDouble(t), rightBefore.applyAsDouble(u));
    }

}
