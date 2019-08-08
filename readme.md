# 3d First Person Shooter Style Game In Libgdx Using Bullet

![3d_game.gif](.github/3d_game.gif?raw=true)

This single screen prototype is a mix of these examples

- the [libgdx shooter test](https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests/src/com/badlogic/gdx/tests/bullet/ShootTest.java) 

- the prototype for space gladiators described in [Building a 3d Game With Libgdx](https://www.packtpub.com/game-development/building-3d-game-libgdx)

The libgdx tests uses all RididBodies, which works very nicely. If you check out 
[this branch](https://github.com/jojomickymack/libgdx_bullet_fps01/tree/btRigidBody_player) you can see that 
implemented with this demo - the player is a btRigidBody just like every other object. The book and this demo uses 
btKinematicCharacterController, which is different in these ways.

- btKinematicCharacterController is supposed to 'slide' up and down slopes without tipping over - it's always upright

- it has methods like 'setWalkDirection' and 'jump' instead of having forces applied to it with 'applyCentralImpulse' 
as a btRigidBody would 

- it uses a GhostObject for filtering collisions (complicated to explain why, it's the ghostObject that's added to 
the physics world using the collisionWorld.addCollisionObject method instead of collisionWorld.addRigidBody)

- with bullet 2.83 and above, it has a messed up coordinate system that makes the character controller fly off to the 
left unless you override the default 'up' direction - see [this issue](https://github.com/DeeepGames/SpaceGladiators/issues/1).

Why bother with the character controller at all? Well, it's really nice. Compare for yourself by checking out 
[this branch](https://github.com/jojomickymack/libgdx_bullet_fps01/tree/btRigidBody_player)

The book uses [Ashley](https://github.com/libgdx/ashley), an entity component system. While this is practical for a 
lot of reasons, it makes the code pertaining to bullet specifically very difficult to locate and follow.

Also used in this is the technique of storing GdxArrays of [BulletEntities](https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests/src/com/badlogic/gdx/tests/bullet/BulletEntity.java), their 
constructors, and the models - this is how all of the [tests](https://github.com/libgdx/libgdx/tree/master/tests/gdx-tests/src/com/badlogic/gdx/tests/bullet) work. The constructors are not used for 
anything, however if they aren't kept in scope, there can be issues with the Java garbage collector cleaning them up when it's not expected, especially on Android. 

This will result in only a message appearing in the debugger, but highlights something important about working with Bullet - it's a c++ native 
library, and all objects created from a btSomeObjectName need to be kept in scope or they can disappear on you and cause your game to crash. 
Keeping these objects in these Gdx arrays will prevent that from happening.

Keep in mind that this example includes support for gamepads and and on screen buttons for android. If you don't have a use for those, it's 
simple to remove, just delete the 'input' directories in android/assets and core/src/org.central - all references to it will turn red. Uncomment 
the freelook binding to the mouse in the updateMovement method in GameScreen, and it should work much better on desktop.

The 3d capabilities of Libgdx are very nice - but there's few examples of how to use them correctly. Bullet is a very powerful tool for physics 
simulations, and it's great that libgdx includes it - I do hope that this example might help others get it up and running and create some cool 
3d games!
