package me.guy.dbca.animations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class AnimationLoader {

    public static AnimationData loadAnimationData(String path) {

        InputStream stream = null;

        try  {
            ResourceLocation location = new ResourceLocation("dbca", path);
            // Get the input stream for the resource using Minecraft's resource manager
            stream = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
            // Convert the input stream to a String
            String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
            // Parse the JSON into a JsonObject using Gson
            JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
            // Create CustomAnimationData instanc
            AnimationData animationList = new AnimationData(jsonObject);
            // Now you can work with the parsed AnimationList object
//            for (AnimationData.Animation animation : animationList.getAnimations()) {
//                System.out.println("Bone: " + animation.getBone());
//                System.out.println("Target: " + animation.getTarget());
//                System.out.println("Keyframes:");
//                for (AnimationData.Keyframe keyframe : animation.getKeyframes()) {
//                    System.out.println("\tTimestamp: " + keyframe.getTimestamp());
//                    System.out.println("\tTarget: " + keyframe.getTarget()[0] + ", " + keyframe.getTarget()[1] + ", " + keyframe.getTarget()[2]);
//                    System.out.println("\tInterpolation: " + keyframe.getInterpolation());
//                    System.out.println();
//                }
//            }
            return animationList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Example usage


}