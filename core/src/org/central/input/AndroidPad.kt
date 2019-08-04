package org.central.input

import com.badlogic.gdx.controllers.PovDirection

class AndroidPad {

    companion object {
        val BUTTON_X: Int = 99
        val BUTTON_Y: Int = 98
        val BUTTON_A: Int = 97
        val BUTTON_B: Int = 96
        val BUTTON_BACK: Int = 82
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
        val AXIS_LEFT_X: Int = 0 //-1 is left | +1 is right
        val AXIS_LEFT_Y: Int = 1 //-1 is up | +1 is down
        val AXIS_LEFT_TRIGGER: Int = 4 //value 0 to 1f
        val AXIS_RIGHT_X: Int = 3 //-1 is left | +1 is right
        val AXIS_RIGHT_Y: Int = 2 //-1 is up | +1 is down
        val AXIS_RIGHT_TRIGGER: Int = 4 //value 0 to -1f
    }
}
