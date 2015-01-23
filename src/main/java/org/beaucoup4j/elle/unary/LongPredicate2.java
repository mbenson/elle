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
import java.util.function.BooleanSupplier;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;

/**
 * Extend {@link LongPredicate}.
 */
@FunctionalInterface
public interface LongPredicate2 extends LongPredicate {

    static LongPredicate2 of(LongPredicate wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LongPredicate2 ? (LongPredicate2) wrapped : l -> wrapped.test(l);
    }

    @Override
    default LongPredicate and(LongPredicate other) {
        return of(LongPredicate.super.and(other));
    }

    @Override
    default LongPredicate negate() {
        return of(LongPredicate.super.negate());
    }

    @Override
    default LongPredicate or(LongPredicate other) {
        return of(LongPredicate.super.or(other));
    }

    default BooleanSupplier bind(LongSupplier l) {
        Objects.requireNonNull(l);
        return () -> test(l.getAsLong());
    }

}
