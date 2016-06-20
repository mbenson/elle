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
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;

import org.beaucoup4j.elle.nullary.LRunnable;
import org.beaucoup4j.elle.unary.LConsumer;
import org.beaucoup4j.elle.unary.LLongConsumer;

/**
 * Extend {@link ObjLongConsumer}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface LObjLongConsumer<T> extends ObjLongConsumer<T> {

    static <T, U> LObjLongConsumer<T> of(ObjLongConsumer<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LObjLongConsumer ? (LObjLongConsumer<T>) wrapped : (t, u) -> wrapped.accept(t, u);
    }

    default LLongConsumer bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> accept(t.get(), u);
    }

    default LConsumer<T> bindRight(LongSupplier l) {
        Objects.requireNonNull(l);
        return t -> accept(t, l.getAsLong());
    }

    default LRunnable bind(Supplier<? extends T> t, LongSupplier l) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(l);
        return () -> accept(t.get(), l.getAsLong());
    }

    default <G, H> LBiConsumer<G, H> composeFunctions(Function<? super G, ? extends T> leftBefore,
            ToLongFunction<? super H> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> accept(leftBefore.apply(g), rightBefore.applyAsLong(h));
    }

    default <G> LObjLongConsumer<G> composeFunctionOperator(Function<? super G, ? extends T> leftBefore,
            LongUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, l) -> accept(leftBefore.apply(g), rightBefore.applyAsLong(l));
    }
}
