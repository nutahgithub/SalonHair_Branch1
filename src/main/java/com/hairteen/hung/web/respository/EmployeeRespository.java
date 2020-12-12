package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Integer>{

    /**
     * Get max id employee.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(ep.id), 0) FROM Employee ep")
    Integer getMaxId();

    /**
     * Find all employee page.
     * 
     */
    @Query("SELECT e from Employee e where e.deleteTsamp IS NULL")
    Page<Employee> findAll(Pageable pageable);

    /**
     * Get list all employee.
     * 
     */
    @Query("SELECT e from Employee e where e.deleteTsamp IS NULL")
    List<Employee> findAll();
}
