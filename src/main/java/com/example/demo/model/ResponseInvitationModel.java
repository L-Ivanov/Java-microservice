package com.example.demo.model;

import com.example.demo.domain.Status;

import java.time.LocalDateTime;

public class ResponseInvitationModel extends BaseModel {

    private UserModel user;
    private Status status;
    private LocalDateTime createOf;
    private LocalDateTime processOf;


    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

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
}
