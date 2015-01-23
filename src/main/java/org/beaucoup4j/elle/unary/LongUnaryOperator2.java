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
import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;

/**
 * Extend {@link LongUnaryOperator}.
 */
@FunctionalInterface
public interface LongUnaryOperator2 extends LongUnaryOperator {

    static <T> LongUnaryOperator2 of(LongUnaryOperator wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LongUnaryOperator2 ? (LongUnaryOperator2) wrapped : l -> wrapped.applyAsLong(l);
    }

    @Override
    default LongUnaryOperator compose(LongUnaryOperator before) {
        return of(LongUnaryOperator.super.compose(before));
    }

    @Override
    default LongUnaryOperator andThen(LongUnaryOperator after) {
        return of(LongUnaryOperator.super.andThen(after));
    }

    default LongSupplier bind(LongSupplier l) {
        Objects.requireNonNull(l);
        return () -> applyAsLong(l.getAsLong());
    }

}
