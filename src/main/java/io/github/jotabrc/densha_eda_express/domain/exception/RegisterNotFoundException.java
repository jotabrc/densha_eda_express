package io.github.jotabrc.densha_eda_express.domain.exception;

public class RegisterNotFoundException extends RuntimeException {
    public RegisterNotFoundException(String name, String identifier) {
        super("Register not found: " + name + " (" + identifier + ")");
    }
}
