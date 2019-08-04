package org.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider
import com.badlogic.gdx.physics.bullet.Bullet
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import ktx.app.KtxGame
import org.central.input.GamepadCtl
import org.central.input.InputCtl
import org.central.input.OnScreenGamepad
import org.central.screens.GameScreen


class App : KtxGame<Screen>() {

    var width = 0f
    var height = 0f
    var portrait = true

    val smallerDimension = 360f
    val largerDimension = 785f

    private lateinit var sb: SpriteBatch
    lateinit var hudSb: SpriteBatch

    lateinit var shadowBatch: ModelBatch
    lateinit var modelBatch: ModelBatch

    private lateinit var view: StretchViewport
    lateinit var hudView: StretchViewport

    lateinit var cam: OrthographicCamera
    lateinit var modelStgCam: PerspectiveCamera
    lateinit var hudCam: OrthographicCamera

    lateinit var stg: Stage
    lateinit var modelStgView: StretchViewport
    lateinit var modelStg: Stage
    lateinit var hudStg: Stage

    lateinit var collisionConfiguration: btDefaultCollisionConfiguration
    lateinit var dispatcher: btCollisionDispatcher
    lateinit var broadphase: btDbvtBroadphase
    lateinit var solver: btSequentialImpulseConstraintSolver
    lateinit var collisionWorld: btDiscreteDynamicsWorld

    lateinit var ic: InputCtl
    lateinit var osc: OnScreenGamepad

    /**
     * this class makes it so the game listens for physical gamepad input
     * it works like magic, just instantiating results in gamepad events getting processed
     * see ControllerListener
     * https://github.com/libgdx/libgdx/wiki/Controllers
     */
    lateinit var gpc: GamepadCtl

    override fun create() {
        width = Gdx.graphics.width.toFloat()
        height = Gdx.graphics.height.toFloat()

        this.sb = SpriteBatch()

        this.shadowBatch = ModelBatch(DepthShaderProvider())
        this.modelBatch = ModelBatch()
        this.hudSb = SpriteBatch()

        this.portrait = width < height

        this.cam = OrthographicCamera(this.width, this.height)
        this.view = if (portrait) StretchViewport(this.smallerDimension, this.largerDimension, this.cam) else StretchViewport(this.largerDimension, this.smallerDimension, this.cam)
        this.stg = Stage(this.view, this.sb)

        this.modelStgCam = PerspectiveCamera(10f, this.width, this.height)
        this.modelStgView = if (portrait) StretchViewport(this.smallerDimension, this.largerDimension, this.modelStgCam) else StretchViewport(this.largerDimension, this.smallerDimension, this.modelStgCam)
        this.modelStg = Stage(modelStgView)

        this.hudCam = OrthographicCamera(this.width, this.height)
        this.hudView = StretchViewport(480f, 360f, this.hudCam)
        this.hudStg = Stage(this.hudView , this.hudSb)

        Bullet.init(true)
        this.collisionConfiguration = btDefaultCollisionConfiguration()
        this.dispatcher = btCollisionDispatcher(collisionConfiguration)
        this.broadphase = btDbvtBroadphase()
        this.solver = btSequentialImpulseConstraintSolver()
        this.collisionWorld = btDiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration)

        this.ic = InputCtl(this)
        this.osc = OnScreenGamepad(this)
        this.gpc = GamepadCtl(this)

        val game = GameScreen(this)

        addScreen(game)
        setScreen<GameScreen>()
    }

    override fun dispose() {
        this.collisionWorld.dispose()
        this.solver.dispose()
        this.broadphase.dispose()
        this.dispatcher.dispose()
        this.collisionConfiguration.dispose()

        this.sb.dispose()
        this.shadowBatch.dispose()
        this.modelBatch.dispose()
        this.hudSb.dispose()

        this.stg.dispose()
        this.modelStg.dispose()
        this.hudStg.dispose()

        this.osc.dispose()

        super.dispose()
    }
}
