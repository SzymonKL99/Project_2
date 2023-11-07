package com.crud.tasks.domain;
import lombok.Data;

@Data
public class TrelloBadges {
    private int votes;
    private AttachmentsByType attachments;
}