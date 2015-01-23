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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * VarFunction with same element and result type.
 *
 * @param <E>
 */
@FunctionalInterface
public interface VarOperator<E> extends VarFunction<E, E> {
    static <E extends Comparable<E>> VarOperator<E> min() {
        return minBy(Comparator.naturalOrder());
    }

    static <E> VarOperator<E> minBy(Comparator<? super E> cmp) {
        Objects.requireNonNull(cmp);
        return e -> {
            if (e == null || e.length == 0) {
                return null;
            }
            final E[] sort = e.clone();
            Arrays.sort(sort, cmp);
            return sort[0];
        };
    }

    static <E extends Comparable<E>> VarOperator<E> max() {
        return maxBy(Comparator.naturalOrder());
    }

    static <E> VarOperator<E> maxBy(Comparator<? super E> cmp) {
        Objects.requireNonNull(cmp);
        return minBy(cmp.reversed());
    }

}
