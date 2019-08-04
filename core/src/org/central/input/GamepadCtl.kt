package org.central.input

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerListener
import com.badlogic.gdx.controllers.Controllers
import com.badlogic.gdx.controllers.PovDirection
import com.badlogic.gdx.math.Vector3
import org.central.App


class GamepadCtl(app: App) : ControllerListener {

    var hasController = false

    init {
        if (Controllers.getControllers().size != 0) {
            println("there is a gamepad")
            hasController = true
            val controller = Controllers.getControllers().first()
            hasController = true

            Controllers.addListener(object : ControllerListener {
                fun indexOf(controller: Controller): Int {
                    return Controllers.getControllers().indexOf(controller, true)
                }

                override fun connected(controller: Controller) {
                    println("connected " + controller.name)
                    var i = 0
                    for (c in Controllers.getControllers()) {
                        println("#" + i++ + ": " + c.name)
                    }
                }

                override fun disconnected(controller: Controller) {
                    println("disconnected " + controller.name)
                    var i = 0
                    for (c in Controllers.getControllers()) {
                        println("#" + i++ + ": " + c.name)
                    }
                    if (Controllers.getControllers().size == 0) println("No controllers attached")
                }

                override fun buttonDown(controller: Controller, buttonIndex: Int): Boolean {
                    println("#" + indexOf(controller) + ", button " + buttonIndex + " down")
                    when (buttonIndex) {
                        XBox360Pad.BUTTON_A,
                        AndroidPad.BUTTON_A -> {
                            app.ic.aPressed = true
                        }
                        XBox360Pad.BUTTON_B,
                        AndroidPad.BUTTON_B-> {
                            app.ic.bPressed = true
                        }
                    }
                    return false
                }

                override fun buttonUp(controller: Controller, buttonIndex: Int): Boolean {
                    //println("#" + indexOf(controller) + ", button " + buttonIndex + " up")
                    when (buttonIndex) {
                        XBox360Pad.BUTTON_A,
                        AndroidPad.BUTTON_A -> {
                            app.ic.aPressed = false
                        }
                        XBox360Pad.BUTTON_B,
                        AndroidPad.BUTTON_B -> {
                            app.ic.bPressed = false
                        }
                    }
                    return false
                }

                override fun axisMoved(controller: Controller, axisIndex: Int, value: Float): Boolean {
                    println("#" + indexOf(controller) + ", axis " + axisIndex + ": " + value)
                    when (axisIndex) {
                        XBox360Pad.AXIS_LEFT_X -> {
                            if (value < -0.2) {
                                app.ic.lPressed = true
                                app.ic.rPressed = false
                            } else if (value > 0.2) {
                                app.ic.lPressed = false
                                app.ic.rPressed = true
                            } else {
                                app.ic.lPressed = false
                                app.ic.rPressed = false
                            }
                        }
                        AndroidPad.AXIS_LEFT_X -> {
                            if (value < -0.2) {
                                app.ic.lPressed = true
                                app.ic.rPressed = false
                            } else if (value > 0.2) {
                                app.ic.lPressed = false
                                app.ic.rPressed = true
                            } else {
                                app.ic.lPressed = false
                                app.ic.rPressed = false
                            }
                        }
                    }

                    return false
                }

                override fun povMoved(controller: Controller, povIndex: Int, value: PovDirection): Boolean {
                    println("#" + indexOf(controller) + ", pov " + povIndex + ": " + value)
                    if (povIndex == 0) {
                        when (value) {
                            XBox360Pad.BUTTON_DPAD_LEFT -> {
                                app.ic.lPressed = true
                                app.ic.rPressed = false
                            }
                            XBox360Pad.BUTTON_DPAD_RIGHT -> {
                                app.ic.lPressed = false
                                app.ic.rPressed = true
                            }
                            XBox360Pad.BUTTON_DPAD_CENTER -> {
                                app.ic.lPressed = false
                                app.ic.rPressed = false
                            }
                        }
                    }
                    return false
                }

                override fun xSliderMoved(controller: Controller, sliderIndex: Int, value: Boolean): Boolean {
                    println("#" + indexOf(controller) + ", x slider " + sliderIndex + ": " + value)
                    return false
                }

                override fun ySliderMoved(controller: Controller, sliderIndex: Int, value: Boolean): Boolean {
                    println("#" + indexOf(controller) + ", y slider " + sliderIndex + ": " + value)
                    return false
                }

                override fun accelerometerMoved(controller: Controller, accelerometerIndex: Int, value: Vector3): Boolean {
                    // not printlning this as we get to many values
                    return false
                }
            })
        }
    }

    override fun connected(controller: Controller) {}

    override fun disconnected(controller: Controller) {}

    override fun buttonDown(controller: Controller?, buttonCode: Int): Boolean {
        return controller?.getButton(buttonCode) == true
    }

    override fun buttonUp(controller: Controller, buttonCode: Int): Boolean {
        return true
    }

    override fun xSliderMoved(controller: Controller, buttonCode: Int, value: Boolean): Boolean {
        return true
    }

    override fun ySliderMoved(controller: Controller, buttonCode: Int, value: Boolean): Boolean {
        return true
    }

    override fun accelerometerMoved(controller: Controller, accelerometerCode: Int, value: Vector3): Boolean {
        return true
    }

    override fun axisMoved(controller: Controller, axisCode: Int, value: Float): Boolean {
        return true
    }

    override fun povMoved(controller: Controller, povCode: Int, direction: PovDirection): Boolean {
        return true
    }
}