package ru.technoserv.services;

import java.time.ZonedDateTime;

public class CurrentTime implements DataTimeService {

    @Override
    public ZonedDateTime getCurrentTime() {
        return ZonedDateTime.now();
    }
}
