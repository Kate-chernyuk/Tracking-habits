import org.example.Habit;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HabitTest {
    @Test
    void testHabitCreation() {
        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        assertThat(habit.getName()).isEqualTo("Exercise");
        assertThat(habit.getDescription()).isEqualTo("Workout for 30 minutes");
        assertThat(habit.getFrequency()).isEqualTo("daily");
    }

    @Test
    void testRecordCompletion() {
        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        habit.recordCompletion(true);
        assertThat(habit.getRecords()).containsExactly(true);
    }

    @Test
    void testStreak() {
        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        habit.recordCompletion(true);
        habit.recordCompletion(true);
        habit.recordCompletion(false);
        habit.recordCompletion(true);
        assertThat(habit.getStreak()).isEqualTo(1); // Should only count the last streak
    }

    @Test
    void testSuccessRate() {
        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        habit.recordCompletion(true);
        habit.recordCompletion(false);
        habit.recordCompletion(true);
        assertThat(habit.getSuccessRate(3)).isEqualTo(66.66666666666666);
    }

    @Test
    void testReportGeneration() {
        Habit habit = new Habit("Exercise", "Workout for 30 minutes", "daily");
        habit.recordCompletion(true);
        habit.recordCompletion(false);
        habit.recordCompletion(true);
        assertThat(habit.getReport(3)).isEqualTo("Выполнено: 2 из 3 (66.66666666666666%)");
    }
}