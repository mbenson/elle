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
package org.beaucoup4j.elle;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

import org.beaucoup4j.elle.binary.LBiConsumer;
import org.beaucoup4j.elle.binary.LBiFunction;
import org.beaucoup4j.elle.binary.LBiPredicate;
import org.beaucoup4j.elle.binary.LBinaryOperator;
import org.beaucoup4j.elle.binary.LDoubleBinaryOperator;
import org.beaucoup4j.elle.binary.LIntBinaryOperator;
import org.beaucoup4j.elle.binary.LLongBinaryOperator;
import org.beaucoup4j.elle.binary.LObjDoubleConsumer;
import org.beaucoup4j.elle.binary.LObjIntConsumer;
import org.beaucoup4j.elle.binary.LObjLongConsumer;
import org.beaucoup4j.elle.binary.LToDoubleBiFunction;
import org.beaucoup4j.elle.binary.LToIntBiFunction;
import org.beaucoup4j.elle.binary.LToLongBiFunction;
import org.beaucoup4j.elle.nullary.LBooleanSupplier;
import org.beaucoup4j.elle.nullary.LRunnable;
import org.beaucoup4j.elle.unary.LConsumer;
import org.beaucoup4j.elle.unary.LDoubleConsumer;
import org.beaucoup4j.elle.unary.LDoubleFunction;
import org.beaucoup4j.elle.unary.LDoublePredicate;
import org.beaucoup4j.elle.unary.LDoubleToIntFunction;
import org.beaucoup4j.elle.unary.LDoubleToLongFunction;
import org.beaucoup4j.elle.unary.LDoubleUnaryOperator;
import org.beaucoup4j.elle.unary.LFunction;
import org.beaucoup4j.elle.unary.LIntConsumer;
import org.beaucoup4j.elle.unary.LIntFunction;
import org.beaucoup4j.elle.unary.LIntPredicate;
import org.beaucoup4j.elle.unary.LIntToDoubleFunction;
import org.beaucoup4j.elle.unary.LIntToLongFunction;
import org.beaucoup4j.elle.unary.LIntUnaryOperator;
import org.beaucoup4j.elle.unary.LLongConsumer;
import org.beaucoup4j.elle.unary.LLongFunction;
import org.beaucoup4j.elle.unary.LLongPredicate;
import org.beaucoup4j.elle.unary.LLongToDoubleFunction;
import org.beaucoup4j.elle.unary.LLongToIntFunction;
import org.beaucoup4j.elle.unary.LLongUnaryOperator;
import org.beaucoup4j.elle.unary.LPredicate;
import org.beaucoup4j.elle.unary.LToDoubleFunction;
import org.beaucoup4j.elle.unary.LToIntFunction;
import org.beaucoup4j.elle.unary.LToLongFunction;
import org.beaucoup4j.elle.unary.LUnaryOperator;

/**
 * API entry point supporting fluent upgrading from a basic functional interface to the
 * corresponding enhanced Elle interface. This is not useful where actual lambda expressions
 * are specified, but may be helpful where an implementation of a functional interface is
 * already available.
 */
public final class Elle {

    public static <T, U> LBiConsumer<T, U> biConsumer(BiConsumer<T, U> toWrap) {
        return LBiConsumer.of(toWrap);
    }

    public static <T, U, R> LBiFunction<T, U, R> biFunction(BiFunction<T, U, R> toWrap) {
        return LBiFunction.of(toWrap);
    }

    public static <T> LBinaryOperator<T> binaryOperator(BinaryOperator<T> toWrap) {
        return LBinaryOperator.of(toWrap);
    }

    public static <T, U> LBiPredicate<T, U> biPredicate(BiPredicate<T, U> toWrap) {
        return LBiPredicate.of(toWrap);
    }

    public static LDoubleBinaryOperator doubleBinaryOperator(DoubleBinaryOperator toWrap) {
        return LDoubleBinaryOperator.of(toWrap);
    }

    public static LIntBinaryOperator intBinaryOperator(IntBinaryOperator toWrap) {
        return LIntBinaryOperator.of(toWrap);
    }

    public static LLongBinaryOperator longBinaryOperator(LongBinaryOperator toWrap) {
        return LLongBinaryOperator.of(toWrap);
    }

    public static <T> LObjDoubleConsumer<T> objDoubleConsumer(ObjDoubleConsumer<T> toWrap) {
        return LObjDoubleConsumer.of(toWrap);
    }

    public static <T> LObjIntConsumer<T> objIntConsumer(ObjIntConsumer<T> toWrap) {
        return LObjIntConsumer.of(toWrap);
    }

    public static <T> LObjLongConsumer<T> objLongConsumer(ObjLongConsumer<T> toWrap) {
        return LObjLongConsumer.of(toWrap);
    }

    public static <T, U> LToDoubleBiFunction<T, U> toDoubleBiFunction(ToDoubleBiFunction<T, U> toWrap) {
        return LToDoubleBiFunction.of(toWrap);
    }

    public static <T, U> LToIntBiFunction<T, U> toIntBiFunction(ToIntBiFunction<T, U> toWrap) {
        return LToIntBiFunction.of(toWrap);
    }

    public static <T, U> LToLongBiFunction<T, U> toLongBiFunction(ToLongBiFunction<T, U> toWrap) {
        return LToLongBiFunction.of(toWrap);
    }

    public static LBooleanSupplier booleanSupplier(BooleanSupplier toWrap) {
        return LBooleanSupplier.of(toWrap);
    }

    public static LRunnable runnable(Runnable toWrap) {
        return LRunnable.of(toWrap);
    }

    public static <T> LConsumer<T> consumer(Consumer<T> toWrap) {
        return LConsumer.of(toWrap);
    }

    public static LDoubleConsumer doubleConsumer(DoubleConsumer toWrap) {
        return LDoubleConsumer.of(toWrap);
    }

    public static <R> LDoubleFunction<R> doubleFunction(DoubleFunction<R> toWrap) {
        return LDoubleFunction.of(toWrap);
    }

    public static LDoublePredicate doublePredicate(DoublePredicate toWrap) {
        return LDoublePredicate.of(toWrap);
    }

    public static LDoubleToIntFunction doubleToIntFunction(DoubleToIntFunction toWrap) {
        return LDoubleToIntFunction.of(toWrap);
    }

    public static LDoubleToLongFunction doubleToLongFunction(DoubleToLongFunction toWrap) {
        return LDoubleToLongFunction.of(toWrap);
    }

    public static LDoubleUnaryOperator doubleUnaryOperator(DoubleUnaryOperator toWrap) {
        return LDoubleUnaryOperator.of(toWrap);
    }

    public static <T, R> LFunction<T, R> function(Function<T, R> toWrap) {
        return LFunction.of(toWrap);
    }

    public static LIntConsumer intConsumer(IntConsumer toWrap) {
        return LIntConsumer.of(toWrap);
    }

    public static <R> LIntFunction<R> intFunction(IntFunction<R> toWrap) {
        return LIntFunction.of(toWrap);
    }

    public static LIntPredicate intPredicate(IntPredicate toWrap) {
        return LIntPredicate.of(toWrap);
    }

    public static LIntToDoubleFunction intToDoubleFunction(IntToDoubleFunction toWrap) {
        return LIntToDoubleFunction.of(toWrap);
    }

    public static LIntToLongFunction intToLongFunction(IntToLongFunction toWrap) {
        return LIntToLongFunction.of(toWrap);
    }

    public static LIntUnaryOperator intUnaryOperator(IntUnaryOperator toWrap) {
        return LIntUnaryOperator.of(toWrap);
    }

    public static LLongConsumer longConsumer(LongConsumer toWrap) {
        return LLongConsumer.of(toWrap);
    }

    public static <R> LLongFunction<R> longFunction(LongFunction<R> toWrap) {
        return LLongFunction.of(toWrap);
    }

    public static LLongPredicate longPredicate(LongPredicate toWrap) {
        return LLongPredicate.of(toWrap);
    }

    public static LLongToDoubleFunction longToDoubleFunction(LongToDoubleFunction toWrap) {
        return LLongToDoubleFunction.of(toWrap);
    }

    public static LLongToIntFunction longToIntFunction(LongToIntFunction toWrap) {
        return LLongToIntFunction.of(toWrap);
    }

    public static LLongUnaryOperator longUnaryOperator(LongUnaryOperator toWrap) {
        return LLongUnaryOperator.of(toWrap);
    }

    public static <T> LPredicate<T> predicate(Predicate<T> toWrap) {
        return LPredicate.of(toWrap);
    }

    public static <T> LToDoubleFunction<T> toDoubleFunction(ToDoubleFunction<T> toWrap) {
        return LToDoubleFunction.of(toWrap);
    }

    public static <T> LToIntFunction<T> toIntFunction(ToIntFunction<T> toWrap) {
        return LToIntFunction.of(toWrap);
    }

    public static <T> LToLongFunction<T> toLongFunction(ToLongFunction<T> toWrap) {
        return LToLongFunction.of(toWrap);
    }

    public static <T> LUnaryOperator<T> unaryOperator(UnaryOperator<T> toWrap) {
        return LUnaryOperator.of(toWrap);
    }

    private Elle() {
    }
}
