package models;

import lombok.*;

@Getter
@RequiredArgsConstructor
public final class Movie {

    private final int position;
    private final String title;
    private final String year;
    private final double rating;
}