package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 13:14
 */
public class WrongMealException extends RuntimeException {
    public WrongMealException() {
    }

    public WrongMealException(String message) {
        super(message);
    }

    public WrongMealException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongMealException(Throwable cause) {
        super(cause);
    }
}
