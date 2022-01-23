package lesson1;

public class Robot implements Move {
    private String model;
    private int runLength;
    private float jumpHeight;

    public Robot(int runLength, float jumpHeight) {
        this.model = "Робот";
        this.runLength = runLength;
        this.jumpHeight = jumpHeight;
    }

    public String getModel() {
        return model;
    }

    public int getRunLength() {
        return runLength;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }
}
