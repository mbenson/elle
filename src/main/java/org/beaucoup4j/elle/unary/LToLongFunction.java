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
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;

/**
 * Extend {@link ToLongFunction}.
 * 
 * @param <T>
 */
@FunctionalInterface
public interface LToLongFunction<T> extends ToLongFunction<T> {

    static <T> LToLongFunction<T> of(ToLongFunction<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LToLongFunction ? (LToLongFunction<T>) wrapped : t -> wrapped.applyAsLong(t);
    }

    default LToDoubleFunction<T> andThen(LongToDoubleFunction next) {
        return t -> next.applyAsDouble(applyAsLong(t));
    }

    default LToIntFunction<T> andThen(LongToIntFunction next) {
        return t -> next.applyAsInt(applyAsLong(t));
    }

    default LongSupplier bind(Supplier<T> t) {
        Objects.requireNonNull(t);
        return () -> applyAsLong(t.get());
    }

}
