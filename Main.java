import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Note> notes = new HashMap<>();

        while (true) {
            System.out.println("1- Add");
            System.out.println("2- Remove");
            System.out.println("3- Notes");
            System.out.println("4- Export");
            System.out.println("In case you wanted to enter menu again, enter 80085");

            System.out.println("Please choose your command: ");

            String command = scanner.next();
            scanner.nextLine();

            switch (command) {
                case "1":
                    addNote(scanner, notes);
                    break;
                case "2":
                    removeNote(scanner, notes);
                    break;
                case "3":
                    displayNotes(scanner, notes);
                    break;
                case "4":
                    exportSingleNote(scanner, notes);
                    break;
                case "80085":
                    menu();
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
    }

    private static void addNote(Scanner scanner, HashMap<String, Note> notes) {
        System.out.println("Please choose a title for the note: ");
        String title = scanner.nextLine();
        if (title.equals("80085")) {
            menu();
            return;
        }
        System.out.println("Ok! Feel free to write! :-)");
        String noteText = scanner.nextLine();
        notes.put(title, new Note(noteText));
        System.out.println("The new note has been added successfully.");
    }

    private static void removeNote(Scanner scanner, HashMap<String, Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("No notes available to remove.");
            return;
        }
        List<String> titles = new ArrayList<>(notes.keySet());
        printNotes(titles, notes);
        System.out.println("Which one do you want to remove? Enter the number:");
        int removeIndex = scanner.nextInt();
        if (removeIndex == 80085) {
            menu();
            return;
        }
        scanner.nextLine();
        if (removeIndex > 0 && removeIndex <= titles.size()) {
            notes.remove(titles.get(removeIndex - 1));
            System.out.println("Note removed successfully.");
        } else {
            System.out.println("Invalid note number.");
        }
    }

    private static void displayNotes(Scanner scanner, HashMap<String, Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
            return;
        }
        List<String> titles = new ArrayList<>(notes.keySet());
        printNotes(titles, notes);
        System.out.println("Choose a note to show:");
        int showIndex = scanner.nextInt();
        if (showIndex == 80085) {
            menu();
            return;
        }
        scanner.nextLine();
        if (showIndex > 0 && showIndex <= titles.size()) {
            String selectedTitle = titles.get(showIndex - 1);
            Note note = notes.get(selectedTitle);
            System.out.println("Content of " + selectedTitle + ":");
            System.out.println(note.getText());
        } else {
            System.out.println("Invalid note number.");
        }
    }

    private static void exportSingleNote(Scanner scanner, HashMap<String, Note> notes) {
        List<String> titles = new ArrayList<>(notes.keySet());
        printNotes(titles, notes);
        System.out.println("Choose your note to be exported:");
        int exportIndex = scanner.nextInt();
        if (exportIndex == 80085) {
            menu();
            return;
        }
        scanner.nextLine();
        if (exportIndex > 0 && exportIndex <= titles.size()) {
            String selectedTitle = titles.get(exportIndex - 1);
            Note note = notes.get(selectedTitle);
            exportNoteToFile(selectedTitle, note);
        } else {
            System.out.println("Invalid note number.");
        }
    }

    private static void exportNoteToFile(String title, Note note) {
        File export = new File("export");
        if (!export.exists()) {
            export.mkdir();
        }

        File exportFile = new File(export, title + ".txt");
        try (FileWriter writer = new FileWriter(exportFile)) {
            writer.write("Title: " + title + "\n");
            writer.write("Added on: " + note.getTimestamp() + "\n");
            writer.write("Content: " + note.getText() + "\n");
            System.out.println("Note '" + title + "' exported successfully to " + exportFile.getPath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void printNotes(List<String> titles, HashMap<String, Note> notes) {
        int i = 0;
        System.out.println("These are the available notes:");
        for (String title : titles) {
            i++;
            System.out.println(i + ". " + title + " - Added on " + notes.get(title).getTimestamp());
        }
    }

}
