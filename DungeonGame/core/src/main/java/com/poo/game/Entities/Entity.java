/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Components.SpriteRendererComponent;
import com.poo.game.Graph.MapGraph;
import com.poo.game.Interfaces.IInteractable;
import com.poo.game.Interfaces.IRenderableComponent;
import com.poo.game.Interfaces.IUpdatableComponent;
import com.poo.game.Scene.DungeonScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for entities of game (ECS - Entity)
 */
public class Entity {

    public String EntityName;
    public List<Integer> EntityTags;

    public DungeonScene DungeonScene;

    // List of component data for entity
    ArrayList<AEntityComponent> EntityComponents;

    // List of components for faster processing
    private final ArrayList<IUpdatableComponent> UpdateableComponents;
    private final ArrayList<IRenderableComponent> RenderableComponents;
    private final ArrayList<IInteractable> InteractableComponents;

    public Entity(DungeonScene DungeonScene, String EntityName, List<Integer> EntityTags) {
        this.EntityName = EntityName;
        this.EntityTags = EntityTags;
        this.DungeonScene = DungeonScene;
        this.EntityComponents = new ArrayList<>();
        this.UpdateableComponents = new ArrayList<>();
        this.RenderableComponents = new ArrayList<>();
        this.InteractableComponents = new ArrayList<>();
    }

    public List<AEntityComponent> getComponents() {
        return EntityComponents;
    }

    // Generic Types, We Can Expose These Later
    // This way we can search for any type of component
    public <T extends AEntityComponent> T GetFirstComponentOfType(Class<T> ComponentClassType) {
        for (int i = 0; i < EntityComponents.size(); ++i) {
            AEntityComponent Component = EntityComponents.get(i);
            if (Component != null && ComponentClassType.isAssignableFrom(Component.getClass())) {
                return ComponentClassType.cast(Component);
            }
        }

        return null;
    }

    // Add Component
    public <T extends AEntityComponent> T AddComponent(AEntityComponent ComponentToAdd) {
        EntityComponents.add(ComponentToAdd);
        ComponentToAdd.AssignedEntity(this);

        // Cache All Updateable and Renderable Components
        if (ComponentToAdd instanceof IUpdatableComponent)
            UpdateableComponents.add((IUpdatableComponent) ComponentToAdd);

        if (ComponentToAdd instanceof IRenderableComponent)
            RenderableComponents.add((IRenderableComponent) ComponentToAdd);

        if (ComponentToAdd instanceof IInteractable)
            InteractableComponents.add((IInteractable) ComponentToAdd);

        return (T) ComponentToAdd;
    }

    // Remove Component
    public <T extends AEntityComponent> void RemoveFirstComponentOfType(Class<T> ComponentClassType) {
        for (int i = 0; i < EntityComponents.size(); ++i) {
            AEntityComponent Component = EntityComponents.get(i);
            if (Component != null && ComponentClassType.isAssignableFrom(Component.getClass())) {
                EntityComponents.remove(i);

                if (Component instanceof IUpdatableComponent)
                    UpdateableComponents.remove((IUpdatableComponent) Component);

                if (Component instanceof IRenderableComponent)
                    RenderableComponents.remove((IRenderableComponent) Component);

                return;
            }
        }
    }

    public void Start() {
        for (int i = 0; i < EntityComponents.size(); ++i) {
            EntityComponents.get(i).Start();
        }
    }

    public void Update(float DeltaTime) {
        for (int i = 0; i < UpdateableComponents.size(); ++i) {
            IUpdatableComponent CurrentComponent = UpdateableComponents.get(i);
            if (CurrentComponent != null && CurrentComponent.CanUpdate())
                CurrentComponent.Update(DeltaTime);
        }
    }

    public void Render(SpriteBatch Batch) {
        for (int i = 0; i < RenderableComponents.size(); ++i) {
            IRenderableComponent CurrentComponent = RenderableComponents.get(i);
            if (CurrentComponent != null && CurrentComponent.CanRender()) {
                CurrentComponent.Render(Batch);
            }
        }
    }

    public void Interact() {
        for (int i = 0; i < InteractableComponents.size(); ++i) {
            IInteractable CurrentComponent = InteractableComponents.get(i);
            if (CurrentComponent != null && CurrentComponent.CanInteract()) {
                CurrentComponent.Interact();
            }
        }
    }

    public Rectangle getBounds() {
        SpriteRendererComponent spriteRendererComponent = this.GetFirstComponentOfType(SpriteRendererComponent.class);
        if(spriteRendererComponent == null)
            return null;

        return new Rectangle(spriteRendererComponent.SpriteToRender.getX() - 0.5f, spriteRendererComponent.SpriteToRender.getY()  - 0.5f, 1, 1);
    }

}
