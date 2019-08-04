package org.central.input

import com.badlogic.gdx.controllers.PovDirection

// This code was taken from http://www.java-gaming.org/index.php?topic=29223.0
// With thanks that is!

class XBox360Pad {
    /*
     * It seems there are different versions of gamepads with different ID
     Strings.
     * Therefore its IMO a better bet to check for:
     * if (controller.getName().toLowerCase().contains("xbox") &&
                   controller.getName().contains("360"))
     *
     * Controller (Gamepad for Xbox 360)
       Controller (XBOX 360 For Windows)
       Controller (Xbox 360 Wireless Receiver for Windows)
       Controller (Xbox wireless receiver for windows)
       XBOX 360 For Windows (Controller)
       Xbox 360 Wireless Receiver
       Xbox Receiver for Windows (Wireless Controller)
       Xbox wireless receiver for windows (Controller)
     */
    //val String ID = "XBOX 360 For Windows (Controller)";
    companion object {
        val BUTTON_X: Int = 2
        val BUTTON_Y: Int = 3
        val BUTTON_A: Int = 0
        val BUTTON_B: Int = 1
        val BUTTON_BACK: Int = 6
        val BUTTON_START: Int = 7
        val BUTTON_DPAD_UP:PovDirection  = PovDirection.north
        val BUTTON_DPAD_DOWN: PovDirection = PovDirection.south
        val BUTTON_DPAD_RIGHT: PovDirection = PovDirection.east
        val BUTTON_DPAD_LEFT: PovDirection = PovDirection.west
        val BUTTON_DPAD_CENTER: PovDirection = PovDirection.center
        val BUTTON_LB: Int = 4
        val BUTTON_L3: Int= 8
        val BUTTON_RB: Int = 5
        val BUTTON_R3: Int = 9
        val AXIS_LEFT_X: Int = 1 //-1 is left | +1 is right
        val AXIS_LEFT_Y: Int = 0 //-1 is up | +1 is down
        val AXIS_LEFT_TRIGGER: Int = 4 //value 0 to 1f
        val AXIS_RIGHT_X: Int = 3 //-1 is left | +1 is right
        val AXIS_RIGHT_Y: Int = 2 //-1 is up | +1 is down
        val AXIS_RIGHT_TRIGGER: Int = 4 //value 0 to -1f
    }
}
