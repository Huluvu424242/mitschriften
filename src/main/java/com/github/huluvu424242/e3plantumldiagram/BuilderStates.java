package com.github.huluvu424242.e3plantumldiagram;

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

import org.apache.commons.lang3.StringUtils;

public interface BuilderStates {
    StringBuilder build();

    interface NewState extends BuilderStates {

        default UmlStartState createUmlHeader() {
            final StringBuilder builder = build();
            builder.append("@startuml");
            return this::build;
        }
    }

    interface UmlStartState extends BuilderStates {
        default EntityState createEntity(final String name) {
            final StringBuilder builder = build();
            builder.append(String.format("\nentity %s{", name));
            return this::build;
        }

        default BuildState createUmlFooter() {
            final StringBuilder builder = build();
            builder.append("\n@enduml");
            return this::build;
        }
    }

    interface EntityState extends BuilderStates {
        default ColumnTypeState createColumnMandatory(final String columnName) {
            final StringBuilder builder = build();
            builder.append(String.format("\n* %s ", columnName));
            return this::build;
        }

        default ColumnTypeState createColumnNullable(final String columnName) {
            final StringBuilder builder = build();
            builder.append(String.format("%n  %s ", columnName));
            return this::build;
        }

        default UmlStartState next() {
            final StringBuilder builder = build();
            builder.append("\n}");
            return this::build;
        }
    }

    interface ColumnTypeState extends BuilderStates {
        default ColumnNotesState columnType(final String columnTypeSpec) {
            final StringBuilder builder = build();
            builder.append(String.format(" %s", columnTypeSpec));
            return this::build;
        }
    }

    interface ColumnNotesState extends BuilderStates {
        default EntityState columnNotes(final String columnNotes) {
            if (!StringUtils.isEmpty(columnNotes)) {
                final StringBuilder builder = build();
                builder.append(String.format(" %s", columnNotes));
            }
            return this::build;
        }
    }


    interface BuildState extends BuilderStates {
        // build() already defined
    }

}


