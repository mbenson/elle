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

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import org.beaucoup4j.elle.binary.LBiConsumer;
import org.beaucoup4j.elle.binary.LBiFunction;
import org.beaucoup4j.elle.binary.LBiPredicate;
import org.beaucoup4j.elle.binary.LDoubleBinaryOperator;
import org.beaucoup4j.elle.binary.LIntBinaryOperator;
import org.beaucoup4j.elle.binary.LLongBinaryOperator;
import org.beaucoup4j.elle.binary.LObjDoubleConsumer;
import org.beaucoup4j.elle.binary.LObjIntConsumer;
import org.beaucoup4j.elle.binary.LObjLongConsumer;
import org.beaucoup4j.elle.binary.LToDoubleBiFunction;
import org.beaucoup4j.elle.binary.LToIntBiFunction;
import org.beaucoup4j.elle.binary.LToLongBiFunction;
import org.beaucoup4j.elle.unary.LConsumer;
import org.beaucoup4j.elle.unary.LDoubleConsumer;
import org.beaucoup4j.elle.unary.LDoublePredicate;
import org.beaucoup4j.elle.unary.LDoubleUnaryOperator;
import org.beaucoup4j.elle.unary.LFunction;
import org.beaucoup4j.elle.unary.LIntConsumer;
import org.beaucoup4j.elle.unary.LIntPredicate;
import org.beaucoup4j.elle.unary.LIntUnaryOperator;
import org.beaucoup4j.elle.unary.LLongConsumer;
import org.beaucoup4j.elle.unary.LLongPredicate;
import org.beaucoup4j.elle.unary.LLongUnaryOperator;
import org.beaucoup4j.elle.unary.LPredicate;
import org.beaucoup4j.elle.unary.LToDoubleFunction;
import org.beaucoup4j.elle.unary.LToIntFunction;
import org.beaucoup4j.elle.unary.LToLongFunction;

/**
 * Extend {@link Runnable}.
 */
@FunctionalInterface
public interface LRunnable extends Runnable {

    static LRunnable of(Runnable wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof LRunnable ? (LRunnable) wrapped : () -> wrapped.run();
    }

    static LRunnable noop() {
        return () -> {
        };
    }

    default LRunnable after(Runnable previous) {
        return () -> {
            previous.run();
            run();
        };
    }

    default LRunnable andThen(Runnable after) {
        return () -> {
            run();
            after.run();
        };
    }

    default <T, U> LBiConsumer<T, U> asBiConsumer() {
        return (t, u) -> run();
    }

    default <T, U> LToDoubleBiFunction<T, U> asToDoubleBiFunction(DoubleSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsDouble();
        };
    }

    default <T, U> LToIntBiFunction<T, U> asToIntBiFunction(IntSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsInt();
        };
    }

    default <T, U> LToLongBiFunction<T, U> asToLongBiFunction(LongSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsLong();
        };
    }

    default <T, U, R> LBiFunction<T, U, R> asBiFunction(Supplier<? extends R> after) {
        return (t, u) -> {
            run();
            return after.get();
        };
    }

    default LDoubleBinaryOperator asBinaryOperator(DoubleSupplier after) {
        return (d1, Ld) -> {
            run();
            return after.getAsDouble();
        };
    }

    default LIntBinaryOperator asBinaryOperator(IntSupplier after) {
        return (i1, Li) -> {
            run();
            return after.getAsInt();
        };
    }

    default LLongBinaryOperator asBinaryOperator(LongSupplier after) {
        return (l1, Ll) -> {
            run();
            return after.getAsLong();
        };
    }

    default <T, U> LBiPredicate<T, U> asBiPredicate(BooleanSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsBoolean();
        };
    }

    default <T> LConsumer<T> asConsumer() {
        return t -> run();
    }

    default LDoubleConsumer asDoubleConsumer() {
        return d -> run();
    }

    default <T> LDoublePredicate asDoublePredicate(BooleanSupplier after) {
        return d -> {
            run();
            return after.getAsBoolean();
        };
    }

    default <T> LToDoubleFunction<T> asToDoubleFunction(DoubleSupplier after) {
        return t -> {
            run();
            return after.getAsDouble();
        };
    }

    default <T> LToIntFunction<T> asToIntFunction(IntSupplier after) {
        return t -> {
            run();
            return after.getAsInt();
        };
    }

    default <T> LToLongFunction<T> asToLongFunction(LongSupplier after) {
        return t -> {
            run();
            return after.getAsLong();
        };
    }

    default <T, R> LFunction<T, R> asFunction(Supplier<? extends R> after) {
        return t -> {
            run();
            return after.get();
        };
    }

    default LIntConsumer asIntConsumer() {
        return i -> run();
    }

    default <T> LIntPredicate asIntPredicate(BooleanSupplier after) {
        return i -> {
            run();
            return after.getAsBoolean();
        };
    }

    default LLongConsumer asLongConsumer() {
        return l -> run();
    }

    default <T> LLongPredicate asLongPredicate(BooleanSupplier after) {
        return l -> {
            run();
            return after.getAsBoolean();
        };
    }

    default <T> LObjDoubleConsumer<T> asObjDoubleConsumer() {
        return (t, d) -> run();
    }

    default <T> LObjIntConsumer<T> asObjIntConsumer() {
        return (t, i) -> run();
    }

    default <T> LObjLongConsumer<T> asObjLongConsumer() {
        return (t, l) -> run();
    }

    default <T> LPredicate<T> asPredicate(BooleanSupplier after) {
        return t -> {
            run();
            return after.getAsBoolean();
        };
    }

    default LDoubleUnaryOperator asDoubleUnaryOperator(DoubleSupplier after) {
        return d -> {
            run();
            return after.getAsDouble();
        };
    }

    default LIntUnaryOperator asIntUnaryOperator(IntSupplier after) {
        return i -> {
            run();
            return after.getAsInt();
        };
    }

    default LLongUnaryOperator asLongUnaryOperator(LongSupplier after) {
        return l -> {
            run();
            return after.getAsLong();
        };
    }

}
