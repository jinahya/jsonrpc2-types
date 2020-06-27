package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.beans.Transient;
import java.math.BigInteger;

import static java.util.Optional.ofNullable;

/**
 * An interface for JSONRPC messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S1214"})
public interface JsonrpcMessage extends JsonrpcObject {

    /**
     * The property name for JSONRPC version. The value is {@value}.
     * <blockquote>
     * A String specifying the version of the JSON-RPC protocol. MUST be exactly "2.0".
     * </blockquote>
     *
     * @see #PROPERTY_PATTERN_REGEXP_JSONRPC
     * @see #PROPERTY_VALUE_JSONRPC
     */
    String PROPERTY_NAME_JSONRPC = "jsonrpc";

    /**
     * The regexp for {@link #PROPERTY_NAME_JSONRPC} property. The value is {@value}.
     */
    String PROPERTY_PATTERN_REGEXP_JSONRPC = "2\\.0";

    /**
     * The default (and fixed) value for {@link #PROPERTY_NAME_JSONRPC} property. The value is {@code 2.0}.
     */
    String PROPERTY_VALUE_JSONRPC = PROPERTY_PATTERN_REGEXP_JSONRPC.replaceAll("\\\\.", ".");

    /**
     * The property name for identifying messages. The value is {@value}.
     * <blockquote>
     * An identifier established by the Client that MUST contain a String, Number, or NULL value if included. If it is
     * not included it is assumed to be a notification. The value SHOULD normally not be Null and Numbers SHOULD NOT
     * contain fractional parts
     * </blockquote>
     */
    String PROPERTY_NAME_ID = "id";

    /**
     * Returns current value of {@value #PROPERTY_NAME_JSONRPC} property.
     *
     * @return current value of {@value #PROPERTY_NAME_JSONRPC} property.
     */
    @Pattern(regexp = PROPERTY_PATTERN_REGEXP_JSONRPC)
    @NotNull
    String getJsonrpc();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_JSONRPC} property with specified value.
     *
     * @param jsonrpc new value for {@value #PROPERTY_NAME_JSONRPC} property.
     */
    void setJsonrpc(String jsonrpc);

    /**
     * Indicates whether this message has a value for {@value #PROPERTY_NAME_ID} property.
     *
     * @return {@code} true if set; {@code false} otherwise.
     */
    @Transient
    boolean hasId();

    /**
     * Indicates whether this message is a notification.
     * <blockquote>
     * A Notification is a Request object without an "id" member. A Request object that is a Notification signifies the
     * Client's lack of interest in the corresponding Response object, and as such no Response object needs to be
     * returned to the client. The Server MUST NOT reply to a Notification, including those that are within a batch
     * request.
     * <p>
     * Notifications are not confirmable by definition, since they do not have a Response object to be returned. As
     * such, the Client would not be aware of any errors (like e.g. "Invalid params","Internal error").
     * </blockquote>
     *
     * @return {@code true} if this message is a notification; {@code false} otherwise.
     * @implSpec Default implementation returns {@code !hasId()};
     */
    @Transient
    default boolean isNotification() {
        return !hasId();
    }

    /**
     * Indicates whether current value of {@value #PROPERTY_NAME_ID} property is contextually valid.
     *
     * @return {@code true} if valid; {@code false} otherwise.
     * @apiNote It's considered to be valid if {@link #hasId()} returns {@code false}.
     */
    @AssertTrue
    @Transient
    boolean isIdContextuallyValid();

    /**
     * Returns current value of {@value #PROPERTY_NAME_ID} property as a string.
     *
     * @return current value of {@value #PROPERTY_NAME_ID} property.
     */
    @Transient
    String getIdAsString();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    void setIdAsString(String id);

    /**
     * Returns current value of {@value #PROPERTY_NAME_ID} property as a number.
     *
     * @return current value of {@value #PROPERTY_NAME_ID} property.
     */
    @Transient
    BigInteger getIdAsNumber();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    void setIdAsNumber(final BigInteger id);

    /**
     * Returns current value of {@value #PROPERTY_NAME_ID} property as a {@code Long} value.
     *
     * @return current value of {@value #PROPERTY_NAME_ID} property.
     * @see #getIdAsNumber()
     * @see BigInteger#longValueExact()
     */
    @Transient
    default Long getIdAsLong() {
        return ofNullable(getIdAsNumber()).map(BigInteger::longValueExact).orElse(null);
    }

    /**
     * Replaces current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     * @see BigInteger#valueOf(long)
     * @see #setIdAsNumber(BigInteger)
     */
    default void setIdAsLong(final Long id) {
        setIdAsNumber(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    /**
     * Returns current value of {@value #PROPERTY_NAME_ID} property as a {@code Integer} value.
     *
     * @return current value of {@value #PROPERTY_NAME_ID} property.
     * @see #getIdAsLong()
     * @see Math#toIntExact(long)
     */
    @Transient
    default Integer getIdAsInteger() {
        return ofNullable(getIdAsLong()).map(Math::toIntExact).orElse(null);
    }

    /**
     * Replaces current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     * @see Integer#longValue()
     * @see #setIdAsLong(Long)
     */
    default void setIdAsInteger(final Integer id) {
        setIdAsLong(ofNullable(id).map(Integer::longValue).orElse(null));
    }
}
