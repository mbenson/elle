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
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

import org.beaucoup4j.elle.unary.LToIntFunction;

/**
 * Extend {@link ToIntBiFunction}.
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface LToIntBiFunction<T, U> extends ToIntBiFunction<T, U> {

    static <T, U> LToIntBiFunction<T, U> of(ToIntBiFunction<T, U> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LToIntBiFunction ? (LToIntBiFunction<T, U>) wrapped : (t, u) -> wrapped.applyAsInt(t,
                u);
    }

    default LToIntFunction<U> bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> applyAsInt(t.get(), u);
    }

    default LToIntFunction<T> bindRight(Supplier<? extends U> u) {
        Objects.requireNonNull(u);
        return t -> applyAsInt(t, u.get());
    }

    default IntSupplier bind(Supplier<? extends T> left, Supplier<? extends U> right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsInt(left.get(), right.get());
    }

    default <G, H> LToIntBiFunction<G, H> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> applyAsInt(leftBefore.apply(g), rightBefore.apply(h));
    }

}
