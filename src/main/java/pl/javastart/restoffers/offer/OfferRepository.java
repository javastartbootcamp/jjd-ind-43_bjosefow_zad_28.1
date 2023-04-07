package pl.javastart.restoffers.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT count(o) FROM Offer o")
    int getOfferCount();

    @Query("SELECT o FROM Offer o WHERE o.title like :title%")
    List<Offer> findOffersByTitle(String title);
}
