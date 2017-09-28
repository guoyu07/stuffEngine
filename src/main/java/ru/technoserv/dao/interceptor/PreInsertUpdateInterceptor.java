package ru.technoserv.dao.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import ru.technoserv.domain.EmployeeHistory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PreInsertUpdateInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof EmployeeHistory) {
            EmployeeHistory eh = (EmployeeHistory) entity;
            Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());

            if(eh.getEmpID() == null)
              //  eh.setEmpID(eh.getId());

            if(eh.getStartDate() == null)
                eh.setStartDate(currentTime);

            eh.setActive(true);
            eh.setDeleted(false);

            for (int i = 0; i < propertyNames.length; i++) {

                if("empID".equals(propertyNames[i]))
                    state[i] = eh.getEmpID();

                if("isActive".equals(propertyNames[i]))
                    state[i] = true;

                if("isDeleted".equals(propertyNames[i]))
                    state[i] = false;

                if("startDate".equals(propertyNames[i]))
                    state[i] = currentTime;

            }
        }

        return super.onSave(entity, id, state, propertyNames, types);
    }
}
