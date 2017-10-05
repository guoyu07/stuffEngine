package ru.technoserv.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.technoserv.domain.AuditInfo
import ru.technoserv.services.AuditService
import java.time.Instant
import java.util.*

@RestController
@RequestMapping(value="audit", produces = arrayOf("application/json"))
open class AuditController (private val service : AuditService){

    @RequestMapping(name="14", value="/getInfoEmp/{depId}", method = arrayOf(RequestMethod.GET))
    fun getDepAudit(@PathVariable depId: Int) : List<AuditInfo>{
        return service.getRecordsOfPeriodForEmployee(Date.from(Instant.now()), Date.from(Instant.now()),depId)
    }

    @RequestMapping(name="15", value="/getInfoDep/{empId}", method = arrayOf(RequestMethod.GET))
    fun getEmpAudit(@PathVariable empId: Int) : List<AuditInfo>{
        return service.getRecordsOfPeriodForEmployee(Date.from(Instant.now()), Date.from(Instant.now()),empId)
    }

}