package org.central.gameobjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.math.collision.BoundingBox
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody


class BulletEntity(modelInstance: ModelInstance, var body: btRigidBody?) {
    var transform: Matrix4? = null
    var modelInstance: ModelInstance? = null
    var color = Color(1f, 1f, 1f, 1f)
        set(color) = setColor(color.r, color.g, color.b, color.a)

    var motionState: MotionState? = null

    val boundingBox = BoundingBox()
    val boundingBoxRadius: Float

    init {
        this.modelInstance = modelInstance
        this.transform = this.modelInstance!!.transform

        modelInstance.calculateBoundingBox(boundingBox)
        boundingBoxRadius = boundingBox.getDimensions(Vector3()).len() * 0.5f

        if (body != null) {
            body!!.userData = this
            if (body is btRigidBody) {
                this.motionState = MotionState(this.modelInstance!!.transform)
                (this.body as btRigidBody).motionState = motionState
            } else
                body!!.worldTransform = transform
        }
    }

    fun setColor(r: Float, g: Float, b: Float, a: Float) {
        this.color.set(r, g, b, a)
        if (modelInstance != null) {
            for (m in modelInstance!!.materials) {
                val ca = m.get(ColorAttribute.Diffuse) as ColorAttribute
                ca.color?.set(r, g, b, a)
            }
        }
    }

    fun dispose() {
        // Don't rely on the GC
        if (motionState != null) motionState!!.dispose()
        if (body != null) body!!.dispose()
        // And remove the reference
        motionState = null
        body = null
    }
}
