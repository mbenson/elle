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
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import org.beaucoup4j.elle.unary.UnaryOperator2;

/**
 * Extend {@link BinaryOperator}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface BinaryOperator2<T> extends BinaryOperator<T> {

    static <T> BinaryOperator2<T> of(BinaryOperator<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof BinaryOperator2 ? (BinaryOperator2<T>) wrapped : (t, u) -> wrapped.apply(t, u);
    }

    @Override
    default <V> BiFunction2<T, T, V> andThen(Function<? super T, ? extends V> after) {
        return BiFunction2.of(BinaryOperator.super.andThen(after));
    }

    default UnaryOperator2<T> bindLeft(Supplier<? extends T> t1) {
        Objects.requireNonNull(t1);
        return t2 -> apply(t1.get(), t2);
    }

    default UnaryOperator2<T> bindRight(Supplier<? extends T> t2) {
        Objects.requireNonNull(t2);
        return t1 -> apply(t1, t2.get());
    }

    default Supplier<T> bind(Supplier<? extends T> left, Supplier<? extends T> right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> apply(left.get(), right.get());
    }

    default BinaryOperator2<T> capturing(BiConsumer2<? super T, ? super T> before) {
        return (t1, t2) -> {
            before.accept(t1, t2);
            return apply(t1, t2);
        };
    };

    default <G, H> BiFunction<G, H, T> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends T> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> apply(leftBefore.apply(g), rightBefore.apply(h));
    }

}
