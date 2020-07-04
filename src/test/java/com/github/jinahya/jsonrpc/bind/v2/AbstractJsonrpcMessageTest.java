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

import org.junit.jupiter.api.Test;

abstract class AbstractJsonrpcMessageTest<T extends AbstractJsonrpcMessage>
        extends AbstractJsonrpcObjectTest<T> {

    AbstractJsonrpcMessageTest(final Class<T> messageClass) {
        super(messageClass);
    }

    // ------------------------------------------------------------------------------------------------------- $.jsonrpc
    @Test
    void testGetJsonrpc() {
        final String jsonrpc = newInstance().getJsonrpc();
    }

    @Test
    void testSetJsonrpc() {
        newInstance().setJsonrpc(JsonrpcMessage.PROPERTY_VALUE_JSONRPC);
        newInstance().setJsonrpc(null);
    }

    // ------------------------------------------------------------------------------------------------------------ $.id
    @Test
    void testHasId() {
        newInstance().hasId();
    }

    @Test
    void testIsIdContextuallyValid() {
        newInstance().isIdContextuallyValid();
    }

    @Test
    void testGetIdAsLong() {
        newInstance().getIdAsLong();
    }

    @Test
    void testSetIdAsLong() {
        newInstance().setIdAsLong(0L);
    }

    @Test
    void testGetIdAsInteger() {
        newInstance().getIdAsInteger();
    }

    @Test
    void testSetIdAsInteger() {
        newInstance().setIdAsInteger(0);
    }
}
