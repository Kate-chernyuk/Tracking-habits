import org.example.User;
import org.example.UserManager;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserManagerTest {
    private UserManager userManager = new UserManager();

    @Test
    void testRegisterUser() {
        assertThat(userManager.registerUser("test@example.com", "password123")).isTrue();
        assertThat(userManager.registerUser("test@example.com", "password456")).isFalse();
    }

    @Test
    void testLoginUser() {
        userManager.registerUser("test@example.com", "password123");
        User user = userManager.loginUser("test@example.com", "password123");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("test@example.com");

        User invalidUser = userManager.loginUser("test@example.com", "wrongpassword");
        assertThat(invalidUser).isNull();
    }

    @org.junit.Test
    public void testUpdateUser() {
        userManager.registerUser("test@example.com", "password123");
        userManager.updateUser("test@example.com", "New Name", "newpassword123");
        User updatedUser = userManager.loginUser("test@example.com", "newpassword123");
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo("New Name");
    }

    @Test
    void testDeleteUser() {
        userManager.registerUser("test@example.com", "password123");
        assertThat(userManager.deleteUser("test@example.com")).isTrue();
        assertThat(userManager.loginUser("test@example.com", "password123")).isNull();
    }
}
