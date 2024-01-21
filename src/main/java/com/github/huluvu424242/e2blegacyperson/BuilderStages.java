package com.github.huluvu424242.e2blegacyperson;

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

import java.time.LocalDate;

// NewStage -> BornStage -> RegisterStage -> RegisteredStage
public interface BuilderStages {
    LegacyPerson build();

    interface NewStage extends BuilderStages {

        default BornStage birthWeight(double birthWeight) {
            final LegacyPerson person = build();
            if (birthWeight < 0.1) throw new IllegalArgumentException("birthWeight must be greather then 100g !");
            person.birthWeight = birthWeight;
            // unpleasant but necessary
            return this::build;
        }

    }

    interface BornStage extends BuilderStages {

        default RegisterStage birthday(LocalDate birthday) {
            final LegacyPerson person = build();
            if (birthday == null) throw new IllegalArgumentException("birthday must be not null");
            person.birthday = birthday;
            // unpleasant but necessary
            return this::build;
        }

    }

    interface RegisterStage extends BuilderStages {

        default RegisterStage firstName(String firstName) {
            final LegacyPerson person = build();
            if (firstName == null) throw new IllegalArgumentException("firstName must be not null");
            person.firstName = firstName;
            // possible because no stage change
            return this;
        }

        default RegisterStage sureName(String sureName) {
            final LegacyPerson person = build();
            if (sureName == null) throw new IllegalArgumentException("sureName must be not null");
            person.sureName = sureName;
            // possible because no stage change
            return this;
        }

        default RegisteredStage register(int registerNumber) {
            final LegacyPerson person = build();
            if (registerNumber < 1) throw new IllegalArgumentException("registerNumber must be greather then 0!");
            person.registerNumber = registerNumber;
            // unpleasant but necessary
            return this::build;
        }
    }

    interface RegisteredStage extends BuilderStages {
        // explizit build() definition not necessary because already defined in BuilderStages
    }

}
