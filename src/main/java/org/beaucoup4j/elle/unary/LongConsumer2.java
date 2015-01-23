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
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

import org.beaucoup4j.elle.binary.ObjLongConsumer2;

/**
 * Extend {@link LongConsumer}.
 */
@FunctionalInterface
public interface LongConsumer2 extends LongConsumer {

    static LongConsumer2 of(LongConsumer wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LongConsumer2 ? (LongConsumer2) wrapped : l -> wrapped.accept(l);
    }

    @Override
    default LongConsumer andThen(LongConsumer after) {
        return of(LongConsumer.super.andThen(after));
    }

    default Runnable bind(LongSupplier l) {
        Objects.requireNonNull(l);
        return () -> accept(l.getAsLong());
    }

    default <T> ObjLongConsumer2<T> ignoreObject() {
        return (t, l) -> accept(l);
    }
}
