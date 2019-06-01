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

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.Objects;

/**
 * Represents response objects.
 *
 * @param <T> result type parameter
 * @param <U> error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<T, U extends ErrorObject<?>> extends JsonrpcObject {

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{" +
               "result=" + result +
               ",error=" + error +
               "}";
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
        if (!(obj instanceof ResponseObject)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final ResponseObject<?, ?> that = (ResponseObject<?, ?>) obj;
        return Objects.equals(getResult(), that.getResult()) &&
               Objects.equals(getError(), that.getError());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResult(), getError());
    }

    /**
     * Checks if the {@code result} attribute and the {@code error} attribute are exclusive.
     *
     * @return {@code true} if {@code result} and {@code error} set exclusively, {@code false} otherwise.
     */
    @JsonIgnore
    @JsonbTransient
    @AssertTrue(message = "result and error should be set exclusively")
    private boolean isResultAndErrorExclusive() {
        return (getResult() != null) ^ (getError() != null);
    }

    /**
     * Returns the current value of {@code result} attribute.
     *
     * @return the current value of {@code result} attribute
     */
    public T getResult() {
        return result;
    }

    /**
     * Replaces the current value of {@code result} attribute with given.
     *
     * @param result new value for {@code result} attribute
     * @see #setResultExclusively(Object)
     */
    public void setResult(final T result) {
        this.result = result;
    }

    /**
     * Sets {@code result} attribute exclusively. This method, if given {@code result} is not {@code null}, sets {@code
     * error} as {@code null}.
     *
     * @param result new value for {@code result} attribute
     * @see #setResult(Object)
     */
    public void setResultExclusively(final T result) {
        setResult(result);
        if (getResult() != null) {
            setError(null);
        }
    }

    /**
     * Returns the current value of {@code error} attribute.
     *
     * @return the current value of {@code error} attribute
     */
    public U getError() {
        return error;
    }

    /**
     * Replaces the current value of {@code error} attribute with given.
     *
     * @param error new value for {@code error} attribute
     * @see #setErrorExclusively(ErrorObject)
     */
    public void setError(final U error) {
        this.error = error;
    }

    /**
     * Sets {@code error} attribute exclusively. This method, if given {@code error} is not {@code null}, sets {@code
     * result} as {@code null}.
     *
     * @param error new value for {@code error} attribute
     * @see #setError(ErrorObject)
     */
    public void setErrorExclusively(final U error) {
        setError(error);
        if (getError() != null) {
            setResult(null);
        }
    }

    @Valid
    private T result;

    @Valid
    private U error;
}
