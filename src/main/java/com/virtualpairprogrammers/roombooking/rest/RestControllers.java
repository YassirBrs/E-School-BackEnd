package com.virtualpairprogrammers.roombooking.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.virtualpairprogrammers.roombooking.ApiClass.ApiRoom;
import com.virtualpairprogrammers.roombooking.model.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.Layout;
import com.virtualpairprogrammers.roombooking.model.entities.LayoutCapacity;
import com.virtualpairprogrammers.roombooking.model.entities.Room;
import com.virtualpairprogrammers.roombooking.model.entities.User;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestControllers {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id")int id) {
		userRepo.deleteById(id);
	}
	
	@DeleteMapping("/deleteRoom/{id}")
	public void deleteRoom(@PathVariable("id") int id) {
		 roomRepository.deleteById(id);
	}
	
	@GetMapping("/rooms")
	public List<Room> getAllRooms(){
		return roomRepository.findAll();		
	}
	
	@GetMapping("/room/{id}")
	public ApiRoom getRoom(@PathVariable("id") int id) {
		Room r1=roomRepository.findById(id).get();
		ApiRoom room=new ApiRoom();
		room.setId(r1.getId());
		room.setName(r1.getName());
		room.setLocation(r1.getLocation());
		room.setSeats(r1.getSeats());
		room.setState(r1.getState().getStates());
		room.setDetail(r1.getState().getDetail());
		for (LayoutCapacity l:r1.getCapacities()){
			if (l.getLayout().getDescription()=="pc")room.setPcs(l.getCapacity());
			if (l.getLayout().getDescription()=="Projector")room.setProjector(l.getCapacity());
			if (l.getLayout().getDescription()=="BOARD")room.setBoard(l.getCapacity());
		}
		return room;
	}
	
	@PostMapping("/room/add")
	public Room addRoom(@RequestBody ApiRoom r1) {
		Room room1 = new Room(r1.getName(),r1.getLocation(),r1.getSeats(),new State(true,""));
		room1.setCapacity(new LayoutCapacity(Layout.PCs,r1.getPcs()));
		room1.setCapacity(new LayoutCapacity(Layout.Projector,r1.getProjector()));
		room1.setCapacity(new LayoutCapacity(Layout.BOARD,r1.getBoard()));

		return roomRepository.save(room1);
	}
	
	@PutMapping(value="/room/{id}")
	public Room updateRoom(@PathVariable("id") int id,@RequestBody ApiRoom r1) {
		Room room=roomRepository.findById(id).get();
		room.setName(r1.getName());
		room.setLocation(r1.getLocation());
		room.setSeats(r1.getSeats());
		room.getState().setStates(r1.getState());
		room.getState().setDetail(r1.getDetail());
		for (LayoutCapacity l:room.getCapacities()){
			if (l.getLayout().getDescription()=="pc")l.setCapacity(r1.getPcs());
			if (l.getLayout().getDescription()=="Projector")l.setCapacity(r1.getProjector());
			if (l.getLayout().getDescription()=="BOARD")l.setCapacity(r1.getBoard());
		}

		return roomRepository.save(room);
	}

	@GetMapping(value="/users")
	public List<User> getAllUsers(){return userRepo.findAll();
	}
	
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable("id") int id){
		return userRepo.findById(id).get();
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User aUser) {
		User u=userRepo.findById(aUser.getId()).get();
		u.setName(aUser.getName());
		System.out.println(u);
		return userRepo.save(u);
	}
	
	@PostMapping("/user")
	public User addUser(@RequestBody User aUser) {
		return userRepo.save(aUser);
	}
	
}
