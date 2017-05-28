package org.sheibani.tableauclient.client

import feign.Body
import feign.Headers
import feign.Param
import feign.RequestLine
import org.sheibani.tableauclient.model.CredentialsResponse
import org.sheibani.tableauclient.model.WorkbookResponse

/**
 * Created by asheibani on 5/28/17.
 */
@Headers(["Content-Type: application/json", "Accept: application/json" ])
interface TableauApiClient {

    @Body("""%7B "credentials": %7B "name": "{username}", "password": "{password}", "site": %7B %7D %7D %7D""")
    @RequestLine("POST /auth/signin")
    CredentialsResponse signin(@Param("username") String username, @Param("password") password)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("GET /sites/{siteId}/workbooks/{workbookId}")
    WorkbookResponse getWorkbook(@Param("token") String token, @Param("siteId") String siteId, @Param("workbookId") String workbookId)
}