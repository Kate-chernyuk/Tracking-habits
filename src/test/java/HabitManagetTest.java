import org.example.Habit;
import org.example.HabitManager;
import org.example.User;
import org.example.UserManager;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class HabitManagetTest {
    private UserManager userManager = new UserManager();
    private HabitManager habitManager = new HabitManager();

    @Test
    void testAddHabit() {
        userManager.registerUser("user@example.com", "password");
        User user = userManager.loginUser("user@example.com", "password");

        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        habitManager.addHabit(user, habit);

        List<Habit> habits = habitManager.getUserHabits(user);
        assertThat(habits).contains(habit);
    }

    @Test
    void testDeleteHabit() {
        userManager.registerUser("user@example.com", "password");
        User user = userManager.loginUser("user@example.com", "password");

        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        habitManager.addHabit(user, habit);
        habitManager.deleteHabit(user, habit.getName());

        List<Habit> habits = habitManager.getUserHabits(user);
        assertThat(habits).doesNotContain(habit);
    }
}
