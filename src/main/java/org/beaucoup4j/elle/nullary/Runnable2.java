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

import org.beaucoup4j.elle.binary.BiConsumer2;
import org.beaucoup4j.elle.binary.BiFunction2;
import org.beaucoup4j.elle.binary.BiPredicate2;
import org.beaucoup4j.elle.binary.DoubleBinaryOperator2;
import org.beaucoup4j.elle.binary.IntBinaryOperator2;
import org.beaucoup4j.elle.binary.LongBinaryOperator2;
import org.beaucoup4j.elle.binary.ObjDoubleConsumer2;
import org.beaucoup4j.elle.binary.ObjIntConsumer2;
import org.beaucoup4j.elle.binary.ObjLongConsumer2;
import org.beaucoup4j.elle.binary.ToDoubleBiFunction2;
import org.beaucoup4j.elle.binary.ToIntBiFunction2;
import org.beaucoup4j.elle.binary.ToLongBiFunction2;
import org.beaucoup4j.elle.unary.Consumer2;
import org.beaucoup4j.elle.unary.DoubleConsumer2;
import org.beaucoup4j.elle.unary.DoublePredicate2;
import org.beaucoup4j.elle.unary.DoubleUnaryOperator2;
import org.beaucoup4j.elle.unary.Function2;
import org.beaucoup4j.elle.unary.IntConsumer2;
import org.beaucoup4j.elle.unary.IntPredicate2;
import org.beaucoup4j.elle.unary.IntUnaryOperator2;
import org.beaucoup4j.elle.unary.LongConsumer2;
import org.beaucoup4j.elle.unary.LongPredicate2;
import org.beaucoup4j.elle.unary.LongUnaryOperator2;
import org.beaucoup4j.elle.unary.Predicate2;
import org.beaucoup4j.elle.unary.ToDoubleFunction2;
import org.beaucoup4j.elle.unary.ToIntFunction2;
import org.beaucoup4j.elle.unary.ToLongFunction2;

/**
 * Extend {@link Runnable}.
 */
@FunctionalInterface
public interface Runnable2 extends Runnable {

    static Runnable2 of(Runnable wrapped) {
        Objects.requireNonNull(wrapped);
        return wrapped instanceof Runnable2 ? (Runnable2) wrapped : () -> wrapped.run();
    }

    static Runnable2 noop() {
        return () -> {
        };
    }

    default Runnable2 after(Runnable previous) {
        return () -> {
            previous.run();
            run();
        };
    }

    default Runnable2 andThen(Runnable after) {
        return () -> {
            run();
            after.run();
        };
    }

    default <T, U> BiConsumer2<T, U> asBiConsumer() {
        return (t, u) -> run();
    }

    default <T, U> ToDoubleBiFunction2<T, U> asToDoubleBiFunction(DoubleSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsDouble();
        };
    }

    default <T, U> ToIntBiFunction2<T, U> asToIntBiFunction(IntSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsInt();
        };
    }

    default <T, U> ToLongBiFunction2<T, U> asToLongBiFunction(LongSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsLong();
        };
    }

    default <T, U, R> BiFunction2<T, U, R> asBiFunction(Supplier<? extends R> after) {
        return (t, u) -> {
            run();
            return after.get();
        };
    }

    default DoubleBinaryOperator2 asBinaryOperator(DoubleSupplier after) {
        return (d1, d2) -> {
            run();
            return after.getAsDouble();
        };
    }

    default IntBinaryOperator2 asBinaryOperator(IntSupplier after) {
        return (i1, i2) -> {
            run();
            return after.getAsInt();
        };
    }

    default LongBinaryOperator2 asBinaryOperator(LongSupplier after) {
        return (l1, l2) -> {
            run();
            return after.getAsLong();
        };
    }

    default <T, U> BiPredicate2<T, U> asBiPredicate(BooleanSupplier after) {
        return (t, u) -> {
            run();
            return after.getAsBoolean();
        };
    }

    default <T> Consumer2<T> asConsumer() {
        return t -> run();
    }

    default DoubleConsumer2 asDoubleConsumer() {
        return d -> run();
    }

    default <T> DoublePredicate2 asDoublePredicate(BooleanSupplier after) {
        return d -> {
            run();
            return after.getAsBoolean();
        };
    }

    default <T> ToDoubleFunction2<T> asToDoubleFunction(DoubleSupplier after) {
        return t -> {
            run();
            return after.getAsDouble();
        };
    }

    default <T> ToIntFunction2<T> asToIntFunction(IntSupplier after) {
        return t -> {
            run();
            return after.getAsInt();
        };
    }

    default <T> ToLongFunction2<T> asToLongFunction(LongSupplier after) {
        return t -> {
            run();
            return after.getAsLong();
        };
    }

    default <T, R> Function2<T, R> asFunction(Supplier<? extends R> after) {
        return t -> {
            run();
            return after.get();
        };
    }

    default IntConsumer2 asIntConsumer() {
        return i -> run();
    }

    default <T> IntPredicate2 asIntPredicate(BooleanSupplier after) {
        return i -> {
            run();
            return after.getAsBoolean();
        };
    }

    default LongConsumer2 asLongConsumer() {
        return l -> run();
    }

    default <T> LongPredicate2 asLongPredicate(BooleanSupplier after) {
        return l -> {
            run();
            return after.getAsBoolean();
        };
    }

    default <T> ObjDoubleConsumer2<T> asObjDoubleConsumer() {
        return (t, d) -> run();
    }

    default <T> ObjIntConsumer2<T> asObjIntConsumer() {
        return (t, i) -> run();
    }

    default <T> ObjLongConsumer2<T> asObjLongConsumer() {
        return (t, l) -> run();
    }

    default <T> Predicate2<T> asPredicate(BooleanSupplier after) {
        return t -> {
            run();
            return after.getAsBoolean();
        };
    }

    default DoubleUnaryOperator2 asDoubleUnaryOperator(DoubleSupplier after) {
        return d -> {
            run();
            return after.getAsDouble();
        };
    }

    default IntUnaryOperator2 asIntUnaryOperator(IntSupplier after) {
        return i -> {
            run();
            return after.getAsInt();
        };
    }

    default LongUnaryOperator2 asLongUnaryOperator(LongSupplier after) {
        return l -> {
            run();
            return after.getAsLong();
        };
    }

}
