package com.virtualpairprogrammers.roombooking.data;

import com.virtualpairprogrammers.roombooking.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
