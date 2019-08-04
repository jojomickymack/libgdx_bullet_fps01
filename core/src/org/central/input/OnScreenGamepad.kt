package org.central.input

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
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

    init {
        aImg.setSize(buttonWidth, buttonHeight)
        bImg.setSize(buttonWidth, buttonHeight)
        rightImg.setSize(buttonWidth, buttonHeight)
        leftImg.setSize(buttonWidth, buttonHeight)

        this.row().pad(5f, 5f, 5f, 5f)
        this.add(aImg).size(aImg.width, aImg.height)
        this.add(bImg).size(bImg.width, bImg.height)
        this.add(leftImg).size(leftImg.width, leftImg.height).padLeft(app.hudStg.width - aImg.width - bImg.width - leftImg.width - rightImg.width - 40f)
        this.add(rightImg).size(rightImg.width, rightImg.height)
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

    fun dispose() {
        this.aTex.dispose()
        this.bTex.dispose()
        this.rightTex.dispose()
        this.leftTex.dispose()
    }
}