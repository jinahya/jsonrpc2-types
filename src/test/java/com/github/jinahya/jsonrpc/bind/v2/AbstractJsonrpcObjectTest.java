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

import java.lang.reflect.Constructor;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class AbstractJsonrpcObjectTest<T extends AbstractJsonrpcObject> {

    AbstractJsonrpcObjectTest(final Class<T> objectClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
    }

    @Test
    void testToString() {
        final String string = newInstance().toString();
        assertNotNull(string);
    }

    // --------------------------------------------------------------------------------------------------------------- $
    @Test
    void testIsContextuallyValid() {
        final boolean result = newInstance().isContextuallyValid();
    }

    @Test
    void testToJson() {
        final String json = newInstance().toJson();
    }

    // -----------------------------------------------------------------------------------------------------------------
    T newInstance() {
        try {
            final Constructor<T> constructor = objectClass.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    final Class<T> objectClass;
}
