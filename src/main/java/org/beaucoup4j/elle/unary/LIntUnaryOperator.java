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
import java.util.function.IntUnaryOperator;

/**
 * Extend {@link IntUnaryOperator}.
 */
@FunctionalInterface
public interface LIntUnaryOperator extends IntUnaryOperator {

    static <T> LIntUnaryOperator of(IntUnaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LIntUnaryOperator ? (LIntUnaryOperator) wrapped : i -> wrapped.applyAsInt(i);
    }

    @Override
    default LIntUnaryOperator compose(IntUnaryOperator before) {
        return of(IntUnaryOperator.super.compose(before));
    }

    @Override
    default LIntUnaryOperator andThen(IntUnaryOperator after) {
        return of(IntUnaryOperator.super.andThen(after));
    }

    default IntSupplier bind(IntSupplier i) {
        Objects.requireNonNull(i);
        return () -> applyAsInt(i.getAsInt());
    }

}
