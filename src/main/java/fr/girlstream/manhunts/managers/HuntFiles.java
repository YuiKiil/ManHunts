package fr.girlstream.manhunts.managers;

import fr.girlstream.manhunts.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Logger;

public enum HuntFiles {
    CONFIG("config.yml"),
    LANG("lang.yml");

    private final String fileName;
    private final File dataFolder;


    HuntFiles(String fileName) {
        this.fileName = fileName;
        this.dataFolder = main.getInstance().getDataFolder();
    }

    public void create(Logger logger){
        if(fileName == null || fileName.isEmpty()){
            throw new IllegalArgumentException("Path cannot be empty or null");
        }

        InputStream in = main.getInstance().getResource(fileName);

        if (in == null){
            throw new IllegalArgumentException("Path cannot be find in the plugin jar");
        }

        if(!dataFolder.exists() && !dataFolder.mkdir()){
            logger.severe("Failed to mkdir");
        }

        File outFile = getFile();

        if(!outFile.exists()){
            logger.info("File " + fileName + " was not found, creation in progress");
            try {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int n;

                while ((n = in.read(buf)) >= 0){
                    out.write(buf, 0, n);
                }

                out.close();
                in.close();

                if(!outFile.exists()){
                    logger.severe("Unable to copy resource");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public File getFile() {
        return new File(dataFolder, fileName);
    }

    public FileConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void save(FileConfiguration config){
        try {
            config.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }
}
