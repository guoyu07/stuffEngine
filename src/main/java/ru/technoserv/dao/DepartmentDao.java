package ru.technoserv.dao;

import java.util.List;

public interface DepartmentDao {
    /**
     * Создание нового отдела
     *
     * @param department подразделение, которое должно быть
     *                   создано
     */
    void create(Department department);

    /**
     * Поиск отдела по id
     *
     * @param depId id искомого отдела
     * @return      отдел, id которого равняется depId
     */
    Department read(Long depId);

    /**
     * Переподчинение отдела, id которого равняется depId,
     * другому отделу, id которого равняется newParentDeptId
     *
     * @param newParentDeptId id отдела, которому должен
     *                        быть переподчинен отдел с id = depId
     * @param depId           id переподчиняемого отдела
     */
    void updateParentDeptId(Long newParentDeptId, Long depId);

    /**
     * Удаление отдела, id которого равняется depId
     *
     * @param depId id удаляемого отдела
     */
    void delete(Long depId);

    /**
     * Поиск всех отделов
     *
     * @return список всех отделов
     */
    List<Department> getDepartmentsList();

    /**
     * Поиск всех дочерних отделов на всех уровнях иерархии
     * отдела, id которого равняется depId
     *
     * @param depId id отдела, в отношении которого ведется
     *              поиск дочерних отделов
     * @return      список всех дочерних отделов на всех
     *              уровнях иерархии отдела, id которого
     *              равняется depId
     */
    List<Department> getAllSubDepts(Long depId);

    /**
     * Поиск всех дочерних отделов на один уровень ниже в
     * иерархии отдела, id которого равняется depId
     *
     * @param depId id отдела, в отношении которого ведется
     *              поиск дочерних отделов
     * @return      список всех дочерних отделов на один
     *              уровень ниже в иерархии отдела, id
     *              которого равняется depId
     */
    List<Department> getLevelBelowSubDepts(Long depId);
}
