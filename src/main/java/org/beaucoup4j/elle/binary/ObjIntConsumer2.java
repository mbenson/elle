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
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * Extend {@link ObjIntConsumer}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface ObjIntConsumer2<T> extends ObjIntConsumer<T> {

    static <T, U> ObjIntConsumer2<T> of(ObjIntConsumer<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof ObjIntConsumer2 ? (ObjIntConsumer2<T>) wrapped : (t, u) -> wrapped.accept(t, u);
    }

    default IntConsumer bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> accept(t.get(), u);
    }

    default Consumer<T> bindRight(IntSupplier i) {
        Objects.requireNonNull(i);
        return t -> accept(t, i.getAsInt());
    }

    default Runnable bind(Supplier<? extends T> t, IntSupplier i) {
        Objects.requireNonNull(i);
        return () -> accept(t.get(), i.getAsInt());
    }

    default <G, H> BiConsumer<G, H> composeFunctions(Function<? super G, ? extends T> leftBefore,
            ToIntFunction<? super H> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> accept(leftBefore.apply(g), rightBefore.applyAsInt(h));
    }

    default <G> ObjIntConsumer<G> composeFunctionOperator(Function<? super G, ? extends T> leftBefore,
            IntUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, i) -> accept(leftBefore.apply(g), rightBefore.applyAsInt(i));
    }
}
