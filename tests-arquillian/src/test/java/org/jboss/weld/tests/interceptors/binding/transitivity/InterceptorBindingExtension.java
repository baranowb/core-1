/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.tests.interceptors.binding.transitivity;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.BeforeBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;

public class InterceptorBindingExtension implements Extension {

    void registerInterceptorBindings(@Observes BeforeBeanDiscovery event) {
        event.addInterceptorBinding(Secure.class);
        // transactional is interceptor binding by default

        event.addInterceptorBinding(UltraSecure.class, new Secure.SecureLiteral()); // register UltraSecure with Secure as a transitive binding
        event.addInterceptorBinding(UltraTransactional.class, new Transactional.TransactionalLiteral()); // register UltraTransactional with Transactional as a transitive binding
    }

}
