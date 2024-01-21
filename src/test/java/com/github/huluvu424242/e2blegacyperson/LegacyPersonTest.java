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


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LegacyPersonTest {


    LocalDate now1;
    LocalDate now2;

    @BeforeEach
    void setUp() {
        this.now1 = LocalDate.now();
        this.now2 = LocalDate.now();
    }

    @Test
    void getPersonNoUnsupportedOpertationExceptionIsRised() {
        final BuilderStages.NewStage builder1 = LegacyPersonBuilder.builder();
        final LegacyPerson person = builder1.build();
        assertNotNull(person);
        assertEquals(0.0, person.getBirthWeight());
        assertNull(person.getBirthday());
        assertNull(person.getFirstName());
        assertNull(person.getSureName());
        assertEquals(0, person.getRegisterNumber());
    }

    @Test
    void createPersonNew() {
        final LegacyPerson person = LegacyPersonBuilder.builder()
                .birthWeight(0.7)
                .birthday(now1)
                .firstName("Arno")
                .sureName("Nym")
                .register(4718161)
                .build();
        assertNotNull(person);
        assertEquals(0.7, person.getBirthWeight());
        assertEquals(now1, person.getBirthday());
        assertEquals("Arno", person.getFirstName());
        assertEquals("Nym", person.getSureName());
        assertEquals(4718161, person.getRegisterNumber());
    }

    @Test
    void create2PersonNew() {

        final BuilderStages.RegisterStage builder1 = LegacyPersonBuilder.builder()
                .birthWeight(0.7)
                .birthday(now1)
                .firstName("Arno");

        final BuilderStages.RegisterStage builder2 = LegacyPersonBuilder.builder()
                .birthWeight(0.8)
                .birthday(now2)
                .firstName("Anno");


        final LegacyPerson person1 = builder1
                .sureName("Nym")
                .register(4718161)
                .build();
        checkValues(person1, null);

        final LegacyPerson person2 = builder2
                .sureName("Nymos")
                .register(12345)
                .build();

        checkValues(person1, person2);

    }

    void checkValues(final LegacyPerson person1, final LegacyPerson person2) {
        if (person1 == null && person2 == null) fail();
        // Person 1
        if (person1 != null) {
            assertNotNull(person1);
            assertEquals(0.7, person1.getBirthWeight());
            assertEquals(now1, person1.getBirthday());
            assertEquals("Arno", person1.getFirstName());
            assertEquals("Nym", person1.getSureName());
            assertEquals(4718161, person1.getRegisterNumber());
        }
        // Person 2
        if (person2 != null) {
            assertNotNull(person1);
            assertEquals(0.8, person2.getBirthWeight());
            assertEquals(now1, person2.getBirthday());
            assertEquals("Anno", person2.getFirstName());
            assertEquals("Nymos", person2.getSureName());
            assertEquals(12345, person2.getRegisterNumber());
        }
    }

}
