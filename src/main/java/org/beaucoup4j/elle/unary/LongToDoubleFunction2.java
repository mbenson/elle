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
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;

/**
 * Extend {@link LongToDoubleFunction}.
 */
@FunctionalInterface
public interface LongToDoubleFunction2 extends LongToDoubleFunction {

    static LongToDoubleFunction2 of(LongToDoubleFunction wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LongToDoubleFunction2 ? (LongToDoubleFunction2) wrapped : l -> wrapped
                .applyAsDouble(l);
    }

    default DoubleSupplier bind(LongSupplier i) {
        Objects.requireNonNull(i);
        return () -> applyAsDouble(i.getAsLong());
    }

}
