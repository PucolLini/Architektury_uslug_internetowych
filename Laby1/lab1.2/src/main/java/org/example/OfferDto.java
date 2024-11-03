package org.example;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class OfferDto implements Comparable<OfferDto>{
    String title;
    String description;
    Date validFrom;
    Date validTo;
    String partnerName;

    public OfferDto(Offer offer){
        title=offer.getTitle();
        description=offer.getDescription();
        validFrom=offer.getValidFrom();
        validTo=offer.getValidTo();
        partnerName=offer.getPartner().getName();
    }

    @Override
    public int compareTo(OfferDto o) {
        return title.compareTo(o.getTitle());
    }
}
