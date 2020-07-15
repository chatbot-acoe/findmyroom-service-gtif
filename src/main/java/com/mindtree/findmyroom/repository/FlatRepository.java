package com.mindtree.findmyroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.findmyroom.entity.Flat;

/**
 * @author M1049287
 *
 */
@Repository
public interface FlatRepository extends JpaRepository<Flat, Integer> {
	@Query(value = "Select * From  Flat f where f.flatcity =:flatCity and f.faltcategory=:flatPurpose and f.isavailable=true and f.flatlocation like :flatLocation%",nativeQuery=true)
	public List<Flat> getFlatDetailsIfLocationIsSingleCharacter(@Param("flatCity")String flatCity,@Param("flatLocation") String flatLocation,@Param("flatPurpose") String flatPurpose);												//Query to Fetch the flat details if the location is of single character

	@Query(value = "Select * From  Flat f where f.flatcity =:flatCity and f.faltcategory=:flatPurpose and f.isavailable=true and f.flatlocation=:flatLocation",nativeQuery=true)				//Query to fetch the flat details if the location matchs in the database
	public List<Flat> getAllFlatDetails(@Param("flatCity") String flatCity,@Param("flatLocation") String flatLocation,@Param("flatPurpose")String flatPurpose);

	@Query(value = "Select * From Flat f where f.flatcity =:flatCity and f.faltcategory=:flatPurpose and f.isavailable=true and f.flatlocation like :flatLocation%",nativeQuery=true)			//Queey to feth the flat details if the location spelling correct until half
	public List<Flat> getAllFlatDetailsMatchesHalfString(@Param("flatCity") String flatCity,@Param("flatLocation") String flatLocation,@Param("flatPurpose") String flatPurpose);

	@Query(value = " Select * From Flat f where f.flatid=:id",nativeQuery=true)
	public Flat getFlatDetails(@Param("id") int id);
	
	@Query(value = " Select * From Flat f where f.flatownerid=:id and f.isavailable=true",nativeQuery=true)
	public List<Flat> getPartnerFlats(@Param("id") int id);
	
	@Query(value = " Select * From Flat f where f.flatownerid=:id And f.flatid=:fid",nativeQuery=true)
	public Flat checkFlatWithEmailId(@Param("id") int id,@Param("fid") int fid);
}
