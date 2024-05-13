import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- Add");
        System.out.println("2- Remove");
        System.out.println("3- Notes");
        System.out.println("4- Export");

        HashMap<String, Note> notes = new HashMap<>();

        while (true) {
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
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
    }

    private static void addNote(Scanner scanner, HashMap<String, Note> notes) {
        System.out.println("Please choose a title for the note: ");
        String title = scanner.nextLine();
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

}
