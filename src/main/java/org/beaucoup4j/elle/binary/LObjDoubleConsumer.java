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
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

import org.beaucoup4j.elle.nullary.LRunnable;
import org.beaucoup4j.elle.unary.LConsumer;
import org.beaucoup4j.elle.unary.LDoubleConsumer;

/**
 * Extend {@link ObjDoubleConsumer}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface LObjDoubleConsumer<T> extends ObjDoubleConsumer<T> {

    static <T, U> LObjDoubleConsumer<T> of(ObjDoubleConsumer<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LObjDoubleConsumer ? (LObjDoubleConsumer<T>) wrapped : (t, u) -> wrapped.accept(t, u);
    }

    default LDoubleConsumer bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> accept(t.get(), u);
    }

    default LConsumer<T> bindRight(DoubleSupplier d) {
        Objects.requireNonNull(d);
        return t -> accept(t, d.getAsDouble());
    }

    default LRunnable bind(Supplier<? extends T> t, DoubleSupplier d) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(d);
        return () -> accept(t.get(), d.getAsDouble());
    }

    default <G, H> LBiConsumer<G, H> composeFunctions(Function<? super G, ? extends T> leftBefore,
            ToDoubleFunction<? super H> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> accept(leftBefore.apply(g), rightBefore.applyAsDouble(h));
    }

    default <G> LObjDoubleConsumer<G> composeFunctionOperator(Function<? super G, ? extends T> leftBefore,
            DoubleUnaryOperator rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, d) -> accept(leftBefore.apply(g), rightBefore.applyAsDouble(d));
    }
}
