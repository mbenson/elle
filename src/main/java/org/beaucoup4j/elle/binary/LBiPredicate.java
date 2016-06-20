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
package org.beaucoup4j.elle.binary;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

import org.beaucoup4j.elle.nullary.LBooleanSupplier;
import org.beaucoup4j.elle.unary.LPredicate;

/**
 * Extend {@link BiPredicate}.
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface LBiPredicate<T, U> extends BiPredicate<T, U> {

    static <T, U> LBiPredicate<T, U> of(BiPredicate<T, U> wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LBiPredicate ? (LBiPredicate<T, U>) wrapped : (t, u) -> wrapped.test(t, u);
    }

    static <T, U> LBiPredicate<T, U> areSame() {
        return (t, u) -> t == u;
    }

    static <T, U> LBiPredicate<T, U> areEqual() {
        return (t, u) -> Objects.equals(t, u);
    }

    @Override
    default LBiPredicate<T, U> and(BiPredicate<? super T, ? super U> other) {
        return of(BiPredicate.super.and(other));
    }

    @Override
    default LBiPredicate<T, U> negate() {
        return of(BiPredicate.super.negate());
    }

    @Override
    default LBiPredicate<T, U> or(BiPredicate<? super T, ? super U> other) {
        return of(BiPredicate.super.or(other));
    }

    default LBiFunction<T, U, Boolean> asFunction() {
        return (t, u) -> Boolean.valueOf(test(t, u));
    }

    default LPredicate<U> bindLeft(Supplier<? extends T> t) {
        Objects.requireNonNull(t);
        return u -> test(t.get(), u);
    }

    default LPredicate<T> bindRight(Supplier<? extends U> u) {
        Objects.requireNonNull(u);
        return t -> test(t, u.get());
    }

    default LBooleanSupplier bind(Supplier<? extends T> t, Supplier<? extends U> u) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(u);
        return () -> test(t.get(), u.get());
    }

    default <G, H> LBiPredicate<G, H> compose(Function<? super G, ? extends T> leftBefore,
            Function<? super H, ? extends U> rightBefore) {
        Objects.requireNonNull(leftBefore);
        Objects.requireNonNull(rightBefore);
        return (g, h) -> test(leftBefore.apply(g), rightBefore.apply(h));
    }

}
