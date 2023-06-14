package levistico.fastleafdecay;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class Main implements ModInitializer {
    public static final String MOD_ID = "fast-leaf-decay";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static int bound = 180;

    @Override
    public void onInitialize() {

        String configName = "config/FastLeafDecay.ini";
        File configFile = new File(configName);
        try {
            if (!configFile.exists()) {
                LOGGER.warn("Config For FastLeafDecay Not Found! Creating new config based upon default value");
                File configDir = new File("config");
                if (!configDir.exists())
                    configDir.mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
                writer.write("" + bound);
                writer.close();
            } else {
                LOGGER.info("FastLeafDecay initialized.");
                BufferedReader reader = new BufferedReader(new FileReader(configFile));
                String currentLine = reader.readLine();
                bound = Integer.parseInt(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
