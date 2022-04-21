package ru.itis.pashin.website.common.model.system.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewsDTO {

    private Long id;

    private String title;

    private String text;

    private boolean deleted;

    private String createdAt;
}
