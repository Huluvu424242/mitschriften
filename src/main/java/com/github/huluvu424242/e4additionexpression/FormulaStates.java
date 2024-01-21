package com.github.huluvu424242.e4additionexpression;

/*-
 * #%L
 * fluent-builder.example
 * %%
 * Copyright (C) 2023 - 2024 Huluvu424242
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

public interface FormulaStates {

    FormulaContext build();

    public interface Zahl1 extends FormulaStates {

        default FormulaStates.Zahl1 digit(int digit) {
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("wrong digit:" + digit);
            }
            final FormulaContext context = build();
            // append digit to current number
            context.zahlBuilder().append(digit);
            return this;
        }

        default FormulaStates.Plus plus() {
            final FormulaContext context = build();
            final int zahl = Integer.parseInt(context.zahlBuilder().toString());
            FormulaContext contextNeu = new FormulaContext(zahl, new StringBuilder());
            return () -> contextNeu;
        }
    }

    public interface Plus extends FormulaStates {
        default FormulaStates.Zahl digit(int digit) {
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("wrong digit:" + digit);
            }
            final FormulaContext context = build();
            // append digit to current number
            context.zahlBuilder().append(digit);
            return this::build;
        }
    }

    public interface Zahl extends FormulaStates {

        default FormulaStates.Zahl digit(int digit) {
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("wrong digit:" + digit);
            }
            final FormulaContext context = build();
            // append digit to current number
            context.zahlBuilder().append(digit);
            return this;
        }

        default FormulaStates.Plus plus() {
            final FormulaContext context = build();
            final int zahl = Integer.parseInt(context.zahlBuilder().toString());
            FormulaContext contextNeu = new FormulaContext(context.summe() + zahl, new StringBuilder());
            return () -> contextNeu;
        }

        default FormulaStates.Final equals() {
            final FormulaContext context = build();
            final int zahl = Integer.parseInt(context.zahlBuilder().toString());
            FormulaContext contextNeu = new FormulaContext(context.summe() + zahl, new StringBuilder());
            return () -> contextNeu;
        }
    }

    interface Final extends FormulaStates {

    }
}
