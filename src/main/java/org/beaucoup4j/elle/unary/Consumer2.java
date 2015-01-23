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
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.beaucoup4j.elle.binary.BiConsumer2;
import org.beaucoup4j.elle.binary.ObjDoubleConsumer2;
import org.beaucoup4j.elle.binary.ObjIntConsumer2;
import org.beaucoup4j.elle.binary.ObjLongConsumer2;

/**
 * Extend {@link Consumer}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface Consumer2<T> extends Consumer<T> {

    static <T> Consumer2<T> of(Consumer<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof Consumer2 ? (Consumer2<T>) wrapped : t -> wrapped.accept(t);
    }

    static <T> Consumer2<T> noop() {
        return t -> {
        };
    }

    @Override
    default Consumer<T> andThen(Consumer<? super T> after) {
        return of(Consumer.super.andThen(after));
    }

    default Function<T, T> asFunction(Supplier<? extends T> after) {
        return t -> {
            accept(t);
            return after.get();
        };
    }

    default Predicate2<T> asPredicate(BooleanSupplier after) {
        return t -> {
            accept(t);
            return after.getAsBoolean();
        };
    }

    default Runnable bind(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return () -> accept(t.get());
    }

    default <G> Consumer<G> compose(Function<? super G, ? extends T> before) {
        return g -> accept(before.apply(g));
    }

    default <U> BiConsumer2<T, U> consumeLeft() {
        return (t, u) -> accept(t);
    }

    default <U> BiConsumer2<U, T> consumeRight() {
        return (u, t) -> accept(t);
    }

    default ObjDoubleConsumer2<T> ignoreDouble() {
        return (t, d) -> accept(t);
    }

    default ObjIntConsumer2<T> ignoreInt() {
        return (t, i) -> accept(t);
    }

    default ObjLongConsumer2<T> ignoreLong() {
        return (t, l) -> accept(t);
    }
}
