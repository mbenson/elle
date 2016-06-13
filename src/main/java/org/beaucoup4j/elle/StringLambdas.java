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

import java.util.Locale;
import java.util.function.Supplier;

import org.beaucoup4j.elle.binary.BiFunction2;
import org.beaucoup4j.elle.binary.BiPredicate2;
import org.beaucoup4j.elle.binary.ToIntBiFunction2;
import org.beaucoup4j.elle.unary.Function2;
import org.beaucoup4j.elle.unary.Predicate2;

/**
 * Utility class that supplies various {@link String}-related function implementations.
 */
public class StringLambdas {
    // ----------------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------------

    private static final String EMPTY_STRING = "";
    private static final String ELLIPSIS = "...";

    // ----------------------------------------------------------------------------------------------------------------------
    // Static Methods
    // ----------------------------------------------------------------------------------------------------------------------

    public static Function2<String, String> abbreviate(final int maxWidth) {
        return abbreviate(0, maxWidth);
    }

    public static Function2<String, String> abbreviate(final int offset, final int maxWidth) {
        final int elen = ELLIPSIS.length();

        if (maxWidth <= elen) {
            throw new IllegalArgumentException("minimum abbreviation width is " + (elen + 1));
        }
        if (offset > 0 && maxWidth <= (elen * 2)) {
            throw new IllegalArgumentException("minimum abbreviation width with offset is " + (elen * 2 + 1));
        }
        return t -> {
            if (t == null || t.length() <= maxWidth) {
                return t;
            }
            final StringBuilder buf = new StringBuilder();

            if (offset > 0) {
                buf.append(ELLIPSIS);
                if (offset + maxWidth > t.length()) {
                    buf.append(t.substring(t.length() - (maxWidth - elen)));
                } else {
                    buf.append(t.substring(offset, maxWidth - elen * 2)).append(ELLIPSIS);
                }
            } else {
                buf.append(t.substring(0, maxWidth - elen)).append(ELLIPSIS);
            }
            return buf.toString();
        };
    }

    public static Function2<String, String> abbreviateMiddle(String middle, final int length) {
        final String mid = middle == null ? "" : middle;
        final int content = length - mid.length();
        if (content < 2) {
            throw new IllegalArgumentException(String.format("length must accommodate \"%s\" + 2", mid));
        }
        return t -> {
            if (t == null || t.length() <= length) {
                return t;
            }
            final StringBuilder buf = new StringBuilder(mid);
            final int end = content / 2;
            buf.append(t.substring(t.length() - end));
            buf.insert(0, t.substring(0, content - end));
            return buf.toString();
        };
    }

    public static BiPredicate2<CharSequence, CharSequence> contains() {
        return (seq, subseq) -> seq != null && subseq != null && seq.toString().indexOf(subseq.toString()) >= 0;
    }

    public static BiPredicate2<CharSequence, CharSequence> containsIgnoreCase() {
        return (seq, subseq) -> seq != null && subseq != null
                && contains().test(lowerCase().apply(seq.toString()), lowerCase().apply(seq.toString()));
    }

    public static BiFunction2<String, String, String> difference() {
        return (t, u) -> {
            if (u == null) {
                return null;
            }
            final int index = indexOfDifference().applyAsInt(t, u);
            return index < 0 ? EMPTY_STRING : u.substring(index);
        };
    }

    public static ToIntBiFunction2<CharSequence, CharSequence> indexOfDifference() {
        return (t, u) -> {
            if (u == null) {
                return -1;
            }
            int i = 0;
            for (; i < u.length(); i++) {
                if (t == null || t.length() <= i || t.charAt(i) != u.charAt(i)) {
                    return Integer.valueOf(i);
                }
            }
            return -1;
        };
    }

    public static Function2<CharSequence, String> lowerCase() {
        return s -> s == null ? null : s.toString().toLowerCase();
    }

    public static Function2<CharSequence, String> lowerCase(final Locale locale) {
        return s -> s == null ? null : s.toString().toLowerCase(locale);
    }

    public static Function2<CharSequence, String> upperCase() {
        return s -> s == null ? null : s.toString().toUpperCase();
    }

    public static Function2<CharSequence, String> upperCase(final Locale locale) {
        return s -> s == null ? null : s.toString().toUpperCase(locale);
    }

    public static BiPredicate2<CharSequence, CharSequence> startsWith() {
        return (s, prefix) -> s == null ? prefix == null : prefix != null && s.toString().startsWith(prefix.toString());
    }

    public static BiPredicate2<CharSequence, CharSequence> startsWithIgnoreCase() {
        return startsWith().compose(lowerCase(), lowerCase());
    }

    public static Supplier<String> empty() {
        return () -> EMPTY_STRING;
    }

    public static Predicate2<CharSequence> isEmpty() {
        return s -> s == null || s.length() == 0;
    }

    public static Predicate2<CharSequence> isBlank() {
        return isEmpty().compose(trim());
    }

    public static Function2<CharSequence, String> trim() {
        return s -> s == null ? null : s.toString().trim();
    }

    public static Function2<CharSequence, Integer> length() {
        return s -> s == null ? 0 : s.length();
    }

    public static Function2<String, String> reverse() {
        return s -> s == null ? null : new StringBuilder(s).reverse().toString();
    }

    // ----------------------------------------------------------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------------------------------------------------------

    private StringLambdas() {
    }
}
