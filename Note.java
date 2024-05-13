import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Note {
    private String text;
    private LocalDateTime timestamp;

    public Note(String text) {
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
}
