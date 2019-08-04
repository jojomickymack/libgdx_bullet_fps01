# 3d First Person Shooter Style Game In Libgdx Using Bullet

![3d_game.gif](.github/3d_game.gif?raw=true)

This single screen prototype is a mix of these examples

- the [libgdx shooter test](https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests/src/com/badlogic/gdx/tests/bullet/ShootTest.java) 

- blunderchip's [first person demo](https://github.com/Blunderchips/LibGDX-First-Person-Demo) 

There are really not a lot of good examples provided for making a first person game using bullet - one notable resource is the packtpub book 
[Building a 3d Game With Libgdx](https://www.packtpub.com/game-development/building-3d-game-libgdx). There are a few things that this 
example does differently than the examples from the book.

- this uses bulletVersion 1.9.8 which introduces a different coordinate system which messes up gravity in the examples - see [this 
issue](https://github.com/DeeepGames/SpaceGladiators/issues/1).

- the book uses [Ashley](https://github.com/libgdx/ashley), an entity component system. While this is practical for a lot of reasons, it makes 
the code pertaining to bullet specifically very difficult to locate and follow.

- the book uses a btKinematicCharacterController to translate inputs to the camera, model, and btRigidBody in the physics world - I could not 
get this approach to work with the structure presented in the libgdx demos.

Like blunderchip's implementation, I applied inputs to the btRigidBody and the camera to push them around - the tmp vector is added to, then 
applied to the player's body, then the camera's position is changed by a 2nd vector. It's confusing, but it works great.

```kotlin
// this is what makes the player move
body.applyCentralForce(tmp)
body.getWorldTransform(transform)
cam.position.set(transform.getTranslation(tmp2))
```

Also used in this is the technique of storing GdxArrays of 
[BulletEntities](https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests/src/com/badlogic/gdx/tests/bullet/BulletEntity.java), their 
constructors, and the models - this is how all of the 
[tests](https://github.com/libgdx/libgdx/tree/master/tests/gdx-tests/src/com/badlogic/gdx/tests/bullet) work. The constructors are not used for 
anything, however if they aren't kept in scope, there can be issues with the Java garbage collector cleaning them up when it's not expected, 
especially on Android. 

This will result in only a message appearing in the debugger, but highlights something important about working with Bullet - it's a c++ native 
library, and all objects created from a btSomeObjectName need to be kept in scope or they can disappear on you and cause your game to crash. 
Keeping these objects in these Gdx arrays will prevent that from happening.

There is an issue you'll notice where if you move the mouse to look around before using the WSDA controls to move yourself, for some reason you 
can't move anymore - I'm not sure why that happens.

Keep in mind that this example includes support for gamepads and and on screen buttons for android. If you don't have a use for those, it's 
simple to remove, just delete the 'input' directories in android/assets and core/src/org.central - all references to it will turn red. Uncomment 
the freelook binding to the mouse in the updateMovement method in GameScreen, and it should work much better on desktop.

The 3d capabilities of Libgdx are very nice - but there's few examples of how to use them correctly. Bullet is a very powerful tool for physics 
simulations, and it's great that libgdx includes it - I do hope that this example might help others get it up and running and create some cool 
3d games!
