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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

/**
 * A class for binding request objects.
 *
 * @param <ParamsType> {@value #PROPERTY_NAME_PARAMS} type parameter
 * @param <IdType>     {@value JsonrpcObject#PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#request_object">Request Object (JSON-RPC 2.0
 * Specification)</a>
 */
public abstract class RequestObject<IdType, ParamsType> extends JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.method}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code $.params}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    protected RequestObject() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "method=" + method
               + ",params=" + paramsType
               + "}";
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Indicates whether the current value of {@value #PROPERTY_NAME_PARAMS} property is, in a view of JSON, a
     * structured value.
     *
     * @return {@code true} if {@value #PROPERTY_NAME_PARAMS} property is, in a view of JSON, a structured value; {@code
     * false} otherwise.
     * @see <a href="https://www.jsonrpc.org/specification#parameter_structures">Parameter Structures (JSON-RPC 2.0
     * Specification)</a>
     */
    protected abstract @AssertTrue boolean isParamsStructured();

    // ---------------------------------------------------------------------------------------------------------- method

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property. The default value of the property is {@code
     * null}.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replace the current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    // ---------------------------------------------------------------------------------------------------------- params

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property. The default value of the property is {@code
     * null}.
     *
     * @return the current value of {@value #PROPERTY_NAME_PARAMS} property.
     */
    protected ParamsType getParamsType() {
        return paramsType;
    }

    public abstract <T> T getParamsAs(final Class<T> paramsClass);

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param paramsType new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    protected void setParamsType(final ParamsType paramsType) {
        this.paramsType = paramsType;
    }

    public abstract <T> void setParams(T params);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_METHOD} property.
     */
    @NotBlank
    private String method;

    /**
     * An attribute for {@value #PROPERTY_NAME_PARAMS} property.
     */
    @Valid
    private ParamsType paramsType;
}
