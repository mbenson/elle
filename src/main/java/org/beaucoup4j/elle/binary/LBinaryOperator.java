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

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import org.beaucoup4j.elle.unary.LUnaryOperator;

/**
 * Extend {@link BinaryOperator}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface LBinaryOperator<T> extends BinaryOperator<T> {

    static <T> LBinaryOperator<T> of(BinaryOperator<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LBinaryOperator ? (LBinaryOperator<T>) wrapped : (t, u) -> wrapped.apply(t, u);
    }

    static <T extends Comparable<T>> LBinaryOperator<T> max() {
        return of(BinaryOperator.maxBy(Comparator.naturalOrder()));
    }

    static <T extends Comparable<T>> LBinaryOperator<T> min() {
        return of(BinaryOperator.minBy(Comparator.naturalOrder()));
    }

    @Override
    default <V> LBiFunction<T, T, V> andThen(Function<? super T, ? extends V> after) {
        return LBiFunction.of(BinaryOperator.super.andThen(after));
    }

    default LUnaryOperator<T> bindLeft(Supplier<? extends T> t1) {
        Objects.requireNonNull(t1);
        return Lt -> apply(t1.get(), Lt);
    }

    default LUnaryOperator<T> bindRight(Supplier<? extends T> Lt) {
        Objects.requireNonNull(Lt);
        return t1 -> apply(t1, Lt.get());
    }

    default Supplier<T> bind(Supplier<? extends T> left, Supplier<? extends T> right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> apply(left.get(), right.get());
    }

    default LBinaryOperator<T> capturing(LBiConsumer<? super T, ? super T> before) {
        return (t1, Lt) -> {
            before.accept(t1, Lt);
            return apply(t1, Lt);
        };
    };

    default <G, H> BiFunction<G, H, T> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends T> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> apply(leftBefore.apply(g), rightBefore.apply(h));
    }

}
