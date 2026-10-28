package gigabyte.model.client;

import static gigabyte.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void constructor_nameWithAllowedPunctuation_keepsFullName() {
        assertEquals("Raj A/L Kumar", new Name("Raj A/L Kumar").fullName);
    }

    @Test
    public void isValidName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));
    }

    @Test
    public void isValidName_blank_returnsFalse() {
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName(" Peter")); // leading space
    }

    @Test
    public void isValidName_startsWithAllowedPunctuation_returnsFalse() {
        for (String name : new String[] {"'Brien", "-Jane", ".Acme", ",Tan", "&Co", "/o Kumar", "(Tan)",
            "@home", "'-.,&/()@"}) {
            assertFalse(Name.isValidName(name), name);
        }
    }

    @Test
    public void isValidName_disallowedCharacter_returnsFalse() {
        for (String name : new String[] {"^", "peter*", "R#chel", "Tan!", "Lee; Tan", "Tan_Lee", "Tan\"Lee",
            "Tan:Lee", "Tan+Lee", "Tan\tLee"}) {
            assertFalse(Name.isValidName(name), name);
        }
    }

    @Test
    public void isValidName_lettersDigitsAndSpaces_returnsTrue() {
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("P")); // single character, boundary
    }

    @Test
    public void isValidName_eachAllowedPunctuationAfterFirstCharacter_returnsTrue() {
        assertTrue(Name.isValidName("O'Brien")); // apostrophe
        assertTrue(Name.isValidName("Mary-Jane Tan")); // hyphen
        assertTrue(Name.isValidName("Acme Pte. Ltd.")); // full stop
        assertTrue(Name.isValidName("Tan, Lee")); // comma
        assertTrue(Name.isValidName("Smith & Co")); // ampersand
        assertTrue(Name.isValidName("Raj s/o Kumar")); // slash
        assertTrue(Name.isValidName("Tan (Studio)")); // parentheses
        assertTrue(Name.isValidName("Cafe @ Tiong Bahru")); // at sign
        assertTrue(Name.isValidName("A'-.,&/()@")); // every allowed punctuation character
    }

    @Test
    public void isValidName_upperCasePrefixWorkaround_returnsTrue() {
        assertTrue(Name.isValidName("Raj A/L Kumar"));
        assertTrue(Name.isValidName("Siti A/P Rahman"));
        assertTrue(Name.isValidName("Lee T/A Shop"));
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
