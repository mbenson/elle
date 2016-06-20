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
import java.util.function.Function;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.function.ToLongBiFunction;

import org.beaucoup4j.elle.unary.LToLongFunction;

/**
 * Extend {@link ToLongBiFunction}.
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface LToLongBiFunction<T, U> extends ToLongBiFunction<T, U> {

    static <T, U> LToLongBiFunction<T, U> of(ToLongBiFunction<T, U> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LToLongBiFunction ? (LToLongBiFunction<T, U>) wrapped : (t, u) -> wrapped
                .applyAsLong(t, u);
    }

    default LToLongFunction<U> bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> applyAsLong(t.get(), u);
    }

    default LToLongFunction<T> bindRight(Supplier<? extends U> u) {
        Objects.requireNonNull(u);
        return t -> applyAsLong(t, u.get());
    }

    default LongSupplier bind(Supplier<? extends T> left, Supplier<? extends U> right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsLong(left.get(), right.get());
    }

    default <G, H> LToLongBiFunction<G, H> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> applyAsLong(leftBefore.apply(g), rightBefore.apply(h));
    }

}
