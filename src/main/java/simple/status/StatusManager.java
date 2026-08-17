package simple.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.FileReader;
import java.lang.reflect.Type;

public class StatusManager {
    private final Map<UUID, String> statuses = new HashMap<>();
    private final File file;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public StatusManager(File file) {
        this.file = file;
    }

    public void setStatus(UUID uuid, String status) {
        if (status.isEmpty()) {
            statuses.remove(uuid);
        } else {
            statuses.put(uuid, status);
        }
    }

    public String getStatus(UUID uuid) {
        return statuses.get(uuid);
    }

    public void save() {
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(statuses, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!file.exists()) {
            return;
        }
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> loaded = gson.fromJson(reader, type);
            if (loaded == null) {
                return;
            }
            for (Map.Entry<String, String> entry : loaded.entrySet()) {
                UUID uuid = UUID.fromString(entry.getKey());
                statuses.put(
                        uuid,
                        entry.getValue()
                );
            }
            System.out.println("Statusses loaded: " + statuses.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}