package com.github.huluvu424242.e1person;

/*-
 * #%L
 * fluent-builder.example
 * %%
 * Copyright (C) 2023 Huluvu424242
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonBuilderTest {

    @Test
    @DisplayName("Erzeugt eine gültige Person ohne Adresse")
    void initPerson() {
        final Person person = new Person.PersonBuilder()
                .birthday(LocalDate.of(1969, 1, 1))
                .firstname("Maik")
                .surename("Kalauer")
                .address("Schildbürgerstr. 8, Siebensanden")
                .build();
        assertNotNull(person);
        assertNotNull(person.address);
    }

    @Test
    @DisplayName("Erzeugt eine gültige Person ohne Adresse")
    void initObdachlosePerson() {
        final Person person = new Person.PersonBuilder()
                .birthday(LocalDate.of(1969, 1, 1))
                .firstname("No")
                .surename("Body")
                .build();
        assertNotNull(person);
        assertNull(person.address);
    }

    @Test
    @DisplayName("Erzeugt eine ungültige Person ohne Geburtsdatum")
    void initInvalidPerson() {
        final Person.PersonBuilder builder = new Person.PersonBuilder()
                .firstname("Arno")
                .surename("Nym")
                .address("Heinzelmannstr. 8, Mainz");
        assertNotNull(builder);
        assertThrows(NullPointerException.class, builder::build);
    }
}
