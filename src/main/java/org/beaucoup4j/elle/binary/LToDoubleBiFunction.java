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
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;

import org.beaucoup4j.elle.unary.LToDoubleFunction;

/**
 * Extend {@link ToDoubleBiFunction}.
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface LToDoubleBiFunction<T, U> extends ToDoubleBiFunction<T, U> {

    static <T, U> LToDoubleBiFunction<T, U> of(ToDoubleBiFunction<T, U> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LToDoubleBiFunction ? (LToDoubleBiFunction<T, U>) wrapped : (t, u) -> wrapped
                .applyAsDouble(t, u);
    }

    default LToDoubleFunction<U> bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> applyAsDouble(t.get(), u);
    }

    default LToDoubleFunction<T> bindRight(Supplier<? extends U> u) {
        Objects.requireNonNull(u);
        return t -> applyAsDouble(t, u.get());
    }

    default DoubleSupplier bind(Supplier<? extends T> left, Supplier<? extends U> right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        return () -> applyAsDouble(left.get(), right.get());
    }

    default <G, H> LToDoubleBiFunction<G, H> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> applyAsDouble(leftBefore.apply(g), rightBefore.apply(h));
    }

}