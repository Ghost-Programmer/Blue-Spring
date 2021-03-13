package com.blue.project.View;

public class Views {
    // include the field as a publicly accessible value in the API, i.e. only expose certain values to the public
    public static class Public {
    }

    // include the field as a privately accessible value in the API, i.e. only expose certain values to the private users
    public static class Private {
    }

    // include the field only where a view is not specified, i.e. internal application calls
    public static class Hidden {
    }
}
