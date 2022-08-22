package shop.repository;

public interface BaseRepository<T, E> {
    T read(E e);

    void create(T t);

    void update(T t);

    void delete(T t);
}
