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
import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * Extend {@link IntBinaryOperator}.
 */
@FunctionalInterface
public interface IntBinaryOperator2 extends IntBinaryOperator {

    static IntBinaryOperator2 of(IntBinaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof IntBinaryOperator2 ? (IntBinaryOperator2) wrapped : (i1, i2) -> wrapped.applyAsInt(
                i1, i2);
    }

    default IntUnaryOperator bindLeft(IntSupplier left) {
        Objects.requireNonNull(left);
        return right -> applyAsInt(left.getAsInt(), right);
    }

    default IntUnaryOperator bindRight(IntSupplier right) {
        Objects.requireNonNull(right);
        return left -> applyAsInt(left, right.getAsInt());
    }

    default IntSupplier bind(IntSupplier left, IntSupplier right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsInt(left.getAsInt(), right.getAsInt());
    }

    default IntBinaryOperator composeOperators(IntUnaryOperator leftBefore, IntUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (i1, i2) -> applyAsInt(leftBefore.applyAsInt(i1), rightBefore.applyAsInt(i2));
    }

    default <T, U> ToIntBiFunction<T, U> composeFunctions(ToIntFunction<? super T> leftBefore,
            ToIntFunction<? super U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (t, u) -> applyAsInt(leftBefore.applyAsInt(t), rightBefore.applyAsInt(u));
    }

}
