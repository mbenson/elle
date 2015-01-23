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
import java.util.function.UnaryOperator;

/**
 * Extend {@link UnaryOperator}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface UnaryOperator2<T> extends UnaryOperator<T> {

    static <T> UnaryOperator2<T> of(UnaryOperator<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof UnaryOperator2 ? (UnaryOperator2<T>) wrapped : t -> wrapped.apply(t);
    }

    @Override
    default <V> Function2<V, T> compose(Function<? super V, ? extends T> before) {
        return Function2.of(UnaryOperator.super.compose(before));
    }

    @Override
    default <V> Function2<T, V> andThen(Function<? super T, ? extends V> after) {
        return Function2.of(UnaryOperator.super.andThen(after));
    }

    default Supplier<T> bind(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return () -> apply(t.get());
    }

}
