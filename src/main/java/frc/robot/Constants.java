package frc.robot;

/**
 * <h3>ALL MEMBERS ARE REQUIRED TO MAINTAIN THIS CLASS</h3>
 * This class is meant to store all non-electrical/port values so that they can
 * easily be modified and viewed here.
 * <p>
 * Also, methods used in multiple places can be stored here as well.
 */
public final class Constants {
    public static final int    PILOT_JOYSTICK   = 0;
    public static final int    COPILOT_JOYSTICK = 1;

    public static final double DRIVE_SPEED      = 0.5D;
    public static final double DEAD_ZONE        = 0.01D;

    private Constants() {}

    public static double squareKeepSign(double num) {
        if (num < 0D) {
            return -Math.pow(num, 2);
        } else {
            return Math.pow(num, 2);
        }
    }
}
