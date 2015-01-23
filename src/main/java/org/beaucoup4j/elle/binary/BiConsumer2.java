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
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Extend {@link BiConsumer}.
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface BiConsumer2<T, U> extends BiConsumer<T, U> {

    static <T, U> BiConsumer2<T, U> of(BiConsumer<T, U> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof BiConsumer2 ? (BiConsumer2<T, U>) wrapped : (t, u) -> wrapped.accept(t, u);
    }

    static <T, U> BiConsumer2<T, U> noop() {
        return (t, u) -> {
        };
    }

    @Override
    default BiConsumer2<T, U> andThen(BiConsumer<? super T, ? super U> after) {
        return of(BiConsumer.super.andThen(after));
    }

    default BiPredicate2<T, U> asPredicate() {
        return (t, u) -> {
            accept(t, u);
            return true;
        };
    }

    default Consumer<U> bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> accept(t.get(), u);
    }

    default Consumer<T> bindRight(Supplier<? extends U> u) {
        Objects.requireNonNull(u);
        return t -> accept(t, u.get());
    }

    default Runnable bind(Supplier<? extends T> t, Supplier<? extends U> u) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(u);
        return () -> accept(t.get(), u.get());
    }

    default <G, H> BiConsumer<G, H> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> accept(leftBefore.apply(g), rightBefore.apply(h));
    }

}
