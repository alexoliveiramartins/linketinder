package dao

interface IDao<T> {
    void add(T object)
    void delete(int id)
    void update(String campo, String novo, int id)
    T get(int id)
}
