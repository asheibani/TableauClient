package org.sheibani.tableauclient.client

import feign.Body
import feign.Headers
import feign.Param
import feign.QueryMap
import feign.RequestLine
import org.sheibani.tableauclient.model.AddUserToSiteResponse
import org.sheibani.tableauclient.model.CredentialsResponse
import org.sheibani.tableauclient.model.QueryUsersResponse
import org.sheibani.tableauclient.model.UpdateUserResponse
import org.sheibani.tableauclient.model.WorkbookResponse

/**
 * Created by asheibani on 5/28/17.
 */
@Headers(["Content-Type: application/json", "Accept: application/json" ])
interface TableauApiClient {

    @RequestLine("POST /auth/signin")
    @Body("""%7B "credentials": %7B "name": "{username}", "password": "{password}", "site": %7B %7D %7D %7D""")
    CredentialsResponse signin(@Param("username") String username, @Param("password") password)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("GET /sites/{siteId}/workbooks/{workbookId}")
    WorkbookResponse getWorkbook(@Param("token") String token, @Param("siteId") String siteId, @Param("workbookId") String workbookId)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("POST /sites/{siteId}/users")
    @Body("""%7B "user": %7B "name": "{username}", "siteRole": "{siteRole}" %7D %7D""")
    AddUserToSiteResponse addUserToSite(@Param("token") String token, @Param("siteId") String siteId,
                                        @Param("username") String username, @Param("siteRole") String siteRole)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("PUT /sites/{siteId}/users/{userId}")
    @Body("""%7B "user": %7B "fullName": "{fullName}", "siteRole": "{siteRole}",  "password": "{password}" %7D %7D""")
    UpdateUserResponse updateUser(@Param("token") String token, @Param("siteId") String siteId,
                                  @Param("userId") String userId, @Param("fullName") String fullName,
                                  @Param("siteRole") String siteRole, @Param("password") String password)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("DELETE /sites/{siteId}/users/{userId}")
    void removeUserFromSite(@Param("token") String token, @Param("siteId") String siteId, @Param("userId") String userId)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("GET /sites/{siteId}/users")
    QueryUsersResponse getUsersOnSite(@Param("token") String token, @Param("siteId") String siteId)

    @Headers("X-Tableau-Auth: {token}")
    @RequestLine("GET /sites/{siteId}/users?filter={filter}")
    QueryUsersResponse searchUsersOnSiteByUsername(@Param("token") String token, @Param("siteId") siteId, @Param(value="filter", encoded = true) filter)


}