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
import java.util.function.DoubleToLongFunction;
import java.util.function.LongSupplier;

/**
 * Extend {@link DoubleToLongFunction}.
 */
@FunctionalInterface
public interface DoubleToLongFunction2 extends DoubleToLongFunction {

    static DoubleToLongFunction2 of(DoubleToLongFunction wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof DoubleToLongFunction2 ? (DoubleToLongFunction2) wrapped : d -> wrapped.applyAsLong(d);
    }

    default LongSupplier bind(DoubleSupplier d) {
        Objects.requireNonNull(d);
        return () -> applyAsLong(d.getAsDouble());
    }

}
