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
package org.beaucoup4j.elle.nullary;

import java.util.function.BooleanSupplier;

import org.beaucoup4j.elle.binary.BiPredicate2;
import org.beaucoup4j.elle.unary.Predicate2;

/**
 * Extend {@link BooleanSupplier}.
 */
@FunctionalInterface
public interface BooleanSupplier2 extends BooleanSupplier {

    static BooleanSupplier2 of(BooleanSupplier wrapped) {
        return wrapped instanceof BooleanSupplier2 ? (BooleanSupplier2) wrapped : () -> wrapped.getAsBoolean();
    }

    default <T> Predicate2<T> asPredicate() {
        return t -> getAsBoolean();
    }

    default <T, U> BiPredicate2<T, U> asBiPredicate() {
        return (t, u) -> getAsBoolean();
    }

    default BooleanSupplier2 negate() {
        return () -> !getAsBoolean();
    }
}
