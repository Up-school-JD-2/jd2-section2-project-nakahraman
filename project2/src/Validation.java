import Constants.Message;
import Constants.Regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private boolean checkRegex(String regex, String valueToBeChecked) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valueToBeChecked);
        if (!(matcher.matches())) {
            return true;
        }
        return false;
    }


    public void validate(String regex, String number) throws InvalidPhoneNumberException, InvalidEmailException {

        if (checkRegex(regex, number)) {
            if(regex.equals(Regex.PHONE_NUMBER))
                throw new InvalidPhoneNumberException(Message.INVALID_PHONE_NUMBER);
            else
                throw new InvalidEmailException(Message.INVALID_EMAIL);
        }
    }
}
