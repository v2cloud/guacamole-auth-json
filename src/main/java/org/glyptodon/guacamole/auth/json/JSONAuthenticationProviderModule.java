/*
 * Copyright (C) 2015 Glyptodon LLC
 *
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
 */

package org.glyptodon.guacamole.auth.json;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.environment.Environment;
import org.apache.guacamole.environment.LocalEnvironment;
import org.apache.guacamole.net.auth.AuthenticationProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.glyptodon.guacamole.auth.json.conf.ConfigurationService;
import org.glyptodon.guacamole.auth.json.conf.DynamicConfigurationService;
import org.glyptodon.guacamole.auth.json.connection.ConnectionService;
import org.glyptodon.guacamole.auth.json.user.UserDataService;

/**
 * Guice module which configures injections specific to the JSON authentication
 * provider.
 *
 * @author Michael Jumper
 */
public class JSONAuthenticationProviderModule extends AbstractModule {

    /**
     * Jersey client configuration.
     */
    private final ClientConfig CLIENT_CONFIG = new DefaultClientConfig(JacksonJsonProvider.class);

    /**
     * Guacamole server environment.
     */
    private final Environment environment;

    /**
     * A reference to the JSONAuthenticationProvider on behalf of which this
     * module has configured injection.
     */
    private final AuthenticationProvider authProvider;

    /**
     * Creates a new JSON authentication provider module which configures
     * injection for the JSONAuthenticationProvider.
     *
     * @param authProvider
     *     The AuthenticationProvider for which injection is being configured.
     *
     * @throws GuacamoleException
     *     If an error occurs while retrieving the Guacamole server
     *     environment.
     */
    public JSONAuthenticationProviderModule(AuthenticationProvider authProvider)
            throws GuacamoleException {

        // Get local environment
        this.environment = new LocalEnvironment();

        // Store associated auth provider
        this.authProvider = authProvider;

    }

    @Override
    protected void configure() {

        // Bind core implementations of guacamole-ext classes
        bind(AuthenticationProvider.class).toInstance(authProvider);
        bind(Environment.class).toInstance(environment);

        // Bind JSON-specific services
        bind(ConfigurationService.class);
        bind(DynamicConfigurationService.class);
        bind(ConnectionService.class);
        bind(CryptoService.class);
        bind(RequestValidationService.class);
        bind(UserDataService.class);

        // Bind singleton ObjectMapper for JSON serialization/deserialization
        bind(ObjectMapper.class).in(Scopes.SINGLETON);

        // Bind singleton Jersey REST client
        bind(Client.class).toInstance(Client.create(CLIENT_CONFIG));
    }

}
