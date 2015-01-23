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
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import org.beaucoup4j.elle.binary.ObjIntConsumer2;

/**
 * Extend {@link IntConsumer}.
 */
@FunctionalInterface
public interface IntConsumer2 extends IntConsumer {

    static IntConsumer2 of(IntConsumer wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof IntConsumer2 ? (IntConsumer2) wrapped : i -> wrapped.accept(i);
    }

    @Override
    default IntConsumer andThen(IntConsumer after) {
        return of(IntConsumer.super.andThen(after));
    }

    default Runnable bind(IntSupplier i) {
        Objects.requireNonNull(i);
        return () -> accept(i.getAsInt());
    }

    default <T> ObjIntConsumer2<T> ignoreObject() {
        return (t, i) -> accept(i);
    }
}
