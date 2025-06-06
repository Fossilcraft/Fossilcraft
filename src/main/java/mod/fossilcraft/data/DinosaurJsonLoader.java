package mod.fossilcraft.data;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.dinosaur.Dinosaur;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public final class DinosaurJsonLoader {

    private static final Gson GSON = new Gson();

    public static List<Dinosaur> load() {
        List<Dinosaur> dinosaurs = new java.util.ArrayList<>();

        try {
            ModContainer container = FabricLoader.getInstance()
                    .getModContainer(FossilCraftMod.MOD_ID)
                    .orElseThrow();

            Path modelDir = container.findPath("assets/fossilcraft/dinosaurs").orElse(null);

            if (modelDir == null || !Files.exists(modelDir)) {
                FossilCraftMod.LOGGER.error("Dinosaur model directory is missing");
                return dinosaurs;
            }

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(modelDir, "*.json")) {
                for (Path path : stream) {
                    if (!Files.isRegularFile(path)) {
                        continue;
                    }

                    try {
                        Dinosaur dinosaur = GSON.fromJson(Files.newBufferedReader(path), Dinosaur.class);

                        if (dinosaur == null) {
                            FossilCraftMod.LOGGER.error("Failed to parse dinosaur JSON model: " + path);
                            continue;
                        }

                        dinosaurs.add(dinosaur);

                    } catch (JsonSyntaxException e) {
                        FossilCraftMod.LOGGER.error("Malformed dinosaur JSON model: " + path, e);
                        continue;
                    } catch (Exception e) {
                        FossilCraftMod.LOGGER.error("Error reading dinosaur JSON model: " + path, e);
                        continue;
                    }
                }
            }

        } catch (Exception e) {
            FossilCraftMod.LOGGER.error("Failed loading dinosaur definitions", e);
            return dinosaurs;
        }

        return dinosaurs;
    }
}
