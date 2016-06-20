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
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

import org.beaucoup4j.elle.nullary.LBooleanSupplier;

/**
 * Extend {@link DoublePredicate}.
 */
@FunctionalInterface
public interface LDoublePredicate extends DoublePredicate {

    static LDoublePredicate of(DoublePredicate wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LDoublePredicate ? (LDoublePredicate) wrapped : d -> wrapped.test(d);
    }

    @Override
    default LDoublePredicate and(DoublePredicate other) {
        return of(DoublePredicate.super.and(other));
    }

    @Override
    default LDoublePredicate negate() {
        return of(DoublePredicate.super.negate());
    }

    @Override
    default LDoublePredicate or(DoublePredicate other) {
        return of(DoublePredicate.super.or(other));
    }

    default LBooleanSupplier bind(DoubleSupplier d) {
        Objects.requireNonNull(d);
        return () -> test(d.getAsDouble());
    }

}
