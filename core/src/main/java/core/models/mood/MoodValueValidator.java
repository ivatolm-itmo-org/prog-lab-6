package core.models.mood;

import core.command.arguments.ArgCheck;

/**
 * Validator for {@code value} field of the {@code Mood}.
 *
 * @author ivatolm
 */
public class MoodValueValidator implements ArgCheck {

    @Override
    public boolean check(String value) {
        try {
            Mood.parseMood(value);
        } catch (Exception e) {
            return false;
        }

        return value != null;
    }

}
