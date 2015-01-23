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
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * Extend {@link ToIntFunction}.
 * 
 * @param <T>
 */
@FunctionalInterface
public interface ToIntFunction2<T> extends ToIntFunction<T> {

    static <T> ToIntFunction2<T> of(ToIntFunction<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof ToIntFunction2 ? (ToIntFunction2<T>) wrapped : t -> wrapped.applyAsInt(t);
    }

    default ToDoubleFunction2<T> andThen(IntToDoubleFunction after) {
        return t -> after.applyAsDouble(applyAsInt(t));
    }

    default ToLongFunction2<T> andThen(IntToLongFunction after) {
        return t -> after.applyAsLong(applyAsInt(t));
    }

    default IntSupplier bind(Supplier<T> t) {
        Objects.requireNonNull(t);
        return () -> applyAsInt(t.get());
    }

}
