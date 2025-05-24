package com.poo.game.Interfaces;

import com.poo.game.BaseComponents.AEntityComponent;

public interface IInteractable {

    boolean CanInteract();

    void Interact(AEntityComponent AEntityComponent);
}
