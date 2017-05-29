package org.sheibani.tableauclient.model

/**
 * Created by asheibani on 5/28/17.
 */
class AddUserToSiteResponse {

    User user

    public class User  {
        String id
        String name
        String siteRole
        String authSetting
        String externalAuthUserId
    }
}
