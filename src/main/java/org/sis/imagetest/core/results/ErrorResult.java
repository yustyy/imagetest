package org.sis.imagetest.core.results;

public class ErrorResult extends Result {

    public ErrorResult(String message) {
        super(false, message);

    }

    public ErrorResult() {
        super(false);
    }

}
