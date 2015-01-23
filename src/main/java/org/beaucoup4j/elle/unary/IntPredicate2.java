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
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;

/**
 * Extend {@link IntPredicate}.
 */
@FunctionalInterface
public interface IntPredicate2 extends IntPredicate {

    static IntPredicate2 of(IntPredicate wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof IntPredicate2 ? (IntPredicate2) wrapped : i -> wrapped.test(i);
    }

    @Override
    default IntPredicate and(IntPredicate other) {
        return of(IntPredicate.super.and(other));
    }

    @Override
    default IntPredicate negate() {
        return of(IntPredicate.super.negate());
    }

    @Override
    default IntPredicate or(IntPredicate other) {
        return of(IntPredicate.super.or(other));
    }

    default BooleanSupplier bind(IntSupplier i) {
        Objects.requireNonNull(i);
        return () -> test(i.getAsInt());
    }

}
