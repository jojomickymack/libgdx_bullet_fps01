package org.central.gameobjects

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState

class MotionState(private val transform: Matrix4) : btMotionState() {

    /** For dynamic and static bodies this method is called by bullet once to get the initial state of the body. For kinematic
     * bodies this method is called on every update, unless the body is deactivated.  */
    override fun getWorldTransform(worldTrans: Matrix4) {
        worldTrans.set(transform)
    }

    /** For dynamic bodies this method is called by bullet every update to inform about the new position and rotation.  */
    override fun setWorldTransform(worldTrans: Matrix4) {
        transform.set(worldTrans)
    }
}