CREATE TABLE IF NOT EXIST courses (
    id bigint not null auto_increment,
    course_code varchar(10) not null,
    course_title varchar(60) not null,
    course_description varchar(300) not null,
    created_at Date not null,
    primary key (id)
)

CREATE TABLE IF NOT EXIST session (
    id bigint not null auto_increment,
    course_id bigint not null,
    year_of_delivery integer not null,
    semester_of_delivery integer not null,
    primary key (id)
) 

ALTER TABLE courses 
    ADD CONSTRAINT UKp02ts69sh53ptd62m3c67v0 
    UNIQUE (course_code)

ALTER TABLE session 
    ADD CONSTRAINT FKde01hisfl6rwwskjaslge4kj2 
    FOREIGN KEY (course_id) 
    REFERENCES courses (id) 
    ON DELETE CASCADE