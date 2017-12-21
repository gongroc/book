package org.book.db.mysql.repository;

import org.book.db.mysql.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    @Query(value = "SELECT * FROM student s WHERE s.id <= ?1", nativeQuery = true)
    List<Student> myQuery(long id);

    @Query("select s from Student s where s.id <= ?1")
    List<Student> myQuery2(long id);

    @Query(value = "select * from student \n#pageable\n ", countQuery = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Page<Student> myQueryAll(Pageable pageable);

    @Modifying
    @Query("update Student s set s.name = :name where s.id = :id")
    void myUpdate(@Param("name") String name, @Param("id") int id);

    @Modifying
    @Query(value = "delete from Student WHERE id = :id", nativeQuery = true)
    void myDelete(@Param("id") long id);
}
