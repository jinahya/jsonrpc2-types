package com.github.jinahya.jsonrpc.bind.v2;

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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Constructor;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for calculatorRequest objects and response objects.
 *
 * @param <IdType> {@value #PROPERTY_NAME_ID} type parameter
 */
public abstract class JsonrpcObject<IdType> {

    /**
     * The property name for {@code jsonrpc}.
     */
    public static final String PROPERTY_NAME_JSONRPC = "jsonrpc";

    /**
     * The property name for {@code id}.
     */
    public static final String PROPERTY_NAME_ID = "id";

    /**
     * The fixed value for {@value #PROPERTY_NAME_JSONRPC} attribute. The value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    public static final String PROPERTY_VALUE_JSONRPC = "2.0";

    static class JsonrpcObjectBuilder<
            BuilderType extends JsonrpcObjectBuilder<BuilderType, ObjectType, IdType>,
            ObjectType extends JsonrpcObject<IdType>,
            IdType> {

        JsonrpcObjectBuilder(final Class<? extends ObjectType> objectClass) {
            super();
            this.objectClass = requireNonNull(objectClass, "objectClass is null");
        }

        @SuppressWarnings({"unchecked"})
        public BuilderType id(final IdType id) {
            this.id = id;
            return (BuilderType) this;
        }

        public ObjectType build() {
            try {
                final Constructor<? extends ObjectType> constructor = objectClass.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                final ObjectType instance = constructor.newInstance();
                instance.setId(id);
                return instance;
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private final Class<? extends ObjectType> objectClass;

        private IdType id;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "jsonrpc=" + jsonrpc
               + ",id=" + id
               + "}";
    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_JSONRPC} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_JSONRPC} property.
     */
    public String getJsonrpc() {
        return jsonrpc;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_VALUE_JSONRPC} property with given.
     *
     * @param jsonrpc new value for {@value #PROPERTY_VALUE_JSONRPC} property.
     */
    public void setJsonrpc(final String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    public IdType getId() {
        return id;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with given.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    public void setId(final IdType id) {
        this.id = id;
    }

    /**
     * Copies this object's {@value #PROPERTY_NAME_ID} property to specified object's {@value #PROPERTY_NAME_ID}
     * property.
     *
     * @param object the object whose {@value #PROPERTY_NAME_ID} property is copied from this object's {@value
     *               #PROPERTY_NAME_ID} property.
     * @param <T>    object type parameter.
     * @see #getId()
     * @see #setId(Object)
     * @see #copyIdFrom(JsonrpcObject)
     */
    public <T extends JsonrpcObject<? super IdType>> void copyIdTo(final T object) {
        object.setId(getId());
    }

    /**
     * Copies specified object's {@value #PROPERTY_NAME_ID} to this objects's {@value #PROPERTY_NAME_ID}.
     *
     * @param object the object whose {@value #PROPERTY_NAME_ID} property is copied to this object's {@value
     *               #PROPERTY_NAME_ID} property.
     * @param <T>    object type parameter.
     * @see #copyIdTo(JsonrpcObject)
     */
    public <T extends JsonrpcObject<? extends IdType>> void copyIdFrom(final T object) {
        object.copyIdTo(this);
    }

    /**
     * An attribute for {@value #PROPERTY_NAME_JSONRPC} property. Default value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    @Pattern(regexp = PROPERTY_VALUE_JSONRPC)
    @NotNull
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;

    /**
     * An attribute for {@value #PROPERTY_NAME_ID} property.
     */
    @Valid
    private IdType id;
}
