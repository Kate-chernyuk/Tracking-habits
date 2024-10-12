import org.example.Admin;
import org.example.Habit;
import org.example.User;
import org.example.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminTest {

    private UserManager userManager;
    private Admin admin;

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        admin = new Admin(userManager);
        userManager.registerUser("user1@example.com", "password1");
        userManager.registerUser("user2@example.com", "password2");
    }

    @Test
    void testViewAllUsers() {
        List<User> users = admin.viewAllUsers();
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getEmail).containsExactlyInAnyOrder("user1@example.com", "user2@example.com");
    }

    @Test
    void testBlockUser() {
        admin.blockUser("user1@example.com");
        assertThat(userManager.getUser("user1@example.com").isActive()).isFalse();
    }

    @Test
    void testUnblockUser() {
        admin.blockUser("user1@example.com");
        admin.unblockUser("user1@example.com");
        assertThat(userManager.getUser("user1@example.com").isActive()).isTrue();
    }

    @Test
    void testDeleteUser() {
        admin.deleteUser("user1@example.com");
        assertThat(userManager.getUser("user1@example.com")).isNull();
    }
    
}
