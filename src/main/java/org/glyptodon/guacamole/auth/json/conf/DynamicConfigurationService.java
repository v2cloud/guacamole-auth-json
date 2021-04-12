/*
 * Copyright (C) 2018 V2Cloud, Inc.
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

package org.glyptodon.guacamole.auth.json.conf;

import com.google.inject.Inject;
import com.google.inject.Singleton;
// import com.sun.jersey.api.client.Client;
// import com.sun.jersey.api.client.ClientHandlerException;
// import com.sun.jersey.api.client.ClientResponse;
// import com.sun.jersey.api.client.WebResource;
import org.apache.guacamole.GuacamoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

/**
 * Service for retrieving configuration information through invoking a REST Api exposed by an external agent.
 */

@Singleton
public class DynamicConfigurationService {

    /**
     * Logger for this class.
     */
    private final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    /**
     * Jersey REST client.
     */
    // @Inject
    // private Client client;

    /**
     * Service for retrieving static configuration information regarding the
     * CallbackAuthenticationProvider.
     */
    @Inject
    private ConfigurationService confService;


    /**
     * Returns the symmetric key which will be used to encrypt and sign all
     * JSON data and should be used to decrypt and verify any received JSON
     * data. This is dictated by the "json-secret-key" property specified
     * within guacamole.properties.
     *
     * @return The key which should be used to decrypt received JSON data.
     * @throws GuacamoleException If guacamole.properties cannot be parsed, or if the
     *                            "json-secret-key" property is missing.
     */
    public byte[] getSecretKey() throws GuacamoleException {

        // Otherwise, use defined HTTP callback
        // try {
        //     if (confService.getDynamicConfigAgentURI() != null) {
        //
        //         // Create WebResource for arbitrary callback
        //         WebResource resource = client.resource(confService.getDynamicConfigAgentURI())
        //                 .path("guac-dynamic-config");
        //
        //         // Attempt to retrieve UserData
        //         ClientResponse response =
        //                 resource.accept("application/json")
        //                         .get(ClientResponse.class);
        //
        //         // Determine status of response
        //         switch (response.getClientResponseStatus().getFamily()) {
        //
        //             // Return nothing if the callback reported an error
        //             case CLIENT_ERROR:
        //             case SERVER_ERROR:
        //                 return confService.getSecretKey();
        //
        //             // If the callback reported success, attempt to parse the
        //             // response
        //             case SUCCESSFUL:
        //                 String secret = response.getEntity(DynamicConfigData.class).getGuacSecretKey();
        //
        //                 try {
        //                     return DatatypeConverter.parseHexBinary(secret);
        //                 }
        //
        //                 // Fail parse if hex invalid
        //                 catch (IllegalArgumentException e) {
        //                     logger.error("Error while parsing dynamic secret key.", e);
        //                 }
        //         }
        //     }
        // } catch (ClientHandlerException e) {
        //     logger.error("Error while retrieving dynamic secret key.", e);
        // }

        // If callback did not return valid JSON, use static configuration (if available)
        return confService.getSecretKey();
    }
}
