package com.example.demo.domain;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "invitations")
public class Invitation extends BaseEntity {

    private User user;

    private Status status;

    private LocalDateTime createOf;


    private LocalDateTime processOf;

    private Team team;

    public Invitation() {
        this.status = Status.WAITING;
        this.createOf = LocalDateTime.now();
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateOf() {
        return createOf;
    }

    public void setCreateOf(LocalDateTime createOf) {
        this.createOf = createOf;
    }

    public LocalDateTime getProcessOf() {
        return processOf;
    }

    public void setProcessOf(LocalDateTime processOf) {
        this.processOf = processOf;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
