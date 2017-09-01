create table EMPLOYEE
(
	EMP_ID NUMBER not null
		constraint EMPLOYEE_PK
			primary key,
	POSITION_ID NUMBER,
	GRADE_ID NUMBER default 4,
	DEPARTMENT_ID NUMBER not null,
	LAST_NAME VARCHAR2(50) default NULL not null,
	FIRST_NAME VARCHAR2(50) default NULL not null,
	PATR_NAME VARCHAR2(50) default NULL,
	GENDER CHAR(2) default NULL  not null,
	BIRTHDAY DATE,
	SALARY NUMBER(10,2) default NULL      not null
)
/

create index EMPLOYEE_POSITION_ID_IDX
	on EMPLOYEE (POSITION_ID)
/

create index EMPLOYEE_GRADE_ID_IDX
	on EMPLOYEE (GRADE_ID)
/

create index EMPLOYEE_DEPARTMENT_ID_IDX
	on EMPLOYEE (DEPARTMENT_ID)
/

create table CERTIFICATE
(
	CERT_NUMBER NUMBER(16) not null
		constraint CERTIFICATE_PK
			primary key,
	EMPLOYEE_ID NUMBER not null
		constraint CERTIFICATE_EMPLOYEE_FK
			references EMPLOYEE,
	ISSUE_DATE DATE not null,
	ISSUER_NAME VARCHAR2(50) not null,
	CERT_NAME VARCHAR2(50) not null,
	IMAGE BLOB
)
/

create index CERTIFICATE_EMPLOYEE_ID_IDX
	on CERTIFICATE (EMPLOYEE_ID)
/

create table DEPARTMENT
(
	DEPT_ID NUMBER not null
		constraint DEPARTMENT_PK
			primary key,
	PARENT_DEPT_ID NUMBER
		constraint FK_PARENT_DEPARTMENT
			references DEPARTMENT,
	DEPT_NAME VARCHAR2(100) default NULL  not null,
	DEPT_HEAD_ID NUMBER
		constraint EMPLOYEE_FK
			references EMPLOYEE
)
/

create unique index DEPARTMENT__IDX
	on DEPARTMENT (EMPLOYEE_ID)
/

create unique index DEPARTMENT_DEPT_NAME_UINDEX
	on DEPARTMENT (DEPT_NAME)
/

alter table EMPLOYEE
	add constraint FK_EMPLOYEE_DEPARTMENT
		foreign key (DEPARTMENT_ID) references DEPARTMENT
/

create table GRADE
(
	GRD_ID NUMBER not null
		constraint GRADE_PK
			primary key,
	DESCRIPTION VARCHAR2(50)
)
/

alter table EMPLOYEE
	add constraint FK_EMPLOYEE_GRADE
		foreign key (GRADE_ID) references GRADE
/

create table POSITION
(
	POS_ID NUMBER not null
		constraint POSITION_PK
			primary key,
	TITLE VARCHAR2(50)
)
/

alter table EMPLOYEE
	add constraint FK_EMPLOYEE_POSITION
		foreign key (POSITION_ID) references POSITION
/

