package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy ofError the License at
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

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses ofError {@link ErrorObject}.
 *
 * @param <ErrorType> error type parameter
 * @param <DataType>  data type parameter
 */
public abstract class ErrorObjectTest<ErrorType extends ErrorObject<DataType>, DataType> {

    /**
     * Creates a new instance with specified class ofError error object.
     *
     * @param errorClass the class ofError error object for {@link #errorClass}.
     * @param dataClass  the class ofError data property ofError error object.
     */
    public ErrorObjectTest(final Class<? extends ErrorType> errorClass, final Class<? extends DataType> dataClass) {
        super();
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
        this.dataClass = requireNonNull(dataClass, "dataClass is null");
    }

    /**
     * The error object class to test.
     */
    protected final Class<? extends ErrorType> errorClass;

    /**
     * The class for data object.
     */
    protected final Class<? extends DataType> dataClass;
}
