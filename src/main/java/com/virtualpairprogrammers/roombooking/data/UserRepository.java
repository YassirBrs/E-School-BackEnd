package com.virtualpairprogrammers.roombooking.data;

import com.virtualpairprogrammers.roombooking.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Integer> {
//    @RestResource(path="/byCIN")
//    public List<User> findByCINContains(@Param("key") String cin);

}
