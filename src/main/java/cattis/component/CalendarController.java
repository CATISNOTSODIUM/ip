package cattis.component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalendarController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    public void initialize() {
        YearMonth currentMonth = YearMonth.now(ZoneId.of("Singapore"));
        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();

        int currentRow = 1;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int dayIndex = dayOfWeek.getValue() % 7; // Sunday=0, Monday=1, ..., Saturday=6
            calendarGrid.add(new Label(String.format("%d", date.getDayOfMonth())), dayIndex, currentRow);
            if (dayIndex == 6) {
                currentRow++;
            }
        }
    }
}
