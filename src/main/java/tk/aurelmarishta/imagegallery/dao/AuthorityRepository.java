package tk.aurelmarishta.imagegallery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.aurelmarishta.imagegallery.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {


}
