package Excercice.Day01Todos;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Todo {
    private String task;
    private LocalDate dueDate;
    private int hoursOfWork;
    private TaskStatus status;
    public enum TaskStatus {Pending, Completed}

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        if (task.length() < 2) {
            throw new IllegalArgumentException("Task length should be greater than 2");
        } else if (task.length() > 50) {
            throw new IllegalArgumentException("Task length should be less than 50");
        } else if (task.contains("|") || task.contains("`")) {
            throw new IllegalArgumentException("Task contains invalid characters");
        } else {
            this.task = task;
        }
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if ((dueDate.getYear() < 1900) || (dueDate.getYear() > 2100)) {
            throw new IllegalArgumentException("Invalid year");
        } else {
            this.dueDate = dueDate;
        }
    }

    public int getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(int hoursOfWork) {
        if ((hoursOfWork < 0)) {
            throw new IllegalArgumentException("Hours of work cannot be negative");
        }
        this.hoursOfWork = hoursOfWork;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return task + "," + dueDate + ", will take " + hoursOfWork + " hour(s) of work, "+ status;
    }

    public Todo(String task, LocalDate dueDate, int hoursOfWork, TaskStatus status) {
        setTask(task);
        setDueDate(dueDate);
        setHoursOfWork(hoursOfWork);
        setStatus(status);
    }
    public Todo(String dataLine){
        String[] parts = dataLine.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid data line format. Expected 4 parts separated by commas.");
        }
        try {
            setTask(parts[0].trim());
            setDueDate(LocalDate.parse(parts[1].trim()));
            setHoursOfWork(Integer.parseInt(parts[2].trim()));
            setStatus(Enum.valueOf(TaskStatus.class, parts[3].trim()));
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format. Expected YYYY-MM-DD.");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid hours of work format. Expected a number.");
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid status in data line. Must be 'Pending' or 'Completed'.");
        }

    }
    public String toDataString(){
        return task + ";" + dueDate + ";" + hoursOfWork+ ";" + status;
    }
}


