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

/**
 * Varargs consumer.
 *
 * @param <E>
 */
@FunctionalInterface
public interface VarConsumer<E> {
    static <E> VarConsumer<E> noop() {
        return e -> {
        };
    }

    void accept(@SuppressWarnings("unchecked") E... elements);

    default VarConsumer<E> andThen(VarConsumer<? super E> after) {
        return e -> {
            accept(e);
            after.accept(e);
        };
    }
}
