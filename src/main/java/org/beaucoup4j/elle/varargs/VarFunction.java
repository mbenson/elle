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
package org.beaucoup4j.elle.varargs;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import org.beaucoup4j.elle.unary.LFunction;

/**
 * Varargs function.
 *
 * @param <E>
 * @param <R>
 */
@FunctionalInterface
public interface VarFunction<E, R> {

    R apply(@SuppressWarnings("unchecked") E... elements);

    default <V> VarFunction<E, V> andThen(final Function<? super R, ? extends V> after) {
        return elements -> after.apply(this.apply(elements));
    }

    @SuppressWarnings("unchecked")
    default Supplier<R> bind(Supplier<? extends E[]> t) {
        Objects.requireNonNull(t);
        return () -> apply(t.get());
    }

    @SuppressWarnings("unchecked")
    default <V> LFunction<V, R> compose(final Function<? super V, ? extends E[]> before) {
        return t -> apply(before.apply(t));
    }

    @SuppressWarnings("unchecked")
    default <V> LFunction<V, R> composeSingle(final Function<? super V, ? extends E> before) {
        return t -> apply(before.apply(t));
    }

    default LFunction<E[], R> toUnary() {
        return e -> apply(e);
    }
}
