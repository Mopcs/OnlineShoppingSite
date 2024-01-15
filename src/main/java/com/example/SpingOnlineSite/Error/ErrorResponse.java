package com.example.SpingOnlineSite.Error;

/**
 * The type Error response.
 */
public class ErrorResponse {
    private String message;

    /**
     * Instantiates a new Error response.
     *
     * @param message the message
     */
    public ErrorResponse(String message) {
        this.message = message;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

