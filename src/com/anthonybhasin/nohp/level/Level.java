package com.anthonybhasin.nohp.level;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.anthonybhasin.nohp.GameSettings;
import com.anthonybhasin.nohp.Screen;
import com.anthonybhasin.nohp.level.entity.Camera;
import com.anthonybhasin.nohp.level.entity.DebugRenderable;
import com.anthonybhasin.nohp.level.entity.EngineDebugRenderable;
import com.anthonybhasin.nohp.level.entity.Entity;
import com.anthonybhasin.nohp.level.entity.PostRenderable;

public class Level {

	public static Comparator<Entity> renderSort = new Comparator<Entity>() {

		@Override
		public int compare(Entity e1, Entity e2) {

			int z1 = e1.position.zIndex, z2 = e2.position.zIndex;

			if (z1 < z2) {

				return -1;
			}

			if (z2 < z1) {

				return 1;
			}

			return 0;
		}
	};

	/**
	 * The camera to be used for rendering in {@link com.anthonybhasin.nohp.Screen}
	 */
	public Camera camera;

	private List<Entity> entities, onScreenEntities;

	public Level(Camera camera) {

		this.camera = camera;

		this.entities = new ArrayList<Entity>();
		this.onScreenEntities = new ArrayList<Entity>();
	}

	public void tick() {

		this.onScreenEntities = new ArrayList<Entity>();

		this.camera.tick();

		for (Entity entity : this.entities) {

			boolean onScreen = this.camera.canSee(entity);

			if (onScreen) {

				this.onScreenEntities.add(entity);
			}

			if (entity.isPersistent() || onScreen) {

				entity.tick();
			}

//			Always update the bounds incase an entity comes back into frame.
			entity.bounds = this.camera.getCameraCoordinates(entity);
		}
	}

	public void render() {

		Screen.rotate(this.camera);

		List<PostRenderable> postRenderEntities = new ArrayList<PostRenderable>();

		for (Entity entity : this.onScreenEntities) {

			if (entity instanceof PostRenderable) {

				postRenderEntities.add((PostRenderable) entity);
			}

			entity.render();

			if (GameSettings.engineDebugMode) {

				if (entity instanceof EngineDebugRenderable) {

					((EngineDebugRenderable) entity).engineDebugRender();
				}
			}

			if (GameSettings.debugMode) {

				if (entity instanceof DebugRenderable) {

					((DebugRenderable) entity).debugRender();
				}
			}
		}

		for (PostRenderable postRenderable : postRenderEntities) {

			postRenderable.postRender();
		}

		this.camera.render();
	}

	public void addEntity(Entity e) {

		this.entities.add(e);
	}

	public void removeEntity(Entity e) {

		this.entities.remove(e);
	}
}
