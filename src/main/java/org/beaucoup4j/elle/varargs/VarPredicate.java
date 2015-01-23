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
package org.beaucoup4j.elle.varargs;

import java.util.Objects;

/**
 * Varargs predicate.
 *
 * @param <E>
 */
@FunctionalInterface
public interface VarPredicate<E> {

    boolean test(@SuppressWarnings("unchecked") E... elements);

    default VarPredicate<E> negate() {
        return e -> !this.test(e);
    }

    default VarPredicate<E> and(VarPredicate<? super E> other) {
        Objects.requireNonNull(other);
        return e -> test(e) && other.test(e);
    }

    default VarPredicate<E> or(VarPredicate<? super E> other) {
        Objects.requireNonNull(other);
        return e -> test(e) || other.test(e);
    }
}
