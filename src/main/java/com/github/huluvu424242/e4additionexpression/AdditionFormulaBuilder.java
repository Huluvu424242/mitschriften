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

public abstract class AdditionFormulaBuilder implements FormulaStates.Zahl1{

    FormulaContext formulaContext;

    private AdditionFormulaBuilder(){
        this.formulaContext =new FormulaContext(0, new StringBuilder());
    }

    public static FormulaStates.Zahl1 builder() {
        final AdditionFormulaBuilder builder = new AdditionFormulaBuilder() {
            // will be never called
            // because is internal overridden by lambda in return of builder() method
            public FormulaContext build() {
                throw new UnsupportedOperationException("Call of method forbidden");
            }
        };
        // create new interface instance
        return () -> builder.formulaContext;
    }

}
