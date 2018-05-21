package cn.duc.global.exception;

/**
 * Business Exception
 *
 * @author Jason Wu
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
