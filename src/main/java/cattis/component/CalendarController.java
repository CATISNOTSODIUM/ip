package cattis.component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import cattis.Cattis;
import cattis.CattisInterface;
import cattis.task.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalendarController {

    @FXML
    private Label header;

    @FXML
    private GridPane calendarGrid;

    private YearMonth currentMonth;
    private CattisInterface cattis;

    public void setCattis(CattisInterface cattis) {
        this.cattis = cattis;
    }

    @FXML
    public void initialize() {
        this.currentMonth = YearMonth.now(ZoneId.of("Singapore"));
        updateCalendar();
    }

    @FXML
    private void changeMonthPrevious() {
        this.currentMonth = this.currentMonth.minusMonths(1);
        updateCalendar();
    }

    @FXML
    private void changeMonthNext() {
        this.currentMonth = this.currentMonth.plusMonths(1);
        updateCalendar();
    }

    private void updateCalendar() {
        // Clear calendar if necessary
        calendarGrid.getChildren().removeIf(node -> {
            Integer rowIndex = GridPane.getRowIndex(node);
            return rowIndex != null && rowIndex > 0; // ignore the headers (Sun - Sat)
        });
        header.setText(this.currentMonth.format(DateTimeFormatter.ofPattern("MMMM")));

        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();
        int currentRow = 1;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int dayIndex = dayOfWeek.getValue() % 7; // Sunday=0, Monday=1, ..., Saturday=6
            calendarGrid.add(getIndividualNode(date), dayIndex, currentRow);
            if (dayIndex == 6) {
                currentRow++;
            }
        }
    }

    private Node getIndividualNode(LocalDate date) {
        assert cattis != null;
        // Task list
        // Get task list for this date
        List<Task> allTasks = cattis.getTaskList().getTasksByDate(date);
        // Date label
        Label dateLabel = new Label(String.valueOf(date.getDayOfMonth()));
        dateLabel.setMaxWidth(Double.MAX_VALUE);
        dateLabel.setAlignment(Pos.CENTER_LEFT);
        dateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5px;");

        VBox scrollContent = new VBox(allTasks.size() + 1);
        scrollContent.setPadding(new Insets(5));
        scrollContent.setAlignment(Pos.TOP_LEFT);

        //
        for (int i = 0; i < allTasks.size(); i++) {
            Label label = new Label(allTasks.get(i).getTaskName());
            if (allTasks.get(i).isCompleted()) {
                label.setStyle("-fx-background-color: #f5f0f0; -fx-text-fill: #bfbbbb; -fx-padding: 2px;");
            } else {
                label.setStyle("-fx-background-color: #f5d0d0; -fx-text-fill: #eb2626;  -fx-padding: 2px;");
            }
            label.setMaxWidth(Double.MAX_VALUE);
            scrollContent.getChildren().add(label);
        }

        ScrollPane scrollPane = new ScrollPane(scrollContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // disables visible bar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // just in case
        // Hide scroll bar with CSS
        scrollPane.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        scrollPane.lookupAll(".scroll-bar").forEach(bar -> bar.setVisible(false));


        VBox container = new VBox();
        VBox.setMargin(scrollPane, new Insets(2));
        container.setPrefWidth(120); // match your grid cell width
        container.getChildren().addAll(dateLabel, scrollPane);

        return container;
    }

}
