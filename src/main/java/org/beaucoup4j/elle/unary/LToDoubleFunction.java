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
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

/**
 * Extend {@link ToDoubleFunction}.
 * 
 * @param <T>
 */
@FunctionalInterface
public interface LToDoubleFunction<T> extends ToDoubleFunction<T> {

    static <T> LToDoubleFunction<T> of(ToDoubleFunction<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LToDoubleFunction ? (LToDoubleFunction<T>) wrapped : t -> wrapped.applyAsDouble(t);
    }

    default LToIntFunction<T> andThen(DoubleToIntFunction after) {
        return t -> after.applyAsInt(applyAsDouble(t));
    }

    default LToLongFunction<T> andThen(DoubleToLongFunction after) {
        return t -> after.applyAsLong(applyAsDouble(t));
    }

    default DoubleSupplier bind(Supplier<T> t) {
        Objects.requireNonNull(t);
        return () -> applyAsDouble(t.get());
    }

}
