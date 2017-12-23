package org.book.db.mysql.service;

import org.book.db.mysql.entity.Student;
import org.book.db.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class BusinessService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * repository 示例
     */
    public void studentTest() {
        repository.findAll();
        repository.findAll(new PageRequest(1, 10));
        repository.findOne(1L);
        repository.count();
        repository.exists(1L);
        repository.save(new Student());
        repository.deleteAll();
        repository.delete(1L);

        Page<Student> result = repository.findAll(new PageRequest(1, 10, new Sort(Sort.Direction.DESC, "id")));
        List<Student> content = result.getContent();
        for (Student student : content) {
            System.out.println(student.getName());
        }
        //获得分页总数
        int countPages = result.getTotalPages();
        //获得数据总数
        long countElements = result.getTotalElements();

    }

    /**
     * jdbcTemplate 示例
     */
    public void jdbcTest() {
        // 判断表是否存在，如果存在则删除
        jdbcTemplate.execute("DROP TABLE IF EXISTS account");
        // 新建表
        jdbcTemplate.execute("CREATE TABLE account(id int ,nickname VARCHAR(255) )");
        // 批量插入数据
        jdbcTemplate.batchUpdate("INSERT INTO account(id,nickname) VALUES(?,?) ", Arrays.asList(new Object[]{1, "昵称111"}, new Object[]{2, "昵称222"}));
        // 更新数据
        jdbcTemplate.update("UPDATE account SET nickname = ? WHERE id = ? ", "昵称", 1);
        // 查询数据
        List result = jdbcTemplate.queryForList("select * from account WHERE nickname LIKE ?", "昵称");
        // 批量更新数据
        jdbcTemplate.batchUpdate("UPDATE account SET nickname = ? WHERE id = ?", Arrays.asList(new Object[]{"批量修改111", 1}, new Object[]{"批量修改222", 2}));
    }

    /**
     * 事务示例
     */
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void demo() {
        repository.save(new Student());
        repository.myDelete(1L);
        // 模拟出错，进行事务回滚
        throw new RuntimeException("出错了");
    }


}
