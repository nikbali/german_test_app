package Behavior;

/**
 * Абстрактный класс Create, задает алгоритм создания сущности
 * @author Nikita Balily
 * */

public abstract class Create {
    public final void run()
    {
        init();
    }

    protected abstract void init();
}
