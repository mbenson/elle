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
import java.util.function.IntToLongFunction;
import java.util.function.LongSupplier;

/**
 * Extend {@link IntToLongFunction}.
 */
@FunctionalInterface
public interface IntToLongFunction2 extends IntToLongFunction {

    static IntToLongFunction2 of(IntToLongFunction wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof IntToLongFunction2 ? (IntToLongFunction2) wrapped : i -> wrapped.applyAsLong(i);
    }

    default LongSupplier bind(IntSupplier i) {
        Objects.requireNonNull(i);
        return () -> applyAsLong(i.getAsInt());
    }

}
