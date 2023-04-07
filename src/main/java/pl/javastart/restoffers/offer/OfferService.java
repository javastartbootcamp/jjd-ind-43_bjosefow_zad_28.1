package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> findAll(String title) {
        List<Offer> offerList;
        if (title == null) {
            offerList = offerRepository.findAll();
        } else {
            offerList = offerRepository.findOffersByTitle(title);
        }
        return offerList.stream().map(this::toDto).collect(Collectors.toList());
    }

    private OfferDto toDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        setOfferValuesToDto(offer, offerDto);
        return offerDto;
    }

    public int getOffersCount() {
        return offerRepository.getOfferCount();
    }

    public Optional<OfferDto> insertOffer(OfferDto dto) {
        if (dto.getId() != null) {
            return Optional.empty();
        }
        Offer offerToDb = new Offer();
        setDtoValuesToOffer(dto, offerToDb);
        offerRepository.save(offerToDb);
        return Optional.of(toDto(offerToDb));
    }

    public Optional<OfferDto> findOfferById(Long id) {
        return offerRepository.findById(id).map(this::toDto);
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    private void setOfferValuesToDto(Offer offer, OfferDto dto) {
        dto.setId(offer.getId());
        dto.setTitle(offer.getTitle());
        dto.setDescription(offer.getDescription());
        dto.setImgUrl(offer.getImgUrl());
        dto.setPrice(offer.getPrice());
        dto.setCategory(offer.getCategory().getName());
    }

    private void setDtoValuesToOffer(OfferDto dto, Offer offer) {
        offer.setTitle(dto.getTitle());
        offer.setDescription(dto.getDescription());
        offer.setImgUrl(dto.getImgUrl());
        offer.setPrice(dto.getPrice());
        offer.setCategory(categoryRepository.getCategoriesByName(dto.getCategory()));
    }
}
