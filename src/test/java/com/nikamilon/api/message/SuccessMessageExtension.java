package com.nikamilon.api.message;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class SuccessMessageExtension implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        //this method will cause only if the test has successfully completed (without exception)
        String testName = context.getDisplayName();
        System.out.println("Test passed successful: " + testName);
    }
}
