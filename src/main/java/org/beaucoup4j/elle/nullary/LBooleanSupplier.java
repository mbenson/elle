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

import org.beaucoup4j.elle.binary.LBiPredicate;
import org.beaucoup4j.elle.unary.LPredicate;

/**
 * Extend {@link BooleanSupplier}.
 */
@FunctionalInterface
public interface LBooleanSupplier extends BooleanSupplier {

    static LBooleanSupplier of(BooleanSupplier wrapped) {
        return wrapped instanceof LBooleanSupplier ? (LBooleanSupplier) wrapped : () -> wrapped.getAsBoolean();
    }

    default <T> LPredicate<T> asPredicate() {
        return t -> getAsBoolean();
    }

    default <T, U> LBiPredicate<T, U> asBiPredicate() {
        return (t, u) -> getAsBoolean();
    }

    default LBooleanSupplier negate() {
        return () -> !getAsBoolean();
    }
}
