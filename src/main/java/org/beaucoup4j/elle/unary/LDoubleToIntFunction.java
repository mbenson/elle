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
import java.util.function.IntSupplier;

/**
 * Extend {@link DoubleToIntFunction}.
 */
@FunctionalInterface
public interface LDoubleToIntFunction extends DoubleToIntFunction {

    static LDoubleToIntFunction of(DoubleToIntFunction wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LDoubleToIntFunction ? (LDoubleToIntFunction) wrapped : d -> wrapped.applyAsInt(d);
    }

    default IntSupplier bind(DoubleSupplier d) {
        Objects.requireNonNull(d);
        return () -> applyAsInt(d.getAsDouble());
    }

}
