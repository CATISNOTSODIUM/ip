import Exceptions.CattisException;
import Exceptions.CattisSaveFileException;
import Task.Task;
import Task.TaskList;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from file filePath
     * @throws CattisException If unsuccessfully load the file.
     */
    public void load(Cattis cattis) throws CattisException {
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
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
        } catch (FileNotFoundException err) {
            throw new CattisException("Cannot find file " + this.filePath);
        } catch (IOException err) {
            throw new CattisException("Failed to load file. " + err.getMessage());
        }
        for (int i = 0; i < result.size(); i++) {
            cattis.getTaskList().add(Task.decode(result.get(i)));
        }
    }

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
