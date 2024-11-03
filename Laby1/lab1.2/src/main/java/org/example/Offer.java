package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Offer implements Serializable {
    String Title;
    String Description;
    Date ValidFrom;
    Date ValidTo;
    Partner Partner;
}
