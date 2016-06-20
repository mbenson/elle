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
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;

import org.beaucoup4j.elle.nullary.LBooleanSupplier;

/**
 * Extend {@link LongPredicate}.
 */
@FunctionalInterface
public interface LLongPredicate extends LongPredicate {

    static LLongPredicate of(LongPredicate wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LLongPredicate ? (LLongPredicate) wrapped : l -> wrapped.test(l);
    }

    @Override
    default LLongPredicate and(LongPredicate other) {
        return of(LongPredicate.super.and(other));
    }

    @Override
    default LLongPredicate negate() {
        return of(LongPredicate.super.negate());
    }

    @Override
    default LLongPredicate or(LongPredicate other) {
        return of(LongPredicate.super.or(other));
    }

    default LBooleanSupplier bind(LongSupplier l) {
        Objects.requireNonNull(l);
        return () -> test(l.getAsLong());
    }

}
