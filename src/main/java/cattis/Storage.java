package cattis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cattis.exception.CattisException;
import cattis.exception.CattisSaveFileException;
import cattis.task.Task;
import cattis.task.TaskList;

/**
 * A class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from file filePath
     *
     * @param cattis application instance
     * @throws CattisException If unsuccessfully load the file
     */
    public void load(CattisInterface cattis) throws CattisException {
        File tempFile = new File(this.filePath);
        if (!tempFile.exists()) {
            cattis.getUi().showMessage("Create new file " + this.filePath);
        }
        ArrayList<String> result = new ArrayList<>();
        try (FileReader f = new FileReader(this.filePath)) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (!sb.isEmpty()) {
                result.add(sb.toString());
            }
        } catch (FileNotFoundException err) {
            throw new CattisException("Cannot find file " + this.filePath);
        } catch (IOException err) {
            throw new CattisException("Failed to load file. " + err.getMessage());
        }
        for (String s : result) {
            cattis.getTaskList().add(Task.decode(s));
        }
    }

    /**
     * Save <code>tasks</code> to the target file
     *
     * @param tasks tasks to be saved
     * @throws CattisException If unsuccessfully save the file
     */
    public void save(TaskList tasks) throws CattisException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            if (!created) {
                throw new CattisSaveFileException(
                        "Failed to create directory: " + parentDir.getPath());
            }
        }

        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(tasks.toEncodedString());
        } catch (IOException err) {
            throw new CattisSaveFileException("Failed to save file " + err.getMessage());
        }
    }
}
