package beregner.semesterprojekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// Jonas
public class LoginModelTest {

    private LoginModel loginModel;

    @BeforeEach
    public void setUp() {
        loginModel = new LoginModel();
    }

    @org.junit.Test
    public void testConnect() {
        //Tester hvorvidt forbindelsen er gyldig
        try {
            assertTrue(loginModel.Connect(), "Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateUser() {
        // Ved forkert input
        int userTypeID = loginModel.validateUser("4", "1241");
        assertEquals(-1, userTypeID, "Burde return -1");

        // Givet der er en sælger, med ID = 1 og password = 1234
        userTypeID = loginModel.validateUser("1", "1234");
        assertEquals(1, userTypeID, "Burde return userTypeID 1");
    }
}