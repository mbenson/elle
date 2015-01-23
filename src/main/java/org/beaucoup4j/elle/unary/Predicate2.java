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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Extend {@link Predicate}.
 *
 * @param <T>
 */
@FunctionalInterface
public interface Predicate2<T> extends Predicate<T> {

    static <T> Predicate2<T> of(Predicate<T> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof Predicate2 ? (Predicate2<T>) wrapped : t -> wrapped.test(t);
    }

    @Override
    default Predicate<T> and(Predicate<? super T> other) {
        return of(Predicate.super.and(other));
    }

    @Override
    default Predicate<T> negate() {
        return of(Predicate.super.negate());
    }

    @Override
    default Predicate<T> or(Predicate<? super T> other) {
        return of(Predicate.super.or(other));
    }

    default BooleanSupplier bind(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return () -> test(t.get());
    }

    default <G> Predicate2<G> compose(Function<? super G, ? extends T> before) {
        return g -> test(before.apply(g));
    }

}
