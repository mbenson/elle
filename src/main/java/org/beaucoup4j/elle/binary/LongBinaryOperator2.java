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
import java.util.function.LongBinaryOperator;
import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

/**
 * Extends {@link LongBinaryOperator}.
 */
@FunctionalInterface
public interface LongBinaryOperator2 extends LongBinaryOperator {

    static LongBinaryOperator2 of(LongBinaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LongBinaryOperator2 ? (LongBinaryOperator2) wrapped : (l1, l2) -> wrapped
                .applyAsLong(l1, l2);
    }

    default LongUnaryOperator bindLeft(LongSupplier left) {
        Objects.requireNonNull(left);
        return right -> applyAsLong(left.getAsLong(), right);
    }

    default LongUnaryOperator bindRight(LongSupplier right) {
        Objects.requireNonNull(right);
        return left -> applyAsLong(left, right.getAsLong());
    }

    default LongSupplier bind(LongSupplier left, LongSupplier right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsLong(left.getAsLong(), right.getAsLong());
    }

    default LongBinaryOperator compose(LongUnaryOperator leftBefore, LongUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (l1, l2) -> applyAsLong(leftBefore.applyAsLong(l1), rightBefore.applyAsLong(l2));
    }

    default <T, U> ToLongBiFunction<T, U> compose(ToLongFunction<? super T> leftBefore,
            ToLongFunction<? super U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (t, u) -> applyAsLong(leftBefore.applyAsLong(t), rightBefore.applyAsLong(u));
    }

}
