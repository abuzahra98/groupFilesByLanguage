import java.io.File;

public class GroupFilesByLanguage {

    public static void main(String[] args) {
        String folderPath = args[0];
        groupFilesByLanguage(folderPath);
    }

    public static void groupFilesByLanguage(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("folder doesn't exist.");
        }

        File[] allFiles = folder.listFiles();

        if (allFiles == null) {
            System.err.println("Error found files in the folder.");
            return;
        }

        for (File file : allFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                // Extract the language name from the file name
                String fileName = file.getName();
                String[] parts = fileName.split("-");
                String language = parts[0];

                // Create sub-folder for the language, if it doesn't exist
                File languageFolder = new File(folder, language);
                if (!languageFolder.exists()) {
                    languageFolder.mkdirs();
                }

                // Move the file to the language sub-folder
                file.renameTo(new File(languageFolder, fileName));
            }
        }
        System.out.println("Files grouped");
    }
}
