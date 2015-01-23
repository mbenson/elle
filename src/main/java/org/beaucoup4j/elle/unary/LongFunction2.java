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
import java.util.function.LongFunction;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * Extend {@link LongFunction}.
 * 
 * @param <R>
 */
@FunctionalInterface
public interface LongFunction2<R> extends LongFunction<R> {

    static <R> LongFunction2<R> of(LongFunction<R> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LongFunction2 ? (LongFunction2<R>) wrapped : l -> wrapped.apply(l);
    }

    default Supplier<R> bind(LongSupplier l) {
        Objects.requireNonNull(l);
        return () -> apply(l.getAsLong());
    }

}
