package com.example.tripback.api.dto;

import com.example.tripback.api.entity.Locations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class LocationDto {

    @NoArgsConstructor
    @Getter
    public static class SaveLocation{
        @NotNull
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(style = "yyyy-MM-dd")
        private LocalDate date;
        @NotNull
        private String locations;

        @Temporal(TemporalType.TIME)
        @DateTimeFormat(style = "hh:mm")
        private LocalTime time;
        private String note;
        private String planName;

        private Long eventId;

        public Locations toEntity(){
            return Locations.builder()
                    .date(date)
                    .locations(locations)
                    .time(time)
                    .note(note)
                    .planName(planName)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class SimpleLocation{
        private Long locationId;
        private LocalDate date;
        private String locations;
        private LocalTime time;
        private String note;
        private String planName;

        public SimpleLocation(Locations locations) {
            this.locationId = locations.getLocationId();
            this.date = locations.getDate();
            this.locations = locations.getLocations();
            this.time = locations.getTime();
            this.note = locations.getNote();
            this.planName = locations.getPlanName();
        }
    }
    @Setter
    @Getter
    public static class GetResponseLocation{
        private final List<SimpleLocation> locationsList;

        public GetResponseLocation(List<Locations> locationsList) {
            this.locationsList = locationsList.stream()
                    .map(SimpleLocation::new)
                    .collect(Collectors.toList());
        }
    }
}
