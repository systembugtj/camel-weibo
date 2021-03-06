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

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WeiboConsumerTest extends WeiboTestSupport {
    private static final transient Logger LOG = LoggerFactory.getLogger(WeiboConsumerTest.class);
    
    protected String getFromURI() {
        return "weibo://timeline/public/?";
    }

    @Test
    public void testConsumerTest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        // just make sure we can get some date here.
        mock.expectedMinimumMessageCount(1);
        
        mock.assertIsSatisfied();
        List<Exchange> weibos = mock.getExchanges();
        if (LOG.isInfoEnabled()) {
            for (Exchange e : weibos) {
                LOG.info("Weibo: " + e.getIn().getBody(String.class));
            }
        }

    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from(getFromURI() + getUriTokens() + "&delay=30")
                    .to("mock:result");

            }
        };
    }
}
