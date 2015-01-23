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

/**
 * Function mapping a variable number of {@code int} arguments to a {@code int} result.
 */
@FunctionalInterface
public interface IntVarOperator {
    int applyAsInt(int... d);

    static IntVarOperator min() {
        return i -> {
            if (i == null || i.length == 0) {
                return Integer.MAX_VALUE;
            }
            final int[] sort = i.clone();
            Arrays.sort(sort);
            return sort[0];
        };
    }

    static IntVarOperator max() {
        return i -> {
            if (i == null || i.length == 0) {
                return Integer.MIN_VALUE;
            }
            final int[] sort = i.clone();
            Arrays.sort(sort);
            return sort[sort.length - 1];
        };
    }

}
