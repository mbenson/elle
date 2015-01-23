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
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import org.beaucoup4j.elle.binary.ObjDoubleConsumer2;

/**
 * Extend {@link DoubleConsumer}.
 */
@FunctionalInterface
public interface DoubleConsumer2 extends DoubleConsumer {

    static DoubleConsumer2 of(DoubleConsumer wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof DoubleConsumer2 ? (DoubleConsumer2) wrapped : d -> wrapped.accept(d);
    }

    static DoubleConsumer2 noop() {
        return d -> {
        };
    }

    default DoubleConsumer andThen(DoubleConsumer after) {
        return of(DoubleConsumer.super.andThen(after));
    }

    default Runnable bind(DoubleSupplier d) {
        Objects.requireNonNull(d);
        return () -> accept(d.getAsDouble());
    }

    default <T> ObjDoubleConsumer2<T> ignoreObject() {
        return (t, d) -> accept(d);
    }
}
