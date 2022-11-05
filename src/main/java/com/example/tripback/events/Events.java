package com.example.tripback.events;

import com.example.tripback.events.EventDto.PatchRequestDto;
import com.example.tripback.teams.Teams;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Events {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Teams teams;

    public void update(PatchRequestDto patchRequestDto){
        this.startDate = patchRequestDto.getStartDate();
        this.endDate = patchRequestDto.getEndDate();
        this.startTime = patchRequestDto.getStartTime();
        this.endTime = patchRequestDto.getEndTime();
        this.title = patchRequestDto.getTitle();
    }
}
