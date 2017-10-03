package ru.technoserv.dao;

import ru.technoserv.domain.Department;
import ru.technoserv.domain.EmployeeHistory;

import java.util.List;

public interface DepartmentDao {

    /**
     * Создание нового отдела
     *
     * @param department подразделение, которое должно быть
     *                   создано
     */
    Integer create(Department department);

    /**
     * Поиск отдела по id
     *
     * @param depId id искомого отдела
     * @return      отдел, id которого равняется depId
     */
    Department readById(Integer depId);

    /**
     * Обновление информации по отделу
     *
     * @param department обновляемый отдел
     * @return           обновленный отдел
     */
    Department updateDept(Department department);

    /**
     * Удаление отдела, id которого равняется depId
     *
     * @param depId id удаляемого отдела
     */
    void delete(Integer depId);

    /**
     * Поиск руководителя отдела с id, равным depId
     *
     * @param depId id отдела, в отношении которого ведется
     *              поиск руководителя отдела
     * @return      руководитель отдела с id, равным depId
     */
    EmployeeHistory getDeptHead(Integer depId);

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
    List<Department> getAllSubDepts(Integer depId);

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
    List<Department> getLevelBelowSubDepts(Integer depId);

    List<EmployeeHistory> getDeptEmployees(Integer depId);
}
