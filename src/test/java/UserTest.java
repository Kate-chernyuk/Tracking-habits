import org.example.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {
    @Test
    void testUserCreation() {
        User user = new User("test@example.com", "password123");
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.isActive()).isTrue();  // По умолчанию активен
    }

    @Test
    void testUpdateUserInfo() {
        User user = new User("test@example.com", "password123");
        user.setName("New Name");
        user.setPassword("newpassword456");

        assertThat(user.getName()).isEqualTo("New Name");
        assertThat(user.getPassword()).isEqualTo("newpassword456");
    }

    @Test
    void testDeactivateUser() {
        User user = new User("test@example.com", "password123");
        user.setActive(false);

        assertThat(user.isActive()).isFalse();
    }

    @Test
    void testReactivateUser() {
        User user = new User("test@example.com", "password123");
        user.setActive(false);
        user.setActive(true);

        assertThat(user.isActive()).isTrue();
    }
}
