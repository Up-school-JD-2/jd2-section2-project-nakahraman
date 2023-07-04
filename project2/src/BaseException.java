public class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }
}

class InvalidEmailException extends BaseException {

    public InvalidEmailException(String message) {
        super(message);
    }
}

class InvalidPhoneNumberException extends BaseException {

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}

class InvalidDateException extends BaseException {

    public InvalidDateException(String message) {
        super(message);
    }
}

class SystemNotWorkingException extends BaseException {

    public SystemNotWorkingException(String message) {
        super(message);
    }
}
