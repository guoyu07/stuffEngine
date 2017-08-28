package ru.technoserv.services;

import java.time.ZonedDateTime;

/**
 * Класс для получения текущего времени. Используется для аудита
 */
public class CurrentTime implements DataTimeService {

    /**
     * Возвращает текущее время
     * @return текущее время
     */
    @Override
    public ZonedDateTime getCurrentTime() {
        return ZonedDateTime.now();
    }
}
