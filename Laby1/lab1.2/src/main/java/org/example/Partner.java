package org.example;
import lombok.*;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Partner implements Serializable {
    String Name;
    URL Website;
    int SinceYear;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Offer> Offers;
}
