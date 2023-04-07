package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/offers")
@RestController
public class OfferRestController {

    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("")
    public List<OfferDto> getOfferList(@RequestParam(required = false) String title) {
        return offerService.findAll(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable Long id) {
        Optional<OfferDto> offerDtoOpt = offerService.findOfferById(id);
        return offerDtoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public int getOffersCount() {
        return offerService.getOffersCount();
    }

    @PostMapping("")
    public ResponseEntity<OfferDto> saveOffer(@RequestBody OfferDto dto) {
        Optional<OfferDto> savedOfferDto = offerService.insertOffer(dto);
        return savedOfferDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }
}
