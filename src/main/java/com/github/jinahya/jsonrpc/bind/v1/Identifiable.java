package com.github.jinahya.jsonrpc.bind.v1;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Objects;

/**
 * An abstract class for binding identifiable objects.
 *
 * @param <IdType> id type parameter.
 */
public abstract class Identifiable<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A property name for {@code $.id}.
     */
    public static final String PROPERTY_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Identifiable() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "id=" + id
               + ",idSemanticallyNull=" + isIdSemanticallyNull()
               + "}";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identifiable)) {
            return false;
        }
        final Identifiable<?> that = (Identifiable<?>) obj;
        return Objects.equals(getId(), that.getId());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    public IdType getId() {
        return id;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with specified value.
     *
     * @param id new value for {@value PROPERTY_NAME_ID} property.
     */
    public void setId(final IdType id) {
        this.id = id;
    }

    /**
     * Indicates whether the current value of {@value PROPERTY_NAME_ID} property is <i>semantically</i> {@code null}.
     *
     * @return {@code true} if the current value of {@value PROPERTY_NAME_ID} property is <i>semantically</i> {@code
     * null}; {@code false} otherwise.
     */
    protected boolean isIdSemanticallyNull() {
        return getId() == null;
    }

    /**
     * Copies the current value of {@value #PROPERTY_NAME_ID} property to specified object.
     *
     * @param object the object to which this object's current value of {@value #PROPERTY_NAME_ID} property is copied.
     * @param <T>    object type parameter.
     */
    public <T extends Identifiable<? super IdType>> void copyIdTo(final T object) {
        object.setId(getId());
    }

    /**
     * Copies specified object's current value of {@value #PROPERTY_NAME_ID} property to this object.
     *
     * @param object the object from whose current value of {@value #PROPERTY_NAME_ID} property is copied.
     * @param <T>    object type parameter.
     */
    public <T extends Identifiable<? extends IdType>> void copyIdFrom(final T object) {
        object.copyIdTo(this);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private IdType id;
}
