package com.poo.game.BaseComponents;

public interface IUpdatableComponent
{
    public void Update(float DeltaTime);
    public boolean CanUpdate();
}
