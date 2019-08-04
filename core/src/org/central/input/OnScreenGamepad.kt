package org.central.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.central.App

class OnScreenGamepad(app: App): Table() {
    private val buttonWidth = 100f
    private val buttonHeight = 100f

    private val aTex = Texture("input/ctrl_a.png")
    private val bTex = Texture("input/ctrl_b.png")
    private val rightTex = Texture("input/ctrl_r.png")
    private val leftTex = Texture("input/ctrl_l.png")

    private val aImg = Image(aTex)
    private val bImg = Image(bTex)
    private val rightImg = Image(rightTex)
    private val leftImg = Image(leftTex)

    private val knobTex = Texture(Gdx.files.internal("input/ctrl_knob.png"))
    private val padTex = Texture(Gdx.files.internal("input/ctrl_pad.png"))

    private val touchpadStyle = Touchpad.TouchpadStyle()
    private var leftPad: Touchpad
    private var rightPad: Touchpad
    private var leftVector = Vector2(0f, 0f)
    private var rightVector = Vector2(0f, 0f)

    init {
        aImg.setSize(buttonWidth, buttonHeight)
        bImg.setSize(buttonWidth, buttonHeight)
        rightImg.setSize(buttonWidth, buttonHeight)
        leftImg.setSize(buttonWidth, buttonHeight)

        touchpadStyle.knob = TextureRegionDrawable(TextureRegion(knobTex))
        touchpadStyle.knob.minWidth = 64f
        touchpadStyle.knob.minHeight = 64f

        touchpadStyle.background = TextureRegionDrawable(TextureRegion(padTex))
        touchpadStyle.background.minWidth = 64f
        touchpadStyle.background.minHeight = 64f

        leftPad = Touchpad(10f, touchpadStyle)
        rightPad = Touchpad(10f, touchpadStyle)

        leftPad.setSize(buttonWidth, buttonHeight)
        rightPad.setSize(buttonWidth, buttonHeight)

// for debugging - difficult to tell the pads apart otherwise
//        rightPad.color = Color(255f, 0f, 0f, 1f)

        this.row().pad(5f, 5f, 5f, 5f)
//        this.add(aImg).size(aImg.width, aImg.height)
//        this.add(bImg).size(bImg.width, bImg.height)
//        this.add(leftImg).size(leftImg.width, leftImg.height).padLeft(app.hudStg.width - aImg.width - bImg.width - leftImg.width - rightImg.width - 40f)
//        this.add(rightImg).size(rightImg.width, rightImg.height)
        this.add(leftPad).size(leftPad.width, leftPad.height).padLeft(40f)
        this.add(rightPad).size(rightPad.width, rightPad.height).padLeft(app.hudStg.width - leftPad.width - rightPad.width - 80f)
        this.bottom().left()

        aImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                app.ic.aPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                app.ic.aPressed = false
            }
        })

        bImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                app.ic.bPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                app.ic.bPressed = false
            }
        })

        rightImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                app.ic.rPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                app.ic.rPressed = false
            }
        })

        leftImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                app.ic.lPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                app.ic.lPressed = false
            }
        })
    }

    val leftPadVector: Vector2
        get() = leftVector.set(leftPad.knobPercentX, leftPad.knobPercentY)

    val rightPadVector: Vector2
        get() = rightVector.set(rightPad.knobPercentX, rightPad.knobPercentY)

    fun dispose() {
        this.aTex.dispose()
        this.bTex.dispose()
        this.rightTex.dispose()
        this.leftTex.dispose()
        this.knobTex.dispose()
        this.padTex.dispose()
    }
}