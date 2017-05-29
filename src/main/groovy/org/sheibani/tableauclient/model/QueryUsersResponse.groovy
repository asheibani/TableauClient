package org.sheibani.tableauclient.model

/**
 * Created by asheibani on 5/28/17.
 */
class QueryUsersResponse {

    Pagination pagination
    Users users

    public class Pagination {
        Integer pageNumber
        Integer pageSize
        Integer totalAvailable
    }

    public class Users {
        List<User> user
    }

    public class User {
        String id
        String name
        String siteRole
    }
}
