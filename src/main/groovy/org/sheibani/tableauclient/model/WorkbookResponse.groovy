package org.sheibani.tableauclient.model

/**
 * Created by asheibani on 5/28/17.
 */
class WorkbookResponse {

    Workbook workbook

    public static class Workbook {
        Project project
        Owner owner
        Tags tags
        Views views
        String id
        String name
        String contentUrl
        Boolean showTabs
        String size
        String createdAt
        String updatedAt
    }

    public static class Project {
        String id
        String name
    }

    public static class Owner {
        String id
    }

    public static class Views {
        List<View> view
    }

    public static class Tags {
        List<Tag> tag
    }

    public static class Tag {
        String label
    }

    public static class View {
        Tags tags
        String id
        String name
        String contentUrl
        String createdAt
        String updatedAt
    }
}
