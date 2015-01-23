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
 * Function mapping a variable number of {@code double} arguments to a {@code double} result.
 */
@FunctionalInterface
public interface DoubleVarOperator {
    double applyAsDouble(double... d);

    static DoubleVarOperator min() {
        return d -> {
            if (d == null || d.length == 0) {
                return Double.POSITIVE_INFINITY;
            }
            final double[] sort = d.clone();
            Arrays.sort(sort);
            return sort[0];
        };
    }

    static DoubleVarOperator max() {
        return d -> {
            if (d == null || d.length == 0) {
                return Double.NEGATIVE_INFINITY;
            }
            final double[] sort = d.clone();
            Arrays.sort(sort);
            return sort[sort.length - 1];
        };
    }

}
