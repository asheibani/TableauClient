package org.sheibani.tableauclient.model

/**
 * Created by asheibani on 5/28/17.
 */
class CredentialsResponse {

    Credentials credentials

    private static class Credentials {
        Site site
        User user
        String token
    }

    private static class Site {
        String id
        String contentUrl
    }

    private static class User {
        String id
    }
}
