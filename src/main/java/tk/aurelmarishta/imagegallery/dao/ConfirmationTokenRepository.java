package tk.aurelmarishta.imagegallery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.aurelmarishta.imagegallery.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
