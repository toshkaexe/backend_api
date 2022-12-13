package server.restfull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import server.restfull.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("SELECT table FROM usersdetails AS table WHERE table.id = :id")
    User getByID(@Param("id") String id);

    @Query("SELECT table FROM usersdetails AS table WHERE table.email = :email")
    List<User> getByEmail(@Param("email") String source);


    @Query("SELECT table FROM usersdetails AS table WHERE table.phone = :phone")
    List<User> getByPhone(@Param("phone") String source);


    @Query("SELECT table FROM usersdetails AS table WHERE table.source = :source")
    List<User> getBySource(@Param("source") String source);

    @Query("SELECT   t.source, COUNT(t.email) FROM usersdetails AS t  GROUP BY t.source")
    List<Object[]> numberUserBySource();


    @Query("SELECT  DISTINCT t.source FROM usersdetails AS t")
    List<Object[]> getAllSourcesDistinct();


}