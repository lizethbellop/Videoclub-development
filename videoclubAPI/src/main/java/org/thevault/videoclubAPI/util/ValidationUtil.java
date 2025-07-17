package org.thevault.videoclubAPI.util;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidationUtil {

    public static boolean isEmail(String input){
        return EmailValidator.getInstance().isValid(input);
    }
}
