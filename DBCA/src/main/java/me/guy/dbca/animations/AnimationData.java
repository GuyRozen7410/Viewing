package me.guy.dbca.animations;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.lwjgl.Sys;

public class AnimationData {

    private static float length;
    private Animation[] animations;

    public AnimationData(JsonObject json) {
        parseJson(json);
    }

    private void parseJson(JsonObject json) {
        length = json.get("length").getAsFloat();
        JsonArray animationsArray = json.getAsJsonArray("animations");
        animations = new Animation[animationsArray.size()];

        for (int i = 0; i < animationsArray.size(); i++) {
            animations[i] = new Animation(animationsArray.get(i).getAsJsonObject());
        }
    }

    public static float getLength() {
        return length;
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public static class Animation {
        private final String bone;
        private final String target;
        private final Keyframe[] keyframes;

        public Animation(JsonObject json) {
            bone = json.get("bone").getAsString();
            target = json.get("target").getAsString();
            JsonArray keyframesArray = json.getAsJsonArray("keyframes");

            keyframes = new Keyframe[keyframesArray.size()];

            for (int i = 0; i < keyframesArray.size(); i++) {
                keyframes[i] = new Keyframe(keyframesArray.get(i).getAsJsonObject());
            }

        }

        public boolean isActive(float time) {
            // Check if the animation should be active at the given time
            if (keyframes == null || keyframes.length == 0) {
                return false; // No keyframes, animation is not active
            }

            float startTime = keyframes[0].getTimestamp();
            float endTime = keyframes[keyframes.length - 1].getTimestamp();

            return time >= startTime && time <= endTime;
        }
        public boolean isLastKeyframeFired(float time) {
            if (keyframes == null || keyframes.length == 0) {
                return false; // No keyframes, no animation
            }


            boolean lastKeyframeFired = time >= AnimationData.getLength();

            // Debugging output


            return lastKeyframeFired;
        }

        public String getBone() {
            return bone;
        }

        public String getTarget() {
            return target;
        }

        public Keyframe[] getKeyframes() {
            return keyframes;
        }
    }

    public static class Keyframe {
        private final float timestamp;
        private final float[] target;
        private final String interpolation;

        public Keyframe(JsonObject json) {
            timestamp = json.get("timestamp").getAsFloat();
            JsonArray targetArray = json.getAsJsonArray("target");
            target = new float[targetArray.size()];
            for (int i = 0; i < targetArray.size(); i++) {
                target[i] = targetArray.get(i).getAsFloat();
            }
            interpolation = json.get("interpolation").getAsString();
        }

        public float getTimestamp() {
            return timestamp;
        }

        public float[] getTarget() {
            return target;
        }

        public String getInterpolation() {
            return interpolation;
        }
    }
}