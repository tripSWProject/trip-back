package com.example.tripback.api.entity;

import com.example.tripback.api.dto.EventDto.PatchRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Events {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String title;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Teams teams;

    @OneToMany(mappedBy = "locationId", fetch = FetchType.LAZY)
    private List<Locations> locationList;

    public void update(PatchRequestDto patchRequestDto){
        this.startDate = patchRequestDto.getStartDate();
        this.endDate = patchRequestDto.getEndDate();
        this.title = patchRequestDto.getTitle();
    }
}
