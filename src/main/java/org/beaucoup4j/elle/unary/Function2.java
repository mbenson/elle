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
package org.beaucoup4j.elle.unary;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Extend {@link Function}.
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface Function2<T, R> extends Function<T, R> {

    static <T, R> Function2<T, R> of(Function<T, R> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof Function2 ? (Function2<T, R>) wrapped : t -> wrapped.apply(t);
    }

    @Override
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        return of(Function.super.compose(before));
    }

    @Override
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        return of(Function.super.andThen(after));
    }

    default Supplier<R> bind(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return () -> apply(t.get());
    }

}
