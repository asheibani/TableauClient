package org.sheibani.tableauclient.client

import feign.Param
import feign.RequestLine

/**
 * Created by asheibani on 5/28/17.
 */
interface TableauClient {

    @RequestLine("POST /trusted?username={username}")
    String getToken(@Param("username") String username)

}
