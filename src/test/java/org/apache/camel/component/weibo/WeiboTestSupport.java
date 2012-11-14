/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.weibo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.camel.test.junit4.CamelTestSupport;

public class WeiboTestSupport extends CamelTestSupport {
    private String clientId;
    private String clientSecret;
    private String accessToken;

    public WeiboTestSupport() {
        URL url = getClass().getResource("/test-options.properties");

        InputStream inStream;
        try {
            inStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalAccessError("test-options.properties could not be found");
        }

        Properties properties = new Properties();
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalAccessError("test-options.properties could not be found");
        }

        clientId = properties.get("client.id").toString();
        clientSecret = properties.get("client.secret").toString();
        accessToken = properties.get("access.token").toString();
    }

    protected String getUriTokens() {
        return "clientId=" + clientId + "&clientSecret=" + clientSecret + "&accessToken="
                + accessToken;
    }

}