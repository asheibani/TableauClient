package org.sheibani.tableauclient.model

/**
 * Created by asheibani on 5/28/17.
 */
class UpdateUserResponse {

    User user

    public class User {
        String name
        String fullName
        String siteRole
    }
}
