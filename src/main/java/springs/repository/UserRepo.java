package springs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springs.dto.UserDto;

@Repository
public interface UserRepo extends JpaRepository<UserDto,Integer>{

	UserDto findByUsername(String username);

}
