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
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Extend {@link BiFunction}.
 *
 * @param <T>
 * @param <U>
 * @param <R>
 */
@FunctionalInterface
public interface BiFunction2<T, U, R> extends BiFunction<T, U, R> {

    static <T, U, R> BiFunction2<T, U, R> of(BiFunction<T, U, R> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof BiFunction2 ? (BiFunction2<T, U, R>) wrapped : (t, u) -> wrapped.apply(t, u);
    }

    @Override
    default <V> BiFunction2<T, U, V> andThen(Function<? super R, ? extends V> after) {
        return of(BiFunction.super.andThen(after));
    }

    default Function<U, R> bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> apply(t.get(), u);
    }

    default Function<T, R> bindRight(Supplier<? extends U> u) {
        Objects.requireNonNull(u);
        return t -> apply(t, u.get());
    }

    default Supplier<R> bind(Supplier<? extends T> left, Supplier<? extends U> right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> apply(left.get(), right.get());
    }

    default BiFunction2<T, U, R> capturing(BiConsumer2<? super T, ? super U> before) {
        return (t, u) -> {
            before.accept(t, u);
            return apply(t, u);
        };
    };

    default <G, H> BiFunction<G, H, R> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> apply(leftBefore.apply(g), rightBefore.apply(h));
    }

}
